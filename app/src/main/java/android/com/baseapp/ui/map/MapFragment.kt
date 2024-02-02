package android.com.baseapp.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.com.baseapp.R
import android.com.baseapp.databinding.FragmentMapBinding
import android.com.baseapp.ui.base.BaseFragment
import android.com.baseapp.utils.hasPermission
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>(), OnMapReadyCallback {

    override val resourceLayoutId = R.layout.fragment_map

    lateinit var mMap: GoogleMap

    val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            // Handle permission results here
            var isAllGranted = true
            permissions.entries.forEach {
                val permissionName = it.key
                val isGranted = it.value
                isAllGranted = isAllGranted && isGranted
                // Process granted or denied permissions

            }
            if (isAllGranted) {
                getAndSetCurrentLocation()
            }
        }

    lateinit var localtionListener: LocationListener

    @SuppressLint("MissingPermission")
    private fun getAndSetCurrentLocation() {
        val locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager

        localtionListener = LocationListener { location ->
            val currentLocation = LatLng(location.latitude, location.longitude)
            mMap.addMarker(MarkerOptions().position(currentLocation).title("Marker"))
            // Showing the current location in Google Map
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation))
            // Zoom in the Google Map
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15f))

            locationManager.removeUpdates(localtionListener)
        }
        val provider =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) LocationManager.FUSED_PROVIDER else LocationManager.NETWORK_PROVIDER
        locationManager.requestLocationUpdates(provider, 10 * 1000, 0f, localtionListener)
    }

    override fun onInitView(root: View?) {
        binding.mapView.onCreate(null)
        binding.mapView.getMapAsync(this)
    }

    override fun subscribeUi(viewModel: MapViewModel) {

    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map

        mMap.setOnMapLongClickListener { latlng ->
            mMap.addMarker(MarkerOptions().position(latlng).title("Marker"))
        }

        val locationPermissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (!requireActivity().hasPermission(locationPermissions)) {
            requestPermissionLauncher.launch(locationPermissions)
        } else {
            getAndSetCurrentLocation()
        }
    }
}