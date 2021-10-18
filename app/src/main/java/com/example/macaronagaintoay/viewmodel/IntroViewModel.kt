package com.example.macaronagaintoay.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.macaronagaintoay.Database.AppDatabase
import com.example.macaronagaintoay.Database.Repository
import com.example.macaronagaintoay.entity.DBVersionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IntroViewModel(application: Application) : AndroidViewModel(application) {

    val repository : Repository = Repository(AppDatabase.getDatabase(application,viewModelScope))

    var LastDbVersion : LiveData<DBVersionEntity> = repository.LastDbVersion

    var vmDBstate : LiveData<Boolean> = repository.DBversionState



    fun insert(DbVersion : DBVersionEntity) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("asdasd", "insert")
        repository.DBVersionInsert(DbVersion)
    }

    fun delete()=viewModelScope.launch(Dispatchers.IO) {
        Log.d("asdasd", "delete")
        repository.DBversionDelete()
    }


    fun getDBversion(version : Int)=viewModelScope.launch(Dispatchers.IO){
        Log.d("asdasd", "getDBversion")
        repository.getDBversion(version)

    }

    fun getDBversion()=viewModelScope.launch(Dispatchers.IO){
        Log.d("asdasd", "getDBversion")
        repository.getDBversion()

    }

}

