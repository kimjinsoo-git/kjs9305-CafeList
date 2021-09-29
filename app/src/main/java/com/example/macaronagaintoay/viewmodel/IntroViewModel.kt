package com.example.macaronagaintoay.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.macaronagaintoay.Api.PostApi
import com.example.macaronagaintoay.Database.AppDatabase
import com.example.macaronagaintoay.Database.Repository
import com.example.macaronagaintoay.Entity.DBVersionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.macaronagaintoay.Api.Retrofit
import com.example.macaronagaintoay.dataclass.MyDBversion
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IntroViewModel(application: Application) : AndroidViewModel(application) {

    private var viewModelJop = Job()

    val repository : Repository = Repository(AppDatabase.getDatabase(application,viewModelScope))

    var LastDbVersion : LiveData<DBVersionEntity> = repository.LastDbVersion

    var vmDBversion : Int? = null

    var vmDBstate : LiveData<Boolean> = repository.DBversionState



    fun insert(DbVersion : DBVersionEntity) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("asdasd", "insert")
        repository.DBVersionInsert(DbVersion)
    }

    fun delete()=viewModelScope.launch(Dispatchers.IO) {
        Log.d("asdasd", "delete")
        repository.DBversionDelete()
    }

    fun update()=viewModelScope.launch(Dispatchers.IO) {

    }

    fun getDBversion(version : Int)=viewModelScope.launch(Dispatchers.IO){
        Log.d("asdasd", "getDBversion")
        repository.getDBversion(version)

    }

    fun getDBversion()=viewModelScope.launch(Dispatchers.IO){
        Log.d("asdasd", "getDBversion")
        repository.getDBversion()

    }



    suspend fun getdb() : Int{
        return vmDBversion!!
    }




}

