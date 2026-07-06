package com.techmeeva.chogadiyawidgets.features.panchang

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.techmeeva.chogadiyawidgets.R
import com.techmeeva.chogadiyawidgets.core.localization.AppLanguage
import com.techmeeva.chogadiyawidgets.core.localization.AppLocalizer
import com.techmeeva.chogadiyawidgets.core.localization.AppTextKey
import com.techmeeva.chogadiyawidgets.core.localization.LocalizedContentLanguage
import com.techmeeva.chogadiyawidgets.core.state.AppState
import com.techmeeva.chogadiyawidgets.core.state.ChoghadiyaDataStore
import com.techmeeva.chogadiyawidgets.core.state.PanchangState
import com.techmeeva.chogadiyawidgets.databinding.FragmentPanchangBinding
import com.techmeeva.chogadiyawidgets.models.PanchangDay
import com.techmeeva.chogadiyawidgets.models.PanchangItem
import com.techmeeva.chogadiyawidgets.models.TimingWindow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class PanchangFragment : Fragment() {

    private var _binding: FragmentPanchangBinding? = null
    private val binding get() = _binding!!
    private lateinit var appState: AppState
    private lateinit var dataStore: ChoghadiyaDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appState = AppState(requireContext())
        dataStore = ChoghadiyaDataStore(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPanchangBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupStaticText()

        binding.btnPanchangRetry.setOnClickListener {
            refreshPanchang(force = true)
        }

        dataStore.panchangStates.observe(viewLifecycleOwner) { states ->
            val dateStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).apply {
                timeZone = TimeZone.getTimeZone(appState.selectedCity.timezone)
            }.format(Date())
            val stateKey = "${appState.selectedCity.id}|$dateStr"
            bindState(states[stateKey] ?: PanchangState())
        }
    }

    override fun onResume() {
        super.onResume()
        refreshPanchang(force = false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun refreshPanchang(force: Boolean) {
        lifecycleScope.launch {
            dataStore.loadPanchang(
                baseURL = appState.apiBaseURL,
                city = appState.selectedCity,
                date = Date(),
                subscriptionStatus = appState.subscriptionPlan.rawValue,
                language = appState.selectedLanguage,
                forceRefresh = force
            )
        }
    }

    private fun setupStaticText() {
        val language = appState.selectedLanguage
        binding.tvPanchangTitle.text = panchangTitle(language)
        binding.tvPanchangSubtitle.text = "${appState.selectedCity.name} • ${AppLocalizer.localizedShortDate(Date(), language, appState.selectedCity.timezone)}"
        binding.tvPanchangLoading.text = when (language.localizedContentLanguage) {
            LocalizedContentLanguage.ENGLISH -> "Updating Panchang"
            LocalizedContentLanguage.HINDI -> "पंचांग अपडेट हो रहा है"
            LocalizedContentLanguage.GUJARATI -> "પંચાંગ અપડેટ થઈ રહ્યું છે"
        }
        binding.btnPanchangRetry.text = AppLocalizer.text(AppTextKey.TRY_AGAIN, language).uppercase(Locale.US)
        binding.tvRashiHeader.text = sectionTitle("RASHI & NAKSHATRA", language)
        binding.tvTimeHeader.text = sectionTitle("DAY DETAILS", language)
        binding.tvAuspiciousHeader.text = sectionTitle("AUSPICIOUS WINDOWS", language)
        binding.tvInauspiciousHeader.text = sectionTitle("INAUSPICIOUS WINDOWS", language)
        binding.tvNivasHeader.text = sectionTitle("NIVAS & SHOOL", language)
        binding.tvBalaHeader.text = sectionTitle("BALA", language)
        binding.tvEpochsHeader.text = sectionTitle("CALENDAR SYSTEMS", language)
        binding.tvLagnaHeader.text = sectionTitle("UDAYA LAGNA", language)
    }

    private fun bindState(state: PanchangState) {
        if (state.errorMessage != null && !state.hasContent) {
            binding.panchangLoadingState.visibility = View.GONE
            binding.panchangErrorState.visibility = View.VISIBLE
            binding.panchangContent.visibility = View.GONE
            binding.tvPanchangErrorTitle.text = when (appState.selectedLanguage.localizedContentLanguage) {
                LocalizedContentLanguage.ENGLISH -> "Could not update Panchang"
                LocalizedContentLanguage.HINDI -> "पंचांग अपडेट नहीं हो सका"
                LocalizedContentLanguage.GUJARATI -> "પંચાંગ અપડેટ થઈ શક્યું નહીં"
            }
            binding.tvPanchangErrorMessage.text = state.errorMessage
            return
        }

        if (!state.hasContent) {
            binding.panchangErrorState.visibility = View.GONE
            binding.panchangLoadingState.visibility = if (state.isLoading) View.VISIBLE else View.GONE
            binding.panchangContent.visibility = View.GONE
            return
        }

        binding.panchangErrorState.visibility = View.GONE
        binding.panchangLoadingState.visibility = if (state.isRefreshing) View.VISIBLE else View.GONE
        binding.panchangContent.visibility = View.VISIBLE
        state.day?.let { bindDay(it) }
    }

    private fun bindDay(day: PanchangDay) {
        val language = appState.selectedLanguage
        binding.tvPanchangWeekday.text = day.weekday.uppercase(Locale.US)
        binding.tvPanchangDate.text = AppLocalizer.localizedLongDate(day.date, language, appState.selectedCity.timezone)

        binding.tvPanchangSunrise.text = metricLabel(AppLocalizer.text(AppTextKey.SUNRISE, language), day.sunrise, language)
        binding.tvPanchangSunset.text = metricLabel(AppLocalizer.text(AppTextKey.SUNSET, language), day.sunset, language)
        binding.tvPanchangMoonrise.text = metricLabel(moonriseTitle(language), day.moonrise, language)
        binding.tvPanchangMoonset.text = metricLabel(moonsetTitle(language), day.moonset, language)
        binding.tvPanchangNoon.text = metricLabel(solarNoonTitle(language), day.solarNoon, language)
        binding.tvPanchangIllumination.text = "${illuminationTitle(language)}\n${String.format(Locale.US, "%.0f%%", day.illuminationFraction * 100)}"

        bindItems(day.limbs, binding.limbsContainer, R.color.celestial_primary_strong, showEnds = true)
        bindItems(day.rashi, binding.rashiContainer, R.color.celestial_moon)
        bindItems(day.rituAyana, binding.rituContainer, R.color.choghadiya_labh)
        bindWindows(day.auspiciousWindows, binding.auspiciousContainer, R.color.choghadiya_amrit)
        bindWindows(day.inauspiciousWindows, binding.inauspiciousContainer, R.color.choghadiya_rog)
        bindItems(day.nivasShool, binding.nivasContainer, R.color.choghadiya_shubh)
        bindItems(day.bala, binding.balaContainer, R.color.choghadiya_amrit)
        bindItems(day.calendarEpochs, binding.epochsContainer, R.color.celestial_primary_strong)
        bindWindows(day.lagna, binding.lagnaContainer, R.color.celestial_moon, limit = 12)
        bindNotes(day.notes)
    }

    private fun bindItems(items: List<PanchangItem>, container: LinearLayout, colorRes: Int, showEnds: Boolean = false) {
        container.removeAllViews()
        val colorInt = ContextCompat.getColor(requireContext(), colorRes)
        if (items.isEmpty()) {
            addEmpty(container)
            return
        }
        items.forEach { item ->
            val row = LayoutInflater.from(requireContext()).inflate(R.layout.item_timeline_slot, container, false)
            val tvName = row.findViewById<TextView>(R.id.tv_slot_name)
            val tvBadge = row.findViewById<TextView>(R.id.tv_slot_badge)
            val tvValue = row.findViewById<TextView>(R.id.tv_slot_time_range)
            val dot = row.findViewById<View>(R.id.view_slot_color_dot)

            dot.backgroundTintList = ColorStateList.valueOf(colorInt)
            tvName.text = localizedPanchangTitle(item.title, appState.selectedLanguage)
            tvName.setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_text))
            tvValue.text = listOfNotNull(localizedPanchangValue(item.value), item.detail).joinToString(" • ")
            tvValue.isSingleLine = false
            tvValue.maxLines = 3
            if (showEnds && item.endsAt != null) {
                tvBadge.visibility = View.VISIBLE
                tvBadge.text = "${endsTitle(appState.selectedLanguage)} ${localizedMaybeTime(item.endsAt, appState.selectedLanguage)}"
                tvBadge.setBackgroundResource(R.drawable.bg_capsule_inactive)
            } else {
                tvBadge.visibility = View.GONE
            }
            container.addView(row)
        }
    }

    private fun bindWindows(windows: List<TimingWindow>, container: LinearLayout, colorRes: Int, limit: Int = 8) {
        container.removeAllViews()
        val colorInt = ContextCompat.getColor(requireContext(), colorRes)
        if (windows.isEmpty()) {
            addEmpty(container)
            return
        }
        windows.take(limit).forEach { window ->
            val row = LayoutInflater.from(requireContext()).inflate(R.layout.item_timeline_slot, container, false)
            val tvName = row.findViewById<TextView>(R.id.tv_slot_name)
            val tvBadge = row.findViewById<TextView>(R.id.tv_slot_badge)
            val tvValue = row.findViewById<TextView>(R.id.tv_slot_time_range)
            val dot = row.findViewById<View>(R.id.view_slot_color_dot)

            dot.backgroundTintList = ColorStateList.valueOf(colorInt)
            tvName.text = AppLocalizer.astronomyWindowTitle(window.key, appState.selectedLanguage)
            tvValue.text = "${AppLocalizer.localizedTime(window.startTime, appState.selectedLanguage)} - ${AppLocalizer.localizedTime(window.endTime, appState.selectedLanguage)}"
            tvBadge.visibility = View.VISIBLE
            tvBadge.text = window.source.uppercase(Locale.US)
            tvBadge.setBackgroundResource(R.drawable.bg_capsule_inactive)
            container.addView(row)
        }
    }

    private fun bindNotes(notes: List<String>) {
        binding.notesContainer.removeAllViews()
        notes.forEach { note ->
            val textView = TextView(requireContext()).apply {
                text = "• $note"
                setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_text_muted))
                textSize = 12f
                setPadding(4, 4, 4, 4)
            }
            binding.notesContainer.addView(textView)
        }
    }

    private fun addEmpty(container: LinearLayout) {
        val emptyTv = TextView(requireContext()).apply {
            text = when (appState.selectedLanguage.localizedContentLanguage) {
                LocalizedContentLanguage.ENGLISH -> "No details available."
                LocalizedContentLanguage.HINDI -> "विवरण उपलब्ध नहीं है."
                LocalizedContentLanguage.GUJARATI -> "વિગતો ઉપલબ્ધ નથી."
            }
            setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_text_muted))
            textSize = 13f
            setPadding(12, 12, 12, 12)
        }
        container.addView(emptyTv)
    }

    private fun metricLabel(title: String, rawTime: String?, language: AppLanguage): String {
        val time = rawTime?.let { AppLocalizer.localizedTime(it, language) } ?: "--:--"
        return "$title\n$time"
    }

    private fun localizedMaybeTime(value: String, language: AppLanguage): String {
        return if (value.matches(Regex("^\\d{2}:\\d{2}$"))) AppLocalizer.localizedTime(value, language) else value
    }

    private fun localizedPanchangTitle(title: String, language: AppLanguage): String {
        return when (language.localizedContentLanguage) {
            LocalizedContentLanguage.ENGLISH -> title
            LocalizedContentLanguage.HINDI -> when (title.lowercase(Locale.US)) {
                "tithi" -> "तिथि"
                "nakshatra" -> "नक्षत्र"
                "yoga" -> "योग"
                "karana" -> "करण"
                "vara" -> "वार"
                "moonsign" -> "चंद्र राशि"
                "sunsign" -> "सूर्य राशि"
                "ritu" -> "ऋतु"
                "ayana" -> "अयन"
                "dinamana" -> "दिनमान"
                "ratrimana" -> "रात्रिमान"
                "madhyahna" -> "मध्याह्न"
                else -> title
            }
            LocalizedContentLanguage.GUJARATI -> when (title.lowercase(Locale.US)) {
                "tithi" -> "તિથિ"
                "nakshatra" -> "નક્ષત્ર"
                "yoga" -> "યોગ"
                "karana" -> "કરણ"
                "vara" -> "વાર"
                "moonsign" -> "ચંદ્ર રાશિ"
                "sunsign" -> "સૂર્ય રાશિ"
                "ritu" -> "ઋતુ"
                "ayana" -> "અયન"
                "dinamana" -> "દિનમાન"
                "ratrimana" -> "રાત્રિમાન"
                "madhyahna" -> "મધ્યાહ્ન"
                else -> title
            }
        }
    }

    private fun localizedPanchangValue(value: String): String = value

    private fun panchangTitle(language: AppLanguage): String {
        return when (language.localizedContentLanguage) {
            LocalizedContentLanguage.ENGLISH -> "Panchang"
            LocalizedContentLanguage.HINDI -> "पंचांग"
            LocalizedContentLanguage.GUJARATI -> "પંચાંગ"
        }
    }

    private fun sectionTitle(english: String, language: AppLanguage): String {
        return when (language.localizedContentLanguage) {
            LocalizedContentLanguage.ENGLISH -> english
            LocalizedContentLanguage.HINDI -> when (english) {
                "RASHI & NAKSHATRA" -> "राशि और नक्षत्र"
                "DAY DETAILS" -> "दिन विवरण"
                "AUSPICIOUS WINDOWS" -> "शुभ मुहूर्त"
                "INAUSPICIOUS WINDOWS" -> "अशुभ समय"
                "NIVAS & SHOOL" -> "निवास और शूल"
                "BALA" -> "बल"
                "CALENDAR SYSTEMS" -> "पंचांग संवत"
                "UDAYA LAGNA" -> "उदय लग्न"
                else -> english
            }
            LocalizedContentLanguage.GUJARATI -> when (english) {
                "RASHI & NAKSHATRA" -> "રાશિ અને નક્ષત્ર"
                "DAY DETAILS" -> "દિવસ વિગતો"
                "AUSPICIOUS WINDOWS" -> "શુભ મુહૂર્ત"
                "INAUSPICIOUS WINDOWS" -> "અશુભ સમય"
                "NIVAS & SHOOL" -> "નિવાસ અને શૂલ"
                "BALA" -> "બળ"
                "CALENDAR SYSTEMS" -> "પંચાંગ સંવત"
                "UDAYA LAGNA" -> "ઉદય લગ્ન"
                else -> english
            }
        }
    }

    private fun moonriseTitle(language: AppLanguage) = when (language.localizedContentLanguage) {
        LocalizedContentLanguage.ENGLISH -> "Moonrise"
        LocalizedContentLanguage.HINDI -> "चंद्रोदय"
        LocalizedContentLanguage.GUJARATI -> "ચંદ્રોદય"
    }

    private fun moonsetTitle(language: AppLanguage) = when (language.localizedContentLanguage) {
        LocalizedContentLanguage.ENGLISH -> "Moonset"
        LocalizedContentLanguage.HINDI -> "चंद्रास्त"
        LocalizedContentLanguage.GUJARATI -> "ચંદ્રાસ્ત"
    }

    private fun solarNoonTitle(language: AppLanguage) = when (language.localizedContentLanguage) {
        LocalizedContentLanguage.ENGLISH -> "Solar Noon"
        LocalizedContentLanguage.HINDI -> "सौर मध्याह्न"
        LocalizedContentLanguage.GUJARATI -> "સૌર મધ્યાહ્ન"
    }

    private fun illuminationTitle(language: AppLanguage) = when (language.localizedContentLanguage) {
        LocalizedContentLanguage.ENGLISH -> "Illumination"
        LocalizedContentLanguage.HINDI -> "प्रकाश"
        LocalizedContentLanguage.GUJARATI -> "પ્રકાશ"
    }

    private fun endsTitle(language: AppLanguage) = when (language.localizedContentLanguage) {
        LocalizedContentLanguage.ENGLISH -> "Ends"
        LocalizedContentLanguage.HINDI -> "समाप्त"
        LocalizedContentLanguage.GUJARATI -> "સમાપ્ત"
    }
}
