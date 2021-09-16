package com.example.macaronagaintoay.dataclass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AllCafelist(


    @SerializedName("id")
    @Expose
    var id : Int? = null,

    @SerializedName("cafeName")
    @Expose
    var cafeName : String? = null,

    @SerializedName("category")
    @Expose
    var category : String? = null,

    @SerializedName("newAddress")
    @Expose
    var newAddress : String? = null,

    @SerializedName("openTime")
    @Expose
    var openTime : String? = null,

    @SerializedName("telNumber")
    @Expose
    var telNumber : String? = null,

    @SerializedName("city")
    @Expose
    var city : String? = null,

    @SerializedName("oldAddress")
    @Expose
    var oldAddress : String? = null,

    @SerializedName("lat")
    @Expose
    var lat : Double? = null,

    @SerializedName("lng")
    @Expose
    var lng : Double? = null

){
    override fun toString(): String {
        return ("cafelist[id = " + id + ", cafeName = " + cafeName +", category = " + category + ", newAddress = " + newAddress
         + ", openTime = " + openTime + ", telNumber = " + telNumber + ", city = " + city + ", oldAddress = " + lat + ", lat = "
         + ", lng = " + lng + "]")
    }
}



class MyAllCafeList{
    @SerializedName("cafelist")
    private lateinit var CafeList : List<AllCafelist>

    public fun getCafeList() : List<AllCafelist>{
        return CafeList
    }
}
