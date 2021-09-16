package com.example.macaronagaintoay.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

import com.example.macaronagaintoay.Activity.AdvertisionActivity
import com.example.macaronagaintoay.Activity.CafeDetailActivity
import com.example.macaronagaintoay.Activity.ListHomeCafeActivity
import com.example.macaronagaintoay.R
import com.example.macaronagaintoay.databinding.FragmentMainBinding
import com.example.macaronagaintoay.dataclass.GpsTracker
import com.example.macaronagaintoay.dataclass.Mainlatlng
import com.example.macaronagaintoay.dialog.CitySelectDialog
import com.example.macaronagaintoay.viewmodel.MainViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.MapFragment
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource


class MainFragment : Fragment(), OnMapReadyCallback {

    lateinit var binding : FragmentMainBinding
    private lateinit var viewModel : MainViewModel

    private var gpsTracker: GpsTracker? = null

    private lateinit var locationSource : FusedLocationSource
    private var naverMap: NaverMap? = null

    var mainlatlng = Mainlatlng()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(inflater, R.layout.fragment_main, container, false)

        activity?.let {

            viewModel = ViewModelProvider(it).get(MainViewModel::class.java)
            binding.mainViewModel = viewModel
            binding.lifecycleOwner = this

        }

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        val fm = childFragmentManager

        val mapFragment = fm.findFragmentById(R.id.main_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.main_map, it).commit()
            }

        mapFragment.getMapAsync(this)





        binding.naviView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            binding.mainDrawerLayout.closeDrawers()
            val id = menuItem.itemId
            val title = menuItem.title.toString()
            if (id == R.id.account) {
                var intent = Intent(context, AdvertisionActivity::class.java)
                startActivity(intent)
            } else if (id == R.id.event) {
                var intent = Intent(context, CafeDetailActivity::class.java)
                startActivity(intent)
            } else if (id == R.id.homecafe) {
                var intent = Intent(context, ListHomeCafeActivity::class.java)
                startActivity(intent)
            } else if (id == R.id.logout) {
                Toast.makeText(context, "$title: 로그아웃 시도중", Toast.LENGTH_SHORT).show()
            }
            true
        }


        binding.hotmenuBtn.setOnClickListener {
            binding.mainDrawerLayout.openDrawer(GravityCompat.START)
        }

        binding.listStrBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_fragment_main_to_listCafeFragment)

        }

        binding.mainCityLayout.setOnClickListener {

            var dialog = CitySelectDialog(requireContext())
            dialog.setListener(object : CitySelectDialog.CustomDialogListener{
                override fun onClick(lanlng: Mainlatlng) {

                    binding.mainViewModel?.updateLatLng(lanlng)

                    Log.d("값이뭐고", binding.mainViewModel?.latlng!!.value!!.lng.toString() + " / " +binding.mainViewModel?.latlng!!.value!!.lng)



                }
            })
            dialog.show()

        }

        return binding.root
    }

    override fun onMapReady(p0: NaverMap) {
        this.naverMap = p0



        val marker = Marker()

        binding.mainViewModel!!.allCafe.observe(this, Observer {
            for(i : Int in 0..(it.size-1)) {

                Log.d("latlng1", it[i].lat + "/" +it[i].lng)

                marker.position = LatLng(it[i].lat.toDouble(), it[i].lng.toDouble())
                marker.map = naverMap
            }
        })

        binding.mainViewModel?.latlng!!.observe(requireActivity(), Observer {
            Log.d("latlng", it.lat.toString() + "/" + it.lng.toString())
            val cameraUpdate = CameraUpdate.scrollAndZoomTo(
                LatLng(it.lat, it.lng), 15.0
            ).animate(CameraAnimation.Fly, 3000)
            naverMap!!.moveCamera(cameraUpdate)
        })



        initLocation()
    }

    public fun initLocation(){

        naverMap!!.locationSource = locationSource
        naverMap!!.locationTrackingMode = LocationTrackingMode.Follow


        gpsTracker = GpsTracker(requireContext())

        val latitude = gpsTracker!!.latitude
        val longitude = gpsTracker!!.longitude

        mainlatlng.address = "내위치"
        mainlatlng.lat = latitude
        mainlatlng.lng = longitude
        Log.d("여기다", mainlatlng.lat.toString()+"/"+mainlatlng.lng.toString())
        //latitude = userLocation.latitude
        //longitude = userLocation.longitude

        binding.mainViewModel?.updateLatLng(mainlatlng)



        Toast.makeText(requireContext(), "위도 + " + binding.mainViewModel!!.latlng.value!!.lat + "경도 : " + binding.mainViewModel!!.latlng.value!!.lng, Toast.LENGTH_SHORT).show()
    }





    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }


}