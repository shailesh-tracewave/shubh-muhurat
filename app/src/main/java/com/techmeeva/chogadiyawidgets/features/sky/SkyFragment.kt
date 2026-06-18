package com.techmeeva.chogadiyawidgets.features.sky

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
import com.techmeeva.chogadiyawidgets.core.localization.AppLocalizer
import com.techmeeva.chogadiyawidgets.core.state.AppState
import com.techmeeva.chogadiyawidgets.core.state.ChoghadiyaDataStore
import com.techmeeva.chogadiyawidgets.core.state.AstronomyState
import com.techmeeva.chogadiyawidgets.databinding.FragmentSkyBinding
import com.techmeeva.chogadiyawidgets.models.TimingWindow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class SkyFragment : Fragment() {

    private var _binding: FragmentSkyBinding? = null
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
        _binding = FragmentSkyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStore.astronomyStates.observe(viewLifecycleOwner) { states ->
            val dateStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).apply {
                timeZone = TimeZone.getTimeZone(appState.selectedCity.timezone)
            }.format(Date())

            val stateKey = "${appState.selectedCity.id}|$dateStr"
            val state = states[stateKey] ?: AstronomyState()
            bindAstronomyState(state)
        }
    }

    override fun onResume() {
        super.onResume()
        refreshAstronomyData(force = false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun refreshAstronomyData(force: Boolean) {
        lifecycleScope.launch {
            try {
                dataStore.loadAstronomy(
                    baseURL = appState.apiBaseURL,
                    city = appState.selectedCity,
                    date = Date(),
                    subscriptionStatus = appState.subscriptionPlan.rawValue,
                    language = appState.selectedLanguage,
                    forceRefresh = force
                )
            } catch (e: Exception) {
                // Gracefully handled by state
            }
        }
    }

    private fun bindAstronomyState(state: AstronomyState) {
        if (state.errorMessage != null && !state.hasContent) {
            Toast.makeText(requireContext(), state.errorMessage, Toast.LENGTH_SHORT).show()
            return
        }

        if (!state.hasContent) return

        val day = state.day ?: return
        val language = appState.selectedLanguage

        // 1. Lunar Hero Details
        binding.tvMoonPhaseTitle.text = AppLocalizer.moonPhaseName(day.moonPhaseKey, language)
        binding.tvMoonIllumination.text = String.format(Locale.US, "%.0f%% Illumination", day.illuminationFraction * 100)

        binding.tvSolarNoon.text = AppLocalizer.localizedTime(day.solarNoon, language)
        binding.tvSkySunrise.text = AppLocalizer.localizedTime(day.sunrise, language)
        binding.tvMoonrise.text = day.moonrise?.let { AppLocalizer.localizedTime(it, language) } ?: "--:--"
        binding.tvMoonset.text = day.moonset?.let { AppLocalizer.localizedTime(it, language) } ?: "--:--"

        // 2. Auspicious timing windows
        binding.auspiciousWindowsContainer.removeAllViews()
        bindWindowsToContainer(day.auspiciousWindows, binding.auspiciousWindowsContainer, R.color.choghadiya_amrit)

        // 3. Inauspicious timing windows
        binding.inauspiciousWindowsContainer.removeAllViews()
        bindWindowsToContainer(day.inauspiciousWindows, binding.inauspiciousWindowsContainer, R.color.choghadiya_rog)
    }

    private fun bindWindowsToContainer(windows: List<TimingWindow>, container: LinearLayout, colorRes: Int) {
        val language = appState.selectedLanguage
        val colorInt = ContextCompat.getColor(requireContext(), colorRes)

        if (windows.isEmpty()) {
            val emptyTv = TextView(requireContext()).apply {
                text = "None active for today."
                setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_text_muted))
                textSize = 13f
                setPadding(12, 12, 12, 12)
            }
            container.addView(emptyTv)
            return
        }

        for (window in windows) {
            val windowRow = LayoutInflater.from(requireContext()).inflate(R.layout.item_timeline_slot, container, false)
            val tvSlotName = windowRow.findViewById<TextView>(R.id.tv_slot_name)
            val tvSlotBadge = windowRow.findViewById<TextView>(R.id.tv_slot_badge)
            val tvSlotTimeRange = windowRow.findViewById<TextView>(R.id.tv_slot_time_range)
            val viewColorDot = windowRow.findViewById<View>(R.id.view_slot_color_dot)

            tvSlotName.text = AppLocalizer.astronomyWindowTitle(window.key, language)
            tvSlotName.setTextColor(colorInt)
            viewColorDot.backgroundTintList = ColorStateList.valueOf(colorInt)

            tvSlotTimeRange.text = "${AppLocalizer.localizedTime(window.startTime, language)} - ${AppLocalizer.localizedTime(window.endTime, language)}"

            tvSlotBadge.visibility = View.VISIBLE
            tvSlotBadge.text = window.source.uppercase(Locale.US)
            tvSlotBadge.setBackgroundResource(R.drawable.bg_capsule_inactive)
            tvSlotBadge.setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_outline))

            container.addView(windowRow)
        }
    }
}
