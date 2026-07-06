package com.techmeeva.chogadiyawidgets.features.calendar

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.techmeeva.chogadiyawidgets.R
import com.techmeeva.chogadiyawidgets.core.localization.AppLocalizer
import com.techmeeva.chogadiyawidgets.core.localization.AppTextKey
import com.techmeeva.chogadiyawidgets.core.localization.LocalizedContentLanguage
import com.techmeeva.chogadiyawidgets.core.state.AppState
import com.techmeeva.chogadiyawidgets.core.state.ChoghadiyaDataStore
import com.techmeeva.chogadiyawidgets.core.state.MonthScheduleState
import com.techmeeva.chogadiyawidgets.databinding.FragmentCalendarBinding
import com.techmeeva.chogadiyawidgets.models.ChoghadiyaDay
import com.techmeeva.chogadiyawidgets.models.ChoghadiyaSlot
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    private lateinit var appState: AppState
    private lateinit var dataStore: ChoghadiyaDataStore

    private lateinit var currentMonth: Date
    private lateinit var selectedDate: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appState = AppState(requireContext())
        dataStore = ChoghadiyaDataStore(requireContext())

        val now = Date()
        currentMonth = dataStore.normalizedMonth(now, appState.selectedCity.timezone)
        selectedDate = now
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupStaticTranslations()

        dataStore.monthStates.observe(viewLifecycleOwner) { states ->
            val normMonth = dataStore.normalizedMonth(currentMonth, appState.selectedCity.timezone)
            val stateKey = "${appState.selectedCity.id}|${formatStateKey(normMonth)}"
            val state = states[stateKey] ?: MonthScheduleState()
            bindMonthState(state)
        }

        binding.btnPrevMonth.setOnClickListener {
            changeMonth(-1)
        }

        binding.btnNextMonth.setOnClickListener {
            changeMonth(1)
        }

        binding.btnCalendarToday.setOnClickListener {
            val now = Date()
            currentMonth = dataStore.normalizedMonth(now, appState.selectedCity.timezone)
            selectedDate = now
            refreshMonthData(force = false)
        }

        binding.btnCalendarRetry.setOnClickListener {
            refreshMonthData(force = true)
        }

        binding.btnUnlockCalendar.setOnClickListener {
            Toast.makeText(requireContext(), AppLocalizer.text(AppTextKey.UPGRADE_IN_APP, appState.selectedLanguage), Toast.LENGTH_SHORT).show()
        }

        binding.calendarGrid.setOnItemClickListener { _, _, position, _ ->
            val adapter = binding.calendarGrid.adapter as? CalendarGridAdapter ?: return@setOnItemClickListener
            val clickedDate = adapter.getItem(position)

            if (!isSameMonth(clickedDate, currentMonth)) {
                currentMonth = dataStore.normalizedMonth(clickedDate, appState.selectedCity.timezone)
                selectedDate = clickedDate
                refreshMonthData(force = false)
                return@setOnItemClickListener
            }

            // Check if within premium boundary
            if (isPremiumLocked(clickedDate)) {
                binding.scrollDayDetails.visibility = View.GONE
                binding.layoutPaywall.visibility = View.VISIBLE
                binding.tvPaywallBody.text = AppLocalizer.text(AppTextKey.PREMIUM_PREVIEW_BODY, appState.selectedLanguage)
            } else {
                binding.layoutPaywall.visibility = View.GONE
                binding.scrollDayDetails.visibility = View.VISIBLE
                selectedDate = clickedDate
                adapter.setSelectedDate(clickedDate)
                val normMonth = dataStore.normalizedMonth(currentMonth, appState.selectedCity.timezone)
                val stateKey = "${appState.selectedCity.id}|${formatStateKey(normMonth)}"
                val state = dataStore.monthStates.value?.get(stateKey) ?: MonthScheduleState()
                bindSelectedDayDetails(state.days)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setupStaticTranslations()
        refreshMonthData(force = false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupStaticTranslations() {
        val language = appState.selectedLanguage
        binding.tvCalendarTitle.text = AppLocalizer.text(AppTextKey.CALENDAR, language)
        binding.tvCalendarSubtitle.text = appState.selectedCity.city
        binding.btnCalendarToday.text = AppLocalizer.text(AppTextKey.ACTIVE_NOW, language)
        binding.tvCalendarLegend.text = AppLocalizer.text(AppTextKey.AUSPICIOUS_DAYS, language)
        binding.tvDayNightSubtitle.text = AppLocalizer.text(AppTextKey.DAY_NIGHT_FLOW, language)
        binding.tvDaytimeHeader.text = AppLocalizer.text(AppTextKey.DAYTIME, language).uppercase(Locale.US)
        binding.tvNighttimeHeader.text = AppLocalizer.text(AppTextKey.NIGHTTIME, language).uppercase(Locale.US)
        binding.btnUnlockCalendar.text = AppLocalizer.text(AppTextKey.UPGRADE, language).uppercase(Locale.US)
        binding.btnCalendarRetry.text = AppLocalizer.text(AppTextKey.TRY_AGAIN, language).uppercase(Locale.US)
        binding.tvCalendarLoading.text = when (language.localizedContentLanguage) {
            LocalizedContentLanguage.ENGLISH -> "Updating calendar"
            LocalizedContentLanguage.HINDI -> "कैलेंडर अपडेट हो रहा है"
            LocalizedContentLanguage.GUJARATI -> "કેલેન્ડર અપડેટ થઈ રહ્યું છે"
        }

        // Populate Weekday labels
        binding.layoutWeekdays.removeAllViews()
        val weekdays = AppLocalizer.weekdayTitles(language)
        for (day in weekdays) {
            val tv = TextView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                gravity = android.view.Gravity.CENTER
                text = day
                setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_outline))
                textSize = 11f
                paintFlags = paintFlags or android.graphics.Paint.SUBPIXEL_TEXT_FLAG
                setTypeface(null, android.graphics.Typeface.BOLD)
            }
            binding.layoutWeekdays.addView(tv)
        }
    }

    private fun refreshMonthData(force: Boolean) {
        lifecycleScope.launch {
            try {
                dataStore.loadCalendarMonth(
                    baseURL = appState.apiBaseURL,
                    city = appState.selectedCity,
                    month = currentMonth,
                    forceRefresh = force
                )
            } catch (e: Exception) {
                // Handled in LiveData state
            }
        }
    }

    private fun changeMonth(offset: Int) {
        val cal = Calendar.getInstance(TimeZone.getTimeZone(appState.selectedCity.timezone), Locale.US)
        cal.time = currentMonth
        cal.add(Calendar.MONTH, offset)
        currentMonth = cal.time
        selectedDate = firstDayOfMonth(currentMonth)
        refreshMonthData(force = false)
    }

    private fun isPremiumLocked(date: Date): Boolean {
        if (appState.isPremium) return false

        // Check if date is after today + 7 days
        val limitCal = Calendar.getInstance(TimeZone.getTimeZone(appState.selectedCity.timezone), Locale.US)
        limitCal.add(Calendar.DAY_OF_YEAR, 7)

        val targetCal = Calendar.getInstance(TimeZone.getTimeZone(appState.selectedCity.timezone), Locale.US)
        targetCal.time = date

        // Clear time variables for exact date boundaries comparison
        clearTimeFields(limitCal)
        clearTimeFields(targetCal)

        return targetCal.after(limitCal)
    }

    private fun clearTimeFields(cal: Calendar) {
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
    }

    private fun bindMonthState(state: MonthScheduleState) {
        binding.tvMonthTitle.text = AppLocalizer.localizedMonthYear(currentMonth, appState.selectedLanguage, appState.selectedCity.timezone)
        binding.tvCalendarSubtitle.text = appState.selectedCity.city

        if (state.errorMessage != null && !state.hasContent) {
            binding.calendarLoadingState.visibility = View.GONE
            binding.scrollDayDetails.visibility = View.GONE
            binding.layoutPaywall.visibility = View.GONE
            binding.calendarErrorState.visibility = View.VISIBLE
            binding.tvCalendarErrorTitle.text = AppLocalizer.text(AppTextKey.COULD_NOT_LOAD_MONTH, appState.selectedLanguage)
            binding.tvCalendarErrorMessage.text = state.errorMessage
            return
        }

        if (!state.hasContent) {
            binding.scrollDayDetails.visibility = View.GONE
            binding.layoutPaywall.visibility = View.GONE
            binding.calendarErrorState.visibility = View.GONE
            binding.calendarLoadingState.visibility = if (state.isLoading) View.VISIBLE else View.GONE
            return
        }

        binding.calendarErrorState.visibility = View.GONE
        binding.calendarLoadingState.visibility = if (state.isRefreshing) View.VISIBLE else View.GONE
        binding.scrollDayDetails.visibility = View.VISIBLE

        val days = getDaysForMonth(currentMonth, appState.selectedCity.timezone)
        binding.calendarGrid.adapter = CalendarGridAdapter(days, state.days)

        // Auto select current date or default to 1st of month
        bindSelectedDayDetails(state.days)
    }

    private fun bindSelectedDayDetails(days: List<ChoghadiyaDay>) {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US).apply {
            timeZone = TimeZone.getTimeZone(appState.selectedCity.timezone)
        }
        val dateStr = sdf.format(selectedDate)
        binding.tvSelectedDateLabel.text = AppLocalizer.localizedLongDate(dateStr, appState.selectedLanguage, appState.selectedCity.timezone)
        binding.tvDayNightSubtitle.text = AppLocalizer.text(AppTextKey.DAY_NIGHT_FLOW, appState.selectedLanguage)

        val dayData = days.firstOrNull { it.date == dateStr }

        binding.daytimeSlotsContainer.removeAllViews()
        binding.nighttimeSlotsContainer.removeAllViews()

        if (dayData == null) {
            val emptyTv = TextView(requireContext()).apply {
                text = AppLocalizer.text(AppTextKey.COULD_NOT_LOAD_TODAY_FLOW, appState.selectedLanguage)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_text_muted))
                textSize = 14f
            }
            binding.daytimeSlotsContainer.addView(emptyTv)
            return
        }

        // Split daytime and nighttime slots
        // Daytime: slot index 1..8
        // Nighttime: slot index 9..16
        val daySlots = dayData.slots.filter { it.slot in 1..8 }
        val nightSlots = dayData.slots.filter { it.slot in 9..16 }

        bindSlotsToContainer(daySlots, binding.daytimeSlotsContainer)
        bindSlotsToContainer(nightSlots, binding.nighttimeSlotsContainer)
    }

    private fun bindSlotsToContainer(slots: List<ChoghadiyaSlot>, container: LinearLayout) {
        val language = appState.selectedLanguage

        for (slot in slots) {
            val slotRow = LayoutInflater.from(requireContext()).inflate(R.layout.item_timeline_slot, container, false)
            val tvSlotName = slotRow.findViewById<TextView>(R.id.tv_slot_name)
            val tvSlotBadge = slotRow.findViewById<TextView>(R.id.tv_slot_badge)
            val tvSlotTimeRange = slotRow.findViewById<TextView>(R.id.tv_slot_time_range)
            val viewColorDot = slotRow.findViewById<View>(R.id.view_slot_color_dot)

            val slotColorRes = getSlotColorRes(slot.type)
            val slotColorInt = ContextCompat.getColor(requireContext(), slotColorRes)

            tvSlotName.text = AppLocalizer.slotName(slot.type, language)
            tvSlotName.setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_text))
            viewColorDot.backgroundTintList = ColorStateList.valueOf(slotColorInt)

            // Convert raw start_time / end_time or construct correct dates
            tvSlotTimeRange.text = "${AppLocalizer.localizedTime(slot.startTime, language)} - ${AppLocalizer.localizedTime(slot.endTime, language)}"

            tvSlotBadge.visibility = View.VISIBLE
            tvSlotBadge.text = AppLocalizer.slotMeaning(slot.type, language)
            tvSlotBadge.setBackgroundResource(R.drawable.bg_capsule_inactive)
            tvSlotBadge.setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_outline))

            container.addView(slotRow)
        }
    }

    private fun getSlotColorRes(type: String): Int {
        return when (type.lowercase(Locale.US)) {
            "shubh" -> R.color.choghadiya_shubh
            "labh" -> R.color.choghadiya_labh
            "amrit" -> R.color.choghadiya_amrit
            "chal" -> R.color.choghadiya_chal
            "rog" -> R.color.choghadiya_rog
            "kal" -> R.color.choghadiya_kal
            "udveg" -> R.color.choghadiya_udveg
            else -> R.color.celestial_primary
        }
    }

    private fun getDaysForMonth(monthDate: Date, timezone: String): List<Date> {
        val days = mutableListOf<Date>()
        val cal = Calendar.getInstance(TimeZone.getTimeZone(timezone), Locale.US)
        cal.time = monthDate
        cal.set(Calendar.DAY_OF_MONTH, 1)

        val firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
        val prefixDays = when (firstDayOfWeek) {
            Calendar.MONDAY -> 0
            Calendar.TUESDAY -> 1
            Calendar.WEDNESDAY -> 2
            Calendar.THURSDAY -> 3
            Calendar.FRIDAY -> 4
            Calendar.SATURDAY -> 5
            Calendar.SUNDAY -> 6
            else -> 0
        }

        // Add padding from previous month
        val prevCal = cal.clone() as Calendar
        prevCal.add(Calendar.MONTH, -1)
        val maxDaysInPrevMonth = prevCal.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in prefixDays - 1 downTo 0) {
            val dCal = prevCal.clone() as Calendar
            dCal.set(Calendar.DAY_OF_MONTH, maxDaysInPrevMonth - i)
            days.add(dCal.time)
        }

        // Add actual month days
        val daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in 1..daysInMonth) {
            val dCal = cal.clone() as Calendar
            dCal.set(Calendar.DAY_OF_MONTH, i)
            days.add(dCal.time)
        }

        // Pad to exactly 42 items for complete grid layout representation
        val remaining = 42 - days.size
        val nextCal = cal.clone() as Calendar
        nextCal.add(Calendar.MONTH, 1)
        for (i in 1..remaining) {
            val dCal = nextCal.clone() as Calendar
            dCal.set(Calendar.DAY_OF_MONTH, i)
            days.add(dCal.time)
        }

        return days
    }

    private fun formatStateKey(date: Date): String {
        val sdf = SimpleDateFormat("yyyy-MM", Locale.US).apply {
            timeZone = TimeZone.getTimeZone(appState.selectedCity.timezone)
        }
        return sdf.format(date)
    }

    private fun firstDayOfMonth(date: Date): Date {
        val cal = Calendar.getInstance(TimeZone.getTimeZone(appState.selectedCity.timezone), Locale.US)
        cal.time = dataStore.normalizedMonth(date, appState.selectedCity.timezone)
        return cal.time
    }

    private fun isSameMonth(first: Date, second: Date): Boolean {
        val c1 = Calendar.getInstance(TimeZone.getTimeZone(appState.selectedCity.timezone), Locale.US).apply { time = first }
        val c2 = Calendar.getInstance(TimeZone.getTimeZone(appState.selectedCity.timezone), Locale.US).apply { time = second }
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
    }

    private inner class CalendarGridAdapter(
        private val days: List<Date>,
        private val monthDaysData: List<ChoghadiyaDay>
    ) : BaseAdapter() {

        private var selectedDay: Date = selectedDate
        private val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US).apply {
            timeZone = TimeZone.getTimeZone(appState.selectedCity.timezone)
        }

        fun setSelectedDate(date: Date) {
            selectedDay = date
            notifyDataSetChanged()
        }

        override fun getCount(): Int = days.size

        override fun getItem(position: Int): Date = days[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
            val tvDayNumber = view.findViewById<TextView>(R.id.tv_day_number)
            val dotAuspicious = view.findViewById<View>(R.id.dot_auspicious)
            val dotNormal = view.findViewById<View>(R.id.dot_normal)
            val dotInauspicious = view.findViewById<View>(R.id.dot_inauspicious)

            val date = getItem(position)
            val cellCal = Calendar.getInstance(TimeZone.getTimeZone(appState.selectedCity.timezone), Locale.US).apply {
                time = date
            }
            val currentMonthCal = Calendar.getInstance(TimeZone.getTimeZone(appState.selectedCity.timezone), Locale.US).apply {
                time = currentMonth
            }

            // Set day number
            tvDayNumber.text = cellCal.get(Calendar.DAY_OF_MONTH).toString()

            // Visual dimming for outer month dates
            val isCurrentMonth = cellCal.get(Calendar.MONTH) == currentMonthCal.get(Calendar.MONTH)
            if (isCurrentMonth) {
                tvDayNumber.setTextColor(ContextCompat.getColor(parent.context, R.color.celestial_text))
            } else {
                tvDayNumber.setTextColor(ContextCompat.getColor(parent.context, R.color.celestial_text_muted))
                tvDayNumber.alpha = 0.45f
            }
            if (isCurrentMonth) tvDayNumber.alpha = 1f

            // Highlighting selection
            val isSelected = isSameDay(date, selectedDay)
            if (isSelected) {
                tvDayNumber.setBackgroundResource(R.drawable.bg_calendar_day_selected)
                tvDayNumber.backgroundTintList = null
                tvDayNumber.setTextColor(Color.WHITE)
            } else {
                tvDayNumber.background = null
                tvDayNumber.backgroundTintList = null
            }

            // Auspicious dot highlights
            val dateStr = sdf.format(date)
            val dayData = monthDaysData.firstOrNull { it.date == dateStr }

            if (dayData != null) {
                val types = dayData.slots.map { it.type.lowercase(Locale.US) }
                
                val hasAuspicious = types.any { it == "shubh" || it == "amrit" || it == "labh" }
                val hasNormal = types.any { it == "chal" }
                val hasInauspicious = types.any { it == "rog" || it == "kal" || it == "udveg" }

                if (hasAuspicious) {
                    dotAuspicious.visibility = View.VISIBLE
                    dotAuspicious.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(parent.context, R.color.choghadiya_shubh))
                } else {
                    dotAuspicious.visibility = View.GONE
                }

                if (hasNormal) {
                    dotNormal.visibility = View.VISIBLE
                    dotNormal.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(parent.context, R.color.choghadiya_chal))
                } else {
                    dotNormal.visibility = View.GONE
                }

                if (hasInauspicious) {
                    dotInauspicious.visibility = View.VISIBLE
                    dotInauspicious.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(parent.context, R.color.choghadiya_rog))
                } else {
                    dotInauspicious.visibility = View.GONE
                }
            } else {
                dotAuspicious.visibility = View.GONE
                dotNormal.visibility = View.GONE
                dotInauspicious.visibility = View.GONE
            }

            return view
        }

        private fun isSameDay(d1: Date, d2: Date): Boolean {
            val c1 = Calendar.getInstance(TimeZone.getTimeZone(appState.selectedCity.timezone), Locale.US).apply { time = d1 }
            val c2 = Calendar.getInstance(TimeZone.getTimeZone(appState.selectedCity.timezone), Locale.US).apply { time = d2 }
            return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                    c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR)
        }
    }
}
