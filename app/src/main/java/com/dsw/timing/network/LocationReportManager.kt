package com.dsw.timing.network

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.dsw.timing.Constant.Companion.BASE_DEBUG_URL
import com.dsw.timing.Constant.Companion.BASE_URL
import com.dsw.timing.bean.Location
import com.dsw.timing.bean.LocationKotlin
import com.dsw.timing.fragment.SearchFragment.GetLocationMarkerListener
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class LocationReportManager {

    private var mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(StudentListConvertFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private lateinit var mLocationService: NetworkService

    private val TAG: String = "LocationReportManager"

    private constructor() {

    }

    fun reportLocation(location : Location, context: Context) {
        mLocationService = mRetrofit.create<NetworkService>();
        val call = mLocationService.postLocation(location)
        call.enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful) {
                    Toast.makeText(context, "上报定位成功!", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "上报定位成功!");
                } else {
                    Toast.makeText(context, "上报定位失败!", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "上报定位失败: " + response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e(TAG, "上报定位失败: " + t.message);
            }

        })
    }

    fun getRecentLocation(context: Context, listener: GetLocationMarkerListener) {
        mLocationService.getRecentLocation().enqueue(object : retrofit2.Callback<Location> {
            override fun onResponse(call: Call<Location>, response: Response<Location>) {
                if(response.isSuccessful) {
                    val location = response.body()
                    Log.i("dsw", "拉取位置信息成功！ longitude: " + location?.longitude + ", latitude: " + location?.latitude);
                    listener.onGetLocation(location)
                } else {
                    Log.e(TAG, "请求获取位置失败: " + response.errorBody()?.string());
                }
            }

            override fun onFailure(call: Call<Location>, t: Throwable) {
                Toast.makeText(context, "请求获取位置失败", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "请求获取位置失败: " + t.message);
            }

        })
    }

    companion object {
        val instance : LocationReportManager by lazy { LocationReportManager() }
    }


}