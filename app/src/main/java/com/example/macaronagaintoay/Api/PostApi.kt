package com.example.macaronagaintoay.Api

import com.example.macaronagaintoay.dataclass.AllCafelist
import com.example.macaronagaintoay.dataclass.MyAllCafeList
import com.example.macaronagaintoay.dataclass.MyDBversion
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PostApi {

    @POST("cafelist/Allcafelist.php")
    fun getAllCafelist() : Call<MyAllCafeList>

    @POST("cafelist/getDBversion.php")
    fun getDBVersion() : Call<MyDBversion>



}