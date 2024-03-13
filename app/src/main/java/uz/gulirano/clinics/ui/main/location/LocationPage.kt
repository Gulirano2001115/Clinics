package uz.gulirano.clinics.ui.main.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentSender
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import uz.gulirano.clinics.R
import uz.gulirano.clinics.core.base.BaseFragment
import uz.gulirano.clinics.core.model.ClinicsLocationModel
import uz.gulirano.clinics.core.util.getAllLocation
import uz.gulirano.clinics.databinding.PageLocationBinding
import uz.gulirano.clinics.ui.dialog.ClinicDialog
import uz.gulirano.clinics.ui.dialog.OpenGoogleMap


class LocationPage : BaseFragment(R.layout.page_location) {

    private val binding by viewBinding(PageLocationBinding::bind)
    private var myMap: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission", "PotentialBehaviorOverride")
    override fun onViewCreated() {

        checkPermissionOrStart()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.googleMapMy) as? SupportMapFragment
        mapFragment?.getMapAsync { map ->
            myMap = map
            map.isMyLocationEnabled = true

            for (location in getAllLocation()) {

                val marker = MarkerOptions()
                    .title(location.name)
                    .position(LatLng(location.latitude, location.longitude))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_location_hospital))

                map.addMarker(marker)

                map.setOnMarkerClickListener(GoogleMap.OnMarkerClickListener { marker ->

                    lateinit var model: ClinicsLocationModel
                    for (i in getAllLocation()) {
                        if (i.name == marker.title) {
                            model = i
                            break
                        }
                    }

                    val dialog = ClinicDialog(requireContext(), model)
                    dialog.serGoogleClick(object : OpenGoogleMap {
                        override fun openGoogleNavigator(clinicsLocationModel: ClinicsLocationModel) {

                            goToGoogleMap(clinicsLocationModel)

                        }

                    })
                    dialog.show()

                    return@OnMarkerClickListener true
                })

            }

        }

        if (!checkGPSEnabled()) {
            turnOnGPS()
        }

        binding.getCurrentLcoation.setOnClickListener {
            getDeviceLocation()
        }
        getDeviceLocation()


    }

    private fun goToGoogleMap(m: ClinicsLocationModel) {

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("google.navigation:q=${m.latitude},${m.longitude}&mode=d")
        )
        intent.setPackage("com.google.android.apps.maps")
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }

    }

    private fun turnOnGPS() {
        val request = LocationRequest.create().apply {
            interval = 2000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder().addLocationRequest(request)
        val client = LocationServices.getSettingsClient(requireActivity())
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    exception.startResolutionForResult(requireActivity(), 12345)
                } catch (e: IntentSender.SendIntentException) {
                    // Handle exception
                }
            }
        }.addOnSuccessListener {
            // Handle success
        }
    }

    private fun checkGPSEnabled(): Boolean {
        val locationManager =
            context?.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        return !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }


    @SuppressLint("MissingPermission")
    private fun getDeviceLocation() {
        try {
            val locationTask: Task<Location> = fusedLocationClient.lastLocation


            locationTask.addOnCompleteListener(requireActivity()) { task ->

                if (task.isSuccessful) {

                    myMap?.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            LatLng(
                                task.result.latitude,
                                task.result.longitude,
                            ), 13F
                        )
                    )
                } else {
                    myMap?.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            LatLng(37.4225711, -122.0649872),
                            13f
                        )
                    )
                    myMap?.uiSettings?.isMyLocationButtonEnabled = false
                }
            }
        } catch (e: SecurityException) {
            Log.i("TAGaaaa", "Location not found")
        }
    }


    private fun checkPermissionOrStart() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                )
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
            }

            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
            }

            else -> {
                checkPermissionOrStart()
            }
        }
    }

}