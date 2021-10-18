package com.example.macaronagaintoay.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "search_table")
data class SearchEntity(

        @PrimaryKey(autoGenerate = true)
        val id: Int?,

        @ColumnInfo(name="search")
        var search : String


)