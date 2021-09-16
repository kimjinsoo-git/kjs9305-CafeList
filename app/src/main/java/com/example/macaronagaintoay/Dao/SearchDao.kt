package com.example.macaronagaintoay.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.macaronagaintoay.Entity.SearchEntity


@Dao
interface SearchDao {

    @Query("SELECT * FROM search_table ORDER BY id DESC")
    fun getAlphabetizedSearch(): LiveData<List<SearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchEntity: SearchEntity)

    @Query("DELETE FROM search_table")
    suspend fun deleteAll()


    @Delete
    fun deletes(searchEntity: SearchEntity)

}