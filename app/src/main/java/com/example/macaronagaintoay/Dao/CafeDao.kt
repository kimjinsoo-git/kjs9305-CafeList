package com.example.macaronagaintoay.Dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.macaronagaintoay.Entity.CafeEntity

@Dao
interface CafeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)  //PrimaryKey가 겹치지않게 자동 올라가게함
    suspend fun insert(CafeEntity : CafeEntity)

    @Query("DELETE FROM cafe_table")
    fun deleteAll()

    @Query("SELECT * FROM cafe_table")
    fun getAll() : LiveData<List<CafeEntity>>

    @Query("SELECT * FROM cafe_table")
    fun getCafeList() : List<CafeEntity>



}