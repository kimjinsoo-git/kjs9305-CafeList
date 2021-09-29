package com.example.macaronagaintoay.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.macaronagaintoay.Entity.CafeEntity
import com.example.macaronagaintoay.Entity.DBVersionEntity

@Dao
interface DBVersionDao {

    @Query("SELECT * FROM version_table")
    fun getVersion() : LiveData<DBVersionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)  //PrimaryKey가 겹치지않게 자동 올라가게함
    fun insert(DBVersionEntity: DBVersionEntity?)

    @Query("DELETE FROM version_table")
    suspend fun deleteAll()

    @Query("UPDATE version_table SET id=:newVersion WHERE id=:version ")
    fun dbversionupdate(version : Int, newVersion : Int)
}