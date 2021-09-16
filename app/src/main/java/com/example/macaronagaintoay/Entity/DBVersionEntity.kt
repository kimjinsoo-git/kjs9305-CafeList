package com.example.macaronagaintoay.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.naver.maps.map.NaverMap

@Entity(tableName = "version_table")
class DBVersionEntity (

    @PrimaryKey
    @ColumnInfo(name = "ids")
    var ids : Int?,

    @ColumnInfo(name = "id")
    var versions: Int?

)