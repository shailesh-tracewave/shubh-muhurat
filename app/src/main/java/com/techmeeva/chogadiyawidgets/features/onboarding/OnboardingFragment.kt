package com.techmeeva.chogadiyawidgets.features.onboarding

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.techmeeva.chogadiyawidgets.R
import com.techmeeva.chogadiyawidgets.core.state.AppState
import com.techmeeva.chogadiyawidgets.databinding.FragmentOnboardingBinding
import com.techmeeva.chogadiyawidgets.models.LocationMode
import com.techmeeva.chogadiyawidgets.models.SeedCity

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private lateinit var appState: AppState

    private var locationPageViewHolder: RecyclerView.ViewHolder? = null

    private val requestLocationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarseGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
        if (fineGranted || coarseGranted) {
            detectLocation()
        } else {
            Toast.makeText(requireContext(), "Permission denied. Please select a city manually.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appState = AppState(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = OnboardingPagerAdapter()
        binding.viewPager.adapter = adapter
        // Disable swipe to force using action buttons
        binding.viewPager.isUserInputEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun detectLocation() {
        try {
            val locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (!hasGps && !hasNetwork) {
                Toast.makeText(requireContext(), "Location services are disabled.", Toast.LENGTH_SHORT).show()
                return
            }

            var bestLocation: Location? = null
            if (hasGps) {
                val loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (loc != null) {
                    bestLocation = loc
                }
            }
            if (hasNetwork) {
                val loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if (loc != null && (bestLocation == null || loc.accuracy < bestLocation.accuracy)) {
                    bestLocation = loc
                }
            }

            if (bestLocation != null) {
                val matchedCity = matchLocationToCity(bestLocation.latitude, bestLocation.longitude)
                appState.applyCurrentLocation(matchedCity)
                updateLocationPageVisuals()
            } else {
                val provider = if (hasGps) LocationManager.GPS_PROVIDER else LocationManager.NETWORK_PROVIDER
                locationManager.requestSingleUpdate(provider, object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        if (isAdded) {
                            val matchedCity = matchLocationToCity(location.latitude, location.longitude)
                            appState.applyCurrentLocation(matchedCity)
                            updateLocationPageVisuals()
                        }
                    }
                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
                    override fun onProviderEnabled(provider: String) {}
                    override fun onProviderDisabled(provider: String) {}
                }, null)
            }
        } catch (e: SecurityException) {
            Toast.makeText(requireContext(), "Location permission required.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun matchLocationToCity(lat: Double, lng: Double): SeedCity {
        var closestCity = appState.availableCities.firstOrNull() ?: SeedCity.ahmedabadFallback
        var minDistance = Double.MAX_VALUE

        for (city in appState.availableCities) {
            val dist = calculateDistance(lat, lng, city.lat, city.lng)
            if (dist < minDistance) {
                minDistance = dist
                closestCity = city
            }
        }

        return SeedCity(
            name = "Current Location",
            city = "Current Location",
            state = closestCity.state,
            country = closestCity.country,
            lat = lat,
            lng = lng,
            timezone = closestCity.timezone
        )
    }

    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val r = 6371 // Earth radius in km
        val latDistance = Math.toRadians(lat2 - lat1)
        val lonDistance = Math.toRadians(lon2 - lon1)
        val a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return r * c
    }

    private fun updateLocationPageVisuals() {
        val holder = locationPageViewHolder ?: return
        val view = holder.itemView

        val btnGps = view.findViewById<LinearLayout>(R.id.btn_gps)
        val ivGpsIcon = view.findViewById<ImageView>(R.id.iv_gps_icon)
        val tvGpsTitle = view.findViewById<TextView>(R.id.tv_gps_title)
        val tvGpsSubtitle = view.findViewById<TextView>(R.id.tv_gps_subtitle)
        val citiesList = view.findViewById<LinearLayout>(R.id.cities_list)

        val isCurrentMode = appState.locationMode == LocationMode.CURRENT

        if (isCurrentMode) {
            btnGps.setBackgroundResource(R.drawable.bg_gold_button)
            ivGpsIcon.imageTintList = ContextCompat.getColorStateList(requireContext(), R.color.celestial_white)
            tvGpsTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_white))
            tvGpsSubtitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_surface_glass))
        } else {
            btnGps.setBackgroundResource(R.drawable.bg_city_row)
            ivGpsIcon.imageTintList = ContextCompat.getColorStateList(requireContext(), R.color.celestial_primary)
            tvGpsTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_text))
            tvGpsSubtitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.celestial_text_muted))
        }

        // Refresh manual cities highlights
        for (i in 0 until citiesList.childCount) {
            val cityRow = citiesList.getChildAt(i)
            val city = appState.availableCities[i]
            val ivCityCheck = cityRow.findViewById<ImageView>(R.id.iv_city_check)

            val isSelected = !isCurrentMode && appState.selectedCity.id == city.id
            if (isSelected) {
                cityRow.setBackgroundResource(R.drawable.bg_city_row_selected)
                ivCityCheck.visibility = View.VISIBLE
            } else {
                cityRow.setBackgroundResource(R.drawable.bg_city_row)
                ivCityCheck.visibility = View.GONE
            }
        }
    }

    private inner class OnboardingPagerAdapter : RecyclerView.Adapter<OnboardingPagerAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layoutRes = when (viewType) {
                0 -> R.layout.page_welcome
                1 -> R.layout.page_timeline
                else -> R.layout.page_location
            }
            val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
            return ViewHolder(view)
        }

        override fun getItemViewType(position: Int): Int = position

        override fun getItemCount(): Int = 3

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            when (position) {
                0 -> {
                    holder.itemView.findViewById<Button>(R.id.btn_continue).setOnClickListener {
                        binding.viewPager.currentItem = 1
                    }
                }
                1 -> {
                    holder.itemView.findViewById<Button>(R.id.btn_continue_timeline).setOnClickListener {
                        binding.viewPager.currentItem = 2
                    }
                }
                2 -> {
                    locationPageViewHolder = holder
                    
                    val btnGps = holder.itemView.findViewById<LinearLayout>(R.id.btn_gps)
                    val citiesList = holder.itemView.findViewById<LinearLayout>(R.id.cities_list)
                    val btnStartExploring = holder.itemView.findViewById<Button>(R.id.btn_start_exploring)

                    btnGps.setOnClickListener {
                        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            detectLocation()
                        } else {
                            requestLocationPermissionLauncher.launch(
                                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                            )
                        }
                    }

                    // Populate cities list
                    citiesList.removeAllViews()
                    for (city in appState.availableCities) {
                        val cityRow = LayoutInflater.from(requireContext()).inflate(R.layout.item_city, citiesList, false)
                        val tvCityName = cityRow.findViewById<TextView>(R.id.tv_city_name)
                        val tvCityDetails = cityRow.findViewById<TextView>(R.id.tv_city_details)

                        tvCityName.text = city.city
                        tvCityDetails.text = "${city.state}, ${city.country}"

                        cityRow.setOnClickListener {
                            appState.selectCity(city)
                            updateLocationPageVisuals()
                        }
                        citiesList.addView(cityRow)
                    }

                    btnStartExploring.setOnClickListener {
                        appState.hasCompletedOnboarding = true
                        findNavController().navigate(R.id.action_onboarding_to_home)
                    }

                    updateLocationPageVisuals()
                }
            }
        }
    }
}
