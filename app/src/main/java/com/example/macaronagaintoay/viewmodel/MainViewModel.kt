package com.example.macaronagaintoay.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.macaronagaintoay.Api.PostApi
import com.example.macaronagaintoay.Api.Retrofit
import com.example.macaronagaintoay.Database.AppDatabase
import com.example.macaronagaintoay.Database.Repository
import com.example.macaronagaintoay.entity.CafeEntity
import com.example.macaronagaintoay.dataclass.Mainlatlng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application)  {

    public var latlng : MutableLiveData<Mainlatlng> = MutableLiveData<Mainlatlng>()

    val repository : Repository = Repository(AppDatabase.getDatabase(application,viewModelScope))

    var allCafe : LiveData<List<CafeEntity>> = repository.allCafe
//    var CafeList : List<CafeEntity> = repository.CafeList

    var retrofit = Retrofit().getClient()
    var _getCafelist = retrofit!!.create(PostApi::class.java)
    val getCafelist
                get()=_getCafelist


    fun updateLatLng(mainlatlng: Mainlatlng){
        this.latlng.value = mainlatlng

    }

    fun insert(cafeDao : CafeEntity) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("MVModel", "마커등록")
        repository.CafeInsert(cafeDao)
    }

    fun delete() = viewModelScope.launch(Dispatchers.IO){
        Log.d("MVModel", "마커초기화")
        repository.cafeDeleteAll()
    }




}