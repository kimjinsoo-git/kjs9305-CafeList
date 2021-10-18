package com.example.macaronagaintoay.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.macaronagaintoay.entity.CafeEntity

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