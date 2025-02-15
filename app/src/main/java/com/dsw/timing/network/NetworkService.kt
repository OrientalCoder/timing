package com.dsw.timing.network

import com.dsw.timing.bean.Location
import com.dsw.timing.bean.LocationKotlin
import com.dsw.timing.bean.Student
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkService {

    @GET("/students")
    fun getStudentInfo() : Call<List<Student>>

    @POST("/location/report")
    fun postLocation(@Body location: Location) : Call<ResponseBody>

    @GET("/lastLocation")
    fun getRecentLocation() : Call<Location>
}