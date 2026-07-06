package com.techmeeva.chogadiyawidgets.features.home

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.techmeeva.chogadiyawidgets.R
import com.techmeeva.chogadiyawidgets.core.localization.AppLanguage
import com.techmeeva.chogadiyawidgets.core.localization.AppLocalizer
import com.techmeeva.chogadiyawidgets.core.localization.AppTextKey
import com.techmeeva.chogadiyawidgets.core.localization.LocalizedContentLanguage
import com.techmeeva.chogadiyawidgets.core.state.AppState
import com.techmeeva.chogadiyawidgets.core.state.AstronomyState
import com.techmeeva.chogadiyawidgets.core.state.ChoghadiyaDataStore
import com.techmeeva.chogadiyawidgets.core.state.HomeTimelineState
import com.techmeeva.chogadiyawidgets.core.state.WidgetUpdateWorker
import com.techmeeva.chogadiyawidgets.databinding.FragmentHomeBinding
import com.techmeeva.chogadiyawidgets.models.SeedCity
import com.techmeeva.chogadiyawidgets.models.TimelineSlot
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var appState: AppState
    private lateinit var dataStore: ChoghadiyaDataStore

    private var timerJob: Job? = null

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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvCurrentCity.text = appState.selectedCity.city
        binding.tvHomeDate.text = AppLocalizer.localizedShortDate(
            Date(),
            appState.selectedLanguage,
            appState.selectedCity.timezone
        )
        binding.tvHomeLoading.text = "Updating today’s flow"

        dataStore.homeStates.observe(viewLifecycleOwner) { states ->
            val state = states[appState.selectedCity.id] ?: return@observe
            bindHomeState(state)
        }

        dataStore.astronomyStates.observe(viewLifecycleOwner) { states ->
            val stateKey = "${appState.selectedCity.id}|${AppLocalizer.todayDateKey(appState.selectedCity.timezone)}"
            bindAstronomyState(states[stateKey] ?: AstronomyState())
        }

        binding.layoutLocationPicker.setOnClickListener {
            showLocationDialog()
        }
        binding.btnSkyShortcut.setOnClickListener {
            findNavController().navigate(R.id.skyFragment)
        }
        binding.layoutMoonPill.setOnClickListener {
            findNavController().navigate(R.id.skyFragment)
        }
        binding.cardSunrise.setOnClickListener {
            findNavController().navigate(R.id.skyFragment)
        }
        binding.cardSunset.setOnClickListener {
            findNavController().navigate(R.id.skyFragment)
        }
        binding.cardProvider.setOnClickListener {
            findNavController().navigate(R.id.skyFragment)
        }
        binding.tvFullSchedule.setOnClickListener {
            findNavController().navigate(R.id.calendarFragment)
        }
        binding.btnHomeRetry.setOnClickListener {
            refreshHomeData(force = true)
        }
    }

    override fun onResume() {
        super.onResume()
        refreshHomeData(force = false)
        startTimer()
    }

    override fun onPause() {
        super.onPause()
        stopTimer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun refreshHomeData(force: Boolean) {
        lifecycleScope.launch {
            try {
                dataStore.loadHome(
                    baseURL = appState.apiBaseURL,
                    city = appState.selectedCity,
                    subscriptionStatus = appState.subscriptionPlan.rawValue,
                    language = appState.selectedLanguage,
                    forceRefresh = force
                )
                dataStore.loadAstronomy(
                    baseURL = appState.apiBaseURL,
                    city = appState.selectedCity,
                    date = Date(),
                    subscriptionStatus = appState.subscriptionPlan.rawValue,
                    language = appState.selectedLanguage,
                    forceRefresh = force
                )
            } catch (e: Exception) {
                // Handled gracefully in LiveData state
            }
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewLifecycleOwner.lifecycleScope.launch {
            while (isActive) {
                updateCountdown()
                delay(1000)
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    private fun updateCountdown() {
        val state = dataStore.getHomeState(appState.selectedCity)
        val currentSlot = state.currentSlot ?: return

        val now = System.currentTimeMillis()
        val end = currentSlot.end.time
        val start = currentSlot.start.time
        val diffSeconds = (end - now) / 1000

        if (diffSeconds > 0) {
            val hours = diffSeconds / 3600
            val minutes = (diffSeconds % 3600) / 60
            val seconds = diffSeconds % 60
            val countdownStr = String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds)
            binding.tvHeroCountdown.text = countdownStr
            val total = (end - start).coerceAtLeast(1L)
            val elapsed = (now - start).coerceIn(0L, total)
            binding.progressHero.progress = ((elapsed.toDouble() / total.toDouble()) * 100).toInt()
        } else {
            // Trigger local recalculation of timeline slots
            refreshHomeData(force = false)
        }
    }

    private fun bindHomeState(state: HomeTimelineState) {
        val language = appState.selectedLanguage
        binding.tvCurrentCity.text = appState.selectedCity.city
        binding.tvHomeTitle.text = "Shubh Muhurat"
        binding.tvHomeDate.text = AppLocalizer.localizedShortDate(Date(), language, appState.selectedCity.timezone)
        binding.tvSunriseLabel.text = AppLocalizer.text(AppTextKey.SUNRISE, language)
        binding.tvSunsetLabel.text = AppLocalizer.text(AppTextKey.SUNSET, language)
        binding.tvTimelineTitle.text = AppLocalizer.text(AppTextKey.TODAYS_PATH, language)
        binding.tvFullSchedule.text = AppLocalizer.text(AppTextKey.FULL_SCHEDULE, language)
        binding.tvHeroEndsAt.text = AppLocalizer.text(AppTextKey.ENDS_AT, language)
        binding.btnHomeRetry.text = AppLocalizer.text(AppTextKey.TRY_AGAIN, language).uppercase(Locale.US)

        if (state.errorMessage != null && !state.hasContent) {
            binding.homeLoadingState.visibility = View.GONE
            binding.homeLoadedContent.visibility = View.GONE
            binding.homeErrorState.visibility = View.VISIBLE
            binding.tvHomeErrorTitle.text = AppLocalizer.text(AppTextKey.COULD_NOT_LOAD_TODAY_FLOW, language)
            binding.tvHomeErrorMessage.text = state.errorMessage
            return
        }

        if (!state.hasContent) {
            binding.homeLoadedContent.visibility = View.GONE
            binding.homeErrorState.visibility = View.GONE
            binding.homeLoadingState.visibility = if (state.isLoading) View.VISIBLE else View.GONE
            return
        }

        binding.homeLoadingState.visibility = View.GONE
        binding.homeErrorState.visibility = View.GONE
        binding.homeLoadedContent.visibility = View.VISIBLE

        if (state.errorMessage != null && state.isRefreshing) {
            Toast.makeText(requireContext(), state.errorMessage, Toast.LENGTH_SHORT).show()
        }

        val currentSlot = state.currentSlot ?: return

        // 1. Hero Card details
        binding.tvHeroSlotName.text = AppLocalizer.slotName(currentSlot.type, language)
        val colorRes = getSlotColorRes(currentSlot.type)
        val colorInt = ContextCompat.getColor(requireContext(), colorRes)
        binding.tvHeroSlotName.setTextColor(colorInt)
        binding.viewHeroColorDot.backgroundTintList = ColorStateList.valueOf(colorInt)
        binding.progressHero.progressTintList = ColorStateList.valueOf(colorInt)

        binding.tvHeroBadge.text = AppLocalizer.text(AppTextKey.ACTIVE_NOW, language)
        binding.tvHeroTimeRange.text = AppLocalizer.localizedRange(
            currentSlot.start,
            currentSlot.end,
            language,
            appState.selectedCity.timezone
        )
        binding.tvHeroEndTime.text = AppLocalizer.localizedTime(
            currentSlot.end,
            language,
            appState.selectedCity.timezone
        )
        binding.tvHeroMeaning.text = AppLocalizer.slotDescription(currentSlot.type, language)
        binding.tvTimelineSubtitle.text = AppLocalizer.slotMeaning(currentSlot.type, language)
        updateCountdown()

        // 2. Astronomy strip
        binding.tvSunrise.text = AppLocalizer.localizedTime(state.sunrise, language)
        binding.tvSunset.text = AppLocalizer.localizedTime(state.sunset, language)
        binding.tvProviderLabel.text = moonLabel(language)
        binding.tvProvider.text = state.provider.ifBlank { "--:--" }

        // 3. Upcoming Slots List
        binding.upcomingSlotsContainer.removeAllViews()
        val upcomingSlots = (listOf(currentSlot) + state.nextSlots).take(6)

        for ((index, slot) in upcomingSlots.withIndex()) {
            val slotRow = LayoutInflater.from(requireContext()).inflate(R.layout.item_timeline_slot, binding.upcomingSlotsContainer, false)
            val tvSlotName = slotRow.findViewById<TextView>(R.id.tv_slot_name)
            val tvSlotBadge = slotRow.findViewById<TextView>(R.id.tv_slot_badge)
            val tvSlotTimeRange = slotRow.findViewById<TextView>(R.id.tv_slot_time_range)
            val viewColorDot = slotRow.findViewById<View>(R.id.view_slot_color_dot)

            val slotColorRes = getSlotColorRes(slot.type)
            val slotColorInt = ContextCompat.getColor(requireContext(), slotColorRes)

            tvSlotName.text = AppLocalizer.slotName(slot.type, language)
            tvSlotName.setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_text))
            viewColorDot.backgroundTintList = ColorStateList.valueOf(slotColorInt)

            tvSlotTimeRange.text = AppLocalizer.localizedRange(
                slot.start,
                slot.end,
                language,
                appState.selectedCity.timezone
            )

            if (index == 0) {
                tvSlotBadge.visibility = View.VISIBLE
                tvSlotBadge.text = AppLocalizer.text(AppTextKey.ACTIVE_NOW, language)
                tvSlotBadge.backgroundTintList = ColorStateList.valueOf(slotColorInt)
                tvSlotBadge.setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_white))
            } else if (index == 1) {
                tvSlotBadge.visibility = View.VISIBLE
                tvSlotBadge.text = AppLocalizer.text(AppTextKey.NEXT, language)
                tvSlotBadge.backgroundTintList = null
                tvSlotBadge.setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_primary))
            } else {
                tvSlotBadge.visibility = View.GONE
            }

            binding.upcomingSlotsContainer.addView(slotRow)
        }
    }

    private fun bindAstronomyState(state: AstronomyState) {
        val language = appState.selectedLanguage
        val day = state.day
        if (day == null) {
            binding.layoutMoonPill.visibility = View.GONE
            return
        }

        val moonPhase = AppLocalizer.moonPhaseName(day.moonPhaseKey, language)
        binding.layoutMoonPill.visibility = View.VISIBLE
        binding.tvMoonPill.text = moonPhase
        binding.tvProviderLabel.text = moonPhase
        binding.tvProvider.text = day.moonrise?.let { AppLocalizer.localizedTime(it, language) } ?: "--:--"
    }

    private fun moonLabel(language: AppLanguage): String {
        return when (language.localizedContentLanguage) {
            LocalizedContentLanguage.ENGLISH -> "Moon"
            LocalizedContentLanguage.HINDI -> "चंद्र"
            LocalizedContentLanguage.GUJARATI -> "ચંદ્ર"
        }
    }

    private fun showLocationDialog() {
        val cities = appState.availableCities
        val items = cities.map { "${it.city}, ${it.state}" }.toTypedArray()
        val checkedItem = cities.indexOfFirst { it.id == appState.selectedCity.id }

        AlertDialog.Builder(requireContext())
            .setTitle(AppLocalizer.text(AppTextKey.SELECT_LOCATION, appState.selectedLanguage))
            .setSingleChoiceItems(items, checkedItem) { dialog, which ->
                val selected = cities.getOrNull(which) ?: SeedCity.ahmedabadFallback
                appState.selectCity(selected)
                binding.tvCurrentCity.text = selected.city
                dialog.dismiss()
                refreshHomeData(force = true)
                WidgetUpdateWorker.enqueueInitial(requireContext().applicationContext)
            }
            .setNegativeButton(AppLocalizer.text(AppTextKey.CLOSE, appState.selectedLanguage), null)
            .show()
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
}
