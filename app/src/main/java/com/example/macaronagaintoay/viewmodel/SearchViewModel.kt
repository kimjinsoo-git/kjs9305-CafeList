package com.example.macaronagaintoay.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.macaronagaintoay.Database.AppDatabase
import com.example.macaronagaintoay.Database.Repository
import com.example.macaronagaintoay.entity.SearchEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchViewModel(application: Application) : AndroidViewModel(application) {

    val search : MutableLiveData<String> = MutableLiveData<String>()

    val repository : Repository = Repository(AppDatabase.getDatabase(application,viewModelScope))

    var allSearch : LiveData<List<SearchEntity>> = repository.allSearch

    val text : LiveData<String>
        get() = search


    fun setVisible(view: View, isvisible : Boolean){

        view.visibility = if (isvisible) View.VISIBLE else View.GONE


    }

    fun insert(searchEntity: SearchEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(searchEntity)
    }

//    fun delete(searchEntity: SearchEntity) = viewModelScope.launch(Dispatchers.IO) {
//
//        repository.
//
//    }
//

    fun deletes(searchEntity: SearchEntity){
        repository.deletes(searchEntity)
    }

}