package com.dsw.timing.network

import com.dsw.timing.bean.Student
import okhttp3.ResponseBody
import retrofit2.Converter

class StudentListConverter<T> : Converter<ResponseBody, List<Student>> {
    override fun convert(value: ResponseBody): List<Student>? {
        TODO("Not yet implemented")
    }
}