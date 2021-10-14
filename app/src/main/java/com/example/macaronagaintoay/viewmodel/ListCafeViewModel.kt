package com.example.macaronagaintoay.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.macaronagaintoay.Database.AppDatabase
import com.example.macaronagaintoay.Database.Repository
import com.example.macaronagaintoay.Entity.CafeEntity

class ListCafeViewModel(application: Application) : AndroidViewModel(application) {

    val repository : Repository = Repository(AppDatabase.getDatabase(application, viewModelScope))
    val listCafe : LiveData<List<CafeEntity>> = repository.allCafe

}