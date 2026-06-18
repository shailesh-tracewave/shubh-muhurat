package com.techmeeva.chogadiyawidgets.features.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.techmeeva.chogadiyawidgets.R
import com.techmeeva.chogadiyawidgets.core.TimelineBuilder
import com.techmeeva.chogadiyawidgets.core.localization.AppLocalizer
import com.techmeeva.chogadiyawidgets.core.localization.AppTextKey
import com.techmeeva.chogadiyawidgets.core.state.AppState
import com.techmeeva.chogadiyawidgets.core.state.ChoghadiyaDataStore
import com.techmeeva.chogadiyawidgets.core.state.HomeTimelineState
import com.techmeeva.chogadiyawidgets.databinding.FragmentHomeBinding
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

        dataStore.homeStates.observe(viewLifecycleOwner) { states ->
            val state = states[appState.selectedCity.id] ?: return@observe
            bindHomeState(state)
        }

        binding.layoutLocationPicker.setOnClickListener {
            Toast.makeText(requireContext(), "Configure location mode in Settings tab.", Toast.LENGTH_SHORT).show()
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
        val diffSeconds = (end - now) / 1000

        if (diffSeconds > 0) {
            val hours = diffSeconds / 3600
            val minutes = (diffSeconds % 3600) / 60
            val seconds = diffSeconds % 60
            val countdownStr = String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds)
            binding.tvHeroCountdown.text = countdownStr
        } else {
            // Trigger local recalculation of timeline slots
            refreshHomeData(force = false)
        }
    }

    private fun bindHomeState(state: HomeTimelineState) {
        binding.tvCurrentCity.text = appState.selectedCity.city

        if (state.errorMessage != null && !state.hasContent) {
            Toast.makeText(requireContext(), state.errorMessage, Toast.LENGTH_LONG).show()
            return
        }

        if (!state.hasContent) {
            return
        }

        val currentSlot = state.currentSlot ?: return
        val language = appState.selectedLanguage

        // 1. Hero Card details
        binding.tvHeroSlotName.text = AppLocalizer.slotName(currentSlot.type, language)
        val colorRes = getSlotColorRes(currentSlot.type)
        val colorInt = ContextCompat.getColor(requireContext(), colorRes)
        binding.tvHeroSlotName.setTextColor(colorInt)
        binding.viewHeroColorDot.backgroundTintList = ColorStateList.valueOf(colorInt)

        binding.tvHeroBadge.text = AppLocalizer.text(AppTextKey.ACTIVE_NOW, language).uppercase(Locale.US)
        binding.tvHeroTimeRange.text = AppLocalizer.localizedRange(
            currentSlot.start,
            currentSlot.end,
            language,
            appState.selectedCity.timezone
        )
        binding.tvHeroMeaning.text = AppLocalizer.slotDescription(currentSlot.type, language)

        // 2. Astronomy strip
        binding.tvSunrise.text = AppLocalizer.localizedTime(state.sunrise, language)
        binding.tvSunset.text = AppLocalizer.localizedTime(state.sunset, language)
        binding.tvProvider.text = state.provider

        // 3. Upcoming Slots List
        binding.upcomingSlotsContainer.removeAllViews()
        val upcomingSlots = state.nextSlots.take(6)

        for ((index, slot) in upcomingSlots.withIndex()) {
            val slotRow = LayoutInflater.from(requireContext()).inflate(R.layout.item_timeline_slot, binding.upcomingSlotsContainer, false)
            val tvSlotName = slotRow.findViewById<TextView>(R.id.tv_slot_name)
            val tvSlotBadge = slotRow.findViewById<TextView>(R.id.tv_slot_badge)
            val tvSlotTimeRange = slotRow.findViewById<TextView>(R.id.tv_slot_time_range)
            val viewColorDot = slotRow.findViewById<View>(R.id.view_slot_color_dot)

            val slotColorRes = getSlotColorRes(slot.type)
            val slotColorInt = ContextCompat.getColor(requireContext(), slotColorRes)

            tvSlotName.text = AppLocalizer.slotName(slot.type, language)
            tvSlotName.setTextColor(slotColorInt)
            viewColorDot.backgroundTintList = ColorStateList.valueOf(slotColorInt)

            tvSlotTimeRange.text = AppLocalizer.localizedRange(
                slot.start,
                slot.end,
                language,
                appState.selectedCity.timezone
            )

            if (index == 0) {
                tvSlotBadge.visibility = View.VISIBLE
                tvSlotBadge.text = AppLocalizer.text(AppTextKey.NEXT, language).uppercase(Locale.US)
            } else {
                tvSlotBadge.visibility = View.GONE
            }

            binding.upcomingSlotsContainer.addView(slotRow)
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
}
