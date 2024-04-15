package com.android.hq.wanandroidkotlin.network

import com.android.hq.wanandroidkotlin.bean.BaseResponse
import com.android.hq.wanandroidkotlin.bean.HomeDataBean
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("article/list/{page}/json")
    fun getHomeDataList(
        @Path("page") page: Int
    ): Call<BaseResponse<HomeDataBean>>
}