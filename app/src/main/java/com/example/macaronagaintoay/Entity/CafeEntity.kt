package com.example.macaronagaintoay.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cafe_table")
data class CafeEntity (

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "cafeName")
    var cafeName : String,

    @ColumnInfo(name = "cafeType")
    var cafeType : String,

    @ColumnInfo(name = "NewAddress")
    var NewAddress : String,

    @ColumnInfo(name = "OpeningHours")
    var OpeningHours : String,

    @ColumnInfo(name = "StoreNumber")
    var StoreNumber : String,

    @ColumnInfo(name = "MidAddress")
    var MidAddress : String,

    @ColumnInfo(name = "OldAddress")
    var OldAddress : String,

    @ColumnInfo(name = "lat")
    var lat : String,

    @ColumnInfo(name = "lng")
    var lng : String,

    )







