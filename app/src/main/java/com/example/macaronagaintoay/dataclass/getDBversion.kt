package com.example.macaronagaintoay.dataclass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class getDBversion(
    @SerializedName("version")
    @Expose
    var version : Int? = null
){
    override fun toString(): String {
        return ("dbversion[version = " + version + "]")
    }
}


class MyDBversion{
    @SerializedName("dbversion")
    private lateinit var DBversion : List<getDBversion>

    public fun getDBversion() : List<getDBversion>{
        return DBversion
    }
}