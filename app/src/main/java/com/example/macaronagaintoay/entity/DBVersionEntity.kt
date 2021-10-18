package com.example.macaronagaintoay.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "version_table")
class DBVersionEntity (

    @PrimaryKey
    @ColumnInfo(name = "ids")
    var ids : Int?,

    @ColumnInfo(name = "id")
    var versions: Int?

)