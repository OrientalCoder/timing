package com.dsw.timing.network

import com.dsw.timing.bean.Student
import retrofit2.Call
import retrofit2.http.GET

interface NetworkService {

    @GET("/students")
    fun getStudentInfo() : Call<List<Student>>
}