package com.dsw.timing.network

import android.util.Log
import androidx.annotation.RequiresApi
import com.dsw.timing.bean.Location
import com.dsw.timing.bean.Student
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class StudentListConvertFactory: Converter.Factory() {

    @RequiresApi(28)
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        if("java.util.List<com.dsw.timing.bean.Student>".equals(type.typeName)) {
            return object : Converter<ResponseBody, List<Student>> {
                override fun convert(value: ResponseBody): List<Student>? {
                    return StudentListObject.fromJson(value.string())
                }
            }
        } else if("com.dsw.timing.bean.Location".equals(type.typeName)) {
            return object : Converter<ResponseBody, Location> {
                override fun convert(value: ResponseBody): Location? {
                    return LocationObject.fromJson(value.string())
                }

            }
        }

        return null
    }

    data class LocationObject(val data : String) {
        companion object {
            fun fromJson(jsonstr: String) : Location? {
                try {
                    val jsonObject = JSONObject(jsonstr)
                    val data = jsonObject.getJSONObject("data")
                    val location = Gson().fromJson(data.toString(), Location::class.java)
                    Log.d("dsw", "from json");
                    return location;
                } catch (e : Exception) {
                    e.printStackTrace()
                }
                return null
            }
        }
    }

    data class StudentListObject(val data : String) {
        companion object {
            fun fromJson(jsonstr: String) : List<Student>? {
                try {
                    val jsonObject = JSONObject(jsonstr)
                    val jsonArray = jsonObject.getJSONArray("data")
                    val list = ArrayList<Student>()
                    val gson = Gson()
                    for(i in 0 until jsonArray.length()) {
                        val temp = jsonArray.getJSONObject(i).toString()
                        val student = gson.fromJson(temp, Student::class.java)
                        list.add(student)
                    }
                    Log.d("dsw", "from json");
                    return list;
                } catch (e : Exception) {
                    e.printStackTrace()
                }
                return null
            }
        }
    }
}