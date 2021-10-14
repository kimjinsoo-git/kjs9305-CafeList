package com.example.macaronagaintoay.Activity

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.macaronagaintoay.Fragment.MainFragment
import com.example.macaronagaintoay.R
import com.example.macaronagaintoay.databinding.ActivityMainBinding
import com.example.macaronagaintoay.dataclass.GpsTracker
import com.example.macaronagaintoay.dataclass.Mainlatlng
import com.example.macaronagaintoay.dialog.CitySelectDialog
import com.example.macaronagaintoay.viewmodel.MainViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import java.util.*
import com.naver.maps.map.CameraPosition

import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.OverlayImage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception


class MainActivity : AppCompatActivity(), OnMapReadyCallback{
    lateinit var binding : ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private var gpsTracker: GpsTracker? = null

    private var naverMap: NaverMap? = null

    private lateinit var locationSource : FusedLocationSource

    var mainlatlng = Mainlatlng()
    private var markersPosition: Vector<LatLng>? = null
    private var activeMarkers: Vector<Marker>? = null

    private val GPS_ENABLE_REQUEST_CODE = 2001
    private val PERMISSIONS_REQUEST_CODE = 100
    var REQUIRED_PERMISSIONS = arrayOf<String>(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this



        if (checkLocationServicesStatus()) {
            checkRunTimePermission();
        } else {
            showDialogForLocationServiceSetting();
        }


        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        val fm = supportFragmentManager

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
                var intent = Intent(this, AdvertisionActivity::class.java)
                startActivity(intent)
            } else if (id == R.id.event) {
                var intent = Intent(this, CafeDetailActivity::class.java)
                startActivity(intent)
            } else if (id == R.id.homecafe) {
                var intent = Intent(this, ListCafeActivity::class.java)
                startActivity(intent)
            } else if (id == R.id.logout) {
                Toast.makeText(this, "$title: 로그아웃 시도중", Toast.LENGTH_SHORT).show()
            }
            true
        }


        binding.hotmenuBtn.setOnClickListener {
            binding.mainDrawerLayout.openDrawer(GravityCompat.START)
        }

        binding.listStrBtn.setOnClickListener {
            var intent = Intent(this, ListCafeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }

        binding.mainCityLayout.setOnClickListener {

            var dialog = CitySelectDialog(this)
            dialog.setListener(object : CitySelectDialog.CustomDialogListener{
                override fun onClick(lanlng: Mainlatlng) {

                    binding.viewModel?.updateLatLng(lanlng)

                    Log.d("값이뭐고", binding.viewModel?.latlng!!.value!!.lng.toString() + " / " +binding.viewModel?.latlng!!.value!!.lng)

                }
            })
            dialog.show()

        }




    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSIONS_REQUEST_CODE && grantResults.size == REQUIRED_PERMISSIONS.size) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면
            var check_result = true


            // 모든 퍼미션을 허용했는지 체크합니다.
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false
                    break
                }
            }
            if (check_result) {
                initLocation()
                //위치 값을 가져올 수 있음
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        REQUIRED_PERMISSIONS.get(0)
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        REQUIRED_PERMISSIONS.get(1)
                    )
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.",
                        Toast.LENGTH_LONG
                    ).show()
                    finish()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }






    fun checkRunTimePermission() {

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
            hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED
        ) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)
            if(naverMap != null){
                initLocation()
            }


            // 3.  위치 값을 가져올 수 있음
        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivity,
                    REQUIRED_PERMISSIONS[0]
                )
            ) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(this@MainActivity, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG)
                    .show()
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(
                    this@MainActivity, REQUIRED_PERMISSIONS,
                    PERMISSIONS_REQUEST_CODE
                )
            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(
                    this@MainActivity, REQUIRED_PERMISSIONS,
                    PERMISSIONS_REQUEST_CODE
                )
            }
        }
    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private fun showDialogForLocationServiceSetting() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("위치 서비스 비활성화")
        builder.setMessage(
            """
            앱을 사용하기 위해서는 위치 서비스가 필요합니다.
            위치 설정을 수정하실래요?
            """.trimIndent()
        )
        builder.setCancelable(true)
        builder.setPositiveButton("설정", DialogInterface.OnClickListener { dialog, id ->
            val callGPSSettingIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE)
        })
        builder.setNegativeButton("취소",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        builder.create().show()
    }

    fun checkLocationServicesStatus(): Boolean {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            GPS_ENABLE_REQUEST_CODE ->
                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {
                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음")
                        checkRunTimePermission()
                        return
                    }
                }
        }
    }




    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    override fun onMapReady(p0: NaverMap) {
        this.naverMap = p0

        naverMap!!.mapType = NaverMap.MapType.Navi

        initLocation()


        binding.viewModel?.latlng!!.observe(this, Observer {
            Log.d("latlng", it.lat.toString() + "/" + it.lng.toString())
            val cameraUpdate = CameraUpdate.scrollAndZoomTo(
                LatLng(it.lat, it.lng), 15.0
            ).animate(CameraAnimation.Fly, 3000)
            naverMap!!.moveCamera(cameraUpdate)
        })



        binding.viewModel!!.allCafe.observe(this, Observer {
            for(i : Int in it.indices) {

                Log.d("MVModel all", (it[i].lat.toDouble() + it[i].lng.toDouble()).toString())

                val marker = Marker()
                marker.position = LatLng(it[i].lat.toDouble(), it[i].lng.toDouble())
                marker.width = 100
                marker.height = 100
                marker.icon = OverlayImage.fromResource(R.drawable.coffee);
                marker.map = naverMap

            }
        })

//        GlobalScope.launch {
//            Log.d("cafelist", viewModel.CafeList.size.toString())
//        }




        naverMap!!.addOnCameraIdleListener {
            freeActiveMarkers()

            val currentPosition = getCurrentPosition(naverMap!!)
            try {
                Log.d("MVModel s", "sdd")
//                for (markerPosition in markersPosition!!) {
//                    if (!withinSightMarker(currentPosition!!, markerPosition!!)) continue
//                    val marker = Marker()
//                    marker.position = markerPosition!!
//                    marker.map = naverMap
//                    activeMarkers!!.add(marker)
//                }

            } catch (e: Exception) {
                Log.d("MVModel e", "ssdd")
                e.printStackTrace()
            }
        }


    }



    // 현재 카메라가 보고있는 위치
    fun getCurrentPosition(naverMap: NaverMap): LatLng? {
        val cameraPosition = naverMap.cameraPosition
        return LatLng(cameraPosition.target.latitude, cameraPosition.target.longitude)
    }

    // 선택한 마커의 위치가 가시거리(카메라가 보고있는 위치 반경 3km 내)에 있는지 확인
    val REFERANCE_LAT = 1 / 109.958489129649955
    val REFERANCE_LNG = 1 / 88.74
    val REFERANCE_LAT_X3 = 3 / 109.958489129649955
    val REFERANCE_LNG_X3 = 3 / 88.74

    fun withinSightMarker(currentPosition: LatLng, markerPosition: LatLng): Boolean {
        val withinSightMarkerLat =
            Math.abs(currentPosition.latitude - markerPosition.latitude) <= REFERANCE_LAT_X3
        val withinSightMarkerLng =
            Math.abs(currentPosition.longitude - markerPosition.longitude) <= REFERANCE_LNG_X3
        return withinSightMarkerLat && withinSightMarkerLng
    }

    // 지도상에 표시되고있는 마커들 지도에서 삭제
    private fun freeActiveMarkers() {
        if (activeMarkers == null) {
            activeMarkers = Vector()
            return
        }
        for (activeMarker in activeMarkers!!) {
            activeMarker.map = null
        }
        activeMarkers = Vector()
    }



    fun initLocation(){

        naverMap!!.locationSource = locationSource
        naverMap!!.locationTrackingMode = LocationTrackingMode.Follow

        gpsTracker = GpsTracker(this)

        val latitude = gpsTracker!!.latitude
        val longitude = gpsTracker!!.longitude

        mainlatlng.address = "내위치"
        mainlatlng.lat = latitude
        mainlatlng.lng = longitude
        Log.d("여기다", mainlatlng.lat.toString()+"/"+mainlatlng.lng.toString())
        //latitude = userLocation.latitude
        //longitude = userLocation.longitude

        binding.viewModel?.updateLatLng(mainlatlng)




        Toast.makeText(this, "위도 + " + binding.viewModel!!.latlng.value!!.lat + "경도 : " + binding.viewModel!!.latlng.value!!.lng, Toast.LENGTH_SHORT).show()
    }


}