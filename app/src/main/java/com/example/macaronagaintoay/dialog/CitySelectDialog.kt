package com.example.macaronagaintoay.dialog

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.macaronagaintoay.Adapter.BigCityListAdapter
import com.example.macaronagaintoay.Adapter.CityListAdapter
import com.example.macaronagaintoay.R
import com.example.macaronagaintoay.databinding.DialogCitySelectBinding
import com.example.macaronagaintoay.dataclass.Mainlatlng
import com.example.macaronagaintoay.viewmodel.MainViewModel
import java.util.*

class CitySelectDialog(context : Context) : Dialog(context) {

    private lateinit var listener : CustomDialogListener

    private lateinit var seoulBtn : Button
    private lateinit var busanBtn : Button
    private lateinit var incheonBtn : Button
    private lateinit var daeguBtn : Button
    private lateinit var daejeonBtn : Button
    private lateinit var gwangjuBtn : Button
    private lateinit var ulsanBtn : Button

    private lateinit var bigCityRv : RecyclerView
    private lateinit var CityRv : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_city_select)

        Objects.requireNonNull(window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        init()

        seoulBtn.setOnClickListener {
            var latlng = Mainlatlng()
            latlng.address = "서울"
            latlng.lat = 37.5665734
            latlng.lng = 126.978179
            listener.onClick(latlng)
            dismiss()
        }

        busanBtn.setOnClickListener {
            var latlng = Mainlatlng()
            latlng.address = "부산"
            latlng.lat = 35.1801639
            latlng.lng = 129.074660
            listener.onClick(latlng)
            dismiss()
        }

        incheonBtn.setOnClickListener {
            var latlng = Mainlatlng()
            latlng.address = "인천"
            latlng.lat = 37.4558168
            latlng.lng = 126.706270
            listener.onClick(latlng)
            dismiss()
        }

        daeguBtn.setOnClickListener {
            var latlng = Mainlatlng()
            latlng.address = "대구"
            latlng.lat = 35.8715288
            latlng.lng = 128.601501
            listener.onClick(latlng)
            dismiss()
        }

        daejeonBtn.setOnClickListener {
            var latlng = Mainlatlng()
            latlng.address = "대전"
            latlng.lat = 36.3501622
            latlng.lng = 127.385712
            listener.onClick(latlng)
            dismiss()
        }

        gwangjuBtn.setOnClickListener {
            var latlng = Mainlatlng()
            latlng.address = "광주"
            latlng.lat = 35.1597648
            latlng.lng = 126.851544
            listener.onClick(latlng)
            dismiss()
        }

        ulsanBtn.setOnClickListener {
            var latlng = Mainlatlng()
            latlng.address = "울산"
            latlng.lat = 35.5389027
            latlng.lng = 129.311552
            listener.onClick(latlng)
            dismiss()
        }

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        bigCityRv.setHasFixedSize(true)
        bigCityRv.layoutManager = layoutManager


        val layoutManagers = LinearLayoutManager(context)
        layoutManagers.orientation = LinearLayoutManager.VERTICAL
        CityRv.setHasFixedSize(true)
        CityRv.layoutManager = layoutManagers


        var city : List<String> = listOf("서울", "부산", "인천", "대구", "대전", "광주", "울산")
        var adapter = BigCityListAdapter()
        adapter.BigCityListAdapter(context, city, CityRv)

        bigCityRv.adapter = adapter


    }

    fun init(){

        seoulBtn = findViewById(R.id.seoul_btn)
        busanBtn = findViewById(R.id.busan_btn)
        incheonBtn = findViewById(R.id.incheon_btn)
        daeguBtn = findViewById(R.id.daegu_btn)
        daejeonBtn = findViewById(R.id.daejeon_btn)
        gwangjuBtn = findViewById(R.id.gwangju_btn)
        ulsanBtn = findViewById(R.id.ulsan_btn)

        bigCityRv = findViewById(R.id.big_city_rv)
        CityRv = findViewById(R.id.city_rv)

    }

    fun setListener(listener : CustomDialogListener){
        this.listener = listener
    }

    interface CustomDialogListener{

        fun onClick(lanlng : Mainlatlng)

    }





}