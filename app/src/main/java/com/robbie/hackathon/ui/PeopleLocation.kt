package com.robbie.hackathon.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.robbie.hackathon.R
import com.robbie.hackathon.databinding.FragmentPeopleLocationBinding


class PeopleLocation : Fragment() {

    private lateinit var binding : FragmentPeopleLocationBinding
    private lateinit var fuseLocationClient: FusedLocationProviderClient

    private var longitude = 0.00
    private var latitude = 0.00


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPeopleLocationBinding.inflate (layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getCurrentLocation()
        setClickListeners()
    }
    private fun setClickListeners() {
        binding.btnGetLocation.setOnClickListener {
            getCurrentLocation()
        }

        binding.btnOpenMaps.setOnClickListener {
            openMaps(longitude, latitude)
        }
    }

    private fun getCurrentLocation() {
        fuseLocationClient = LocationServices.getFusedLocationProviderClient(context)

        context?.let {
            if (ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionRequestLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
                return
            }
        }

        fuseLocationClient.lastLocation.addOnSuccessListener { location ->

            if(location != null) {
                longitude = location.longitude
                latitude = location.latitude

                binding.tvLatitudeLocation.text = getString(R.string.text_placeholder_format_latitude, latitude.toString())
                binding.tvLongitudeLocation.text = getString(R.string.text_placeholder_format_longitude, longitude.toString())
                binding.tvProviderLocation.text = getString(R.string.text_placeholder_format_provider, location.provider)
            }


        }.addOnFailureListener { exception ->
            Toast.makeText(requireContext(), "${exception.message}", Toast.LENGTH_SHORT).show()
        }
    }


    private val permissionRequestLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { grantResults ->
            if (grantResults.isNotEmpty() && grantResults[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                grantResults[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {
                getCurrentLocation()
            } else {
                Toast.makeText(
                    context,
                    getString(R.string.text_need_permission),
                    Toast.LENGTH_SHORT
                ).show()
            }}


    private fun openMaps(longitude: Double, latitude: Double) {
        val uri = Uri.parse("geo${latitude},${longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")

        activity?.packageManager?.let {
            mapIntent.resolveActivity(it)?.let {
                startActivity(mapIntent)
            }
        }

    }

}
