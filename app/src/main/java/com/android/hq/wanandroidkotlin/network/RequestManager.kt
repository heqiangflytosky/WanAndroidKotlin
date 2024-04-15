package com.android.hq.wanandroidkotlin.network

import android.util.Log
import com.android.hq.wanandroidkotlin.bean.BaseResponse
import com.android.hq.wanandroidkotlin.bean.HomeDataBean
import com.android.hq.wanandroidkotlin.utils.AppUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException

class RequestManager {
    private val tag = "RequestManager"
    private val MAX_AGE = 4 * 60 * 60 //缓存4个小时
    private val CACHE_SIZE = 10 * 1024 * 1024//缓存10M；
    private val CONNECT_TIMEOUT:Long = 10
    private var httpApi :ApiInterface
    init {
        httpApi = getRetrofit().create(ApiInterface::class.java)
    }

    private fun getRetrofit():Retrofit {
        // 网络拦截器
        val networkInterceptor = Interceptor { chain ->
            Log.d("TestAA","networkInterceptor")
            val request = chain.request()
            lateinit var response: Response
            try {
                response = chain.proceed(request)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            if (request.url().toString().startsWith(Api.WAN_ANDROID_BASE_URL)/* && !request.url().toString().startsWith(
                    GankApi.GANK_SEARCH_URL
                )*/
            ) {
                // 搜索数据不做缓存，其他数据做缓存
                response.newBuilder()
//                    .header("Cache-Control", "max-age=$MAX_AGE")
                    .build()
            } else response
        }

        val connectionInterceptor = Interceptor { chain ->
            Log.d("TestAA","connectionInterceptor")
            if (AppUtils.isConnected()) {
                val request = chain.request()
                chain.proceed(request)
            } else {
                throw NoNetWorkException(ERROR.NETWORD_ERROR)
            }
        }

        // 日志拦截器
        var logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val cacheDirectory = File(AppUtils.getCacheDir(), "responses")
        val okHttpBuilder = OkHttpClient.Builder()
//            .cache(Cache(cacheDirectory, CACHE_SIZE.toLong()))
            .addNetworkInterceptor(networkInterceptor)
            .addInterceptor(logInterceptor)
            .addInterceptor(connectionInterceptor)
//            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        //client.setConnectTimeout(2000, TimeUnit.MILLISECONDS);

        return Retrofit.Builder()
            .client(okHttpBuilder.build())
            .baseUrl(Api.WAN_ANDROID_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
    }

    companion object Factory {

        val instance : RequestManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            RequestManager()
        }
    }

    fun api() : ApiInterface {
        return httpApi
    }


    fun getHomeData(onSuccess:(reponse: BaseResponse<HomeDataBean>) -> Unit = {}, onFail: () -> Unit ={}) {
        GlobalScope.launch(Dispatchers.Main) {
            var reponse: retrofit2.Response<BaseResponse<HomeDataBean>>?=null
            try {
                reponse = withContext(Dispatchers.IO) {
                    httpApi.getHomeDataList(0).execute()
                }
            } catch (e : Exception) {
                Log.e(tag,"getGankData error ",e)
            }


            if (reponse?.isSuccessful == true) {
                //onSuccess(reponse?.body() as BaseResponse)
                onSuccess(reponse.body())
            }else{
                onFail()
            }
        }
    }

//    fun getGankData(category:String, pageCount:Int, page:Int, onSuccess:(reponse:GankDataResponse) -> Unit = {}, onFail: () -> Unit ={}){
////        GlobalScope.launch(Dispatchers.IO) {
////            val reponse = service.getGankData(category, pageCount, page).execute()
////            withContext(Dispatchers.Main) {
////                if (reponse.isSuccessful) {
////                    onSuccess(reponse.body() as GankDataResponse)
////                }else{
////                    onFail()
////                }
////            }
////        }
//
//        GlobalScope.launch(Dispatchers.Main) {
//            var reponse: retrofit2.Response<GankDataResponse>?=null
//            try {
//                reponse = withContext(Dispatchers.IO) {
//                    service.getGankData(category, pageCount, page).execute()
//                }
//            } catch (e : Exception) {
//                Log.e(tag,"getGankData error ",e)
//            }
//
//
//            if (reponse?.isSuccessful == true) {
//                onSuccess(reponse?.body() as GankDataResponse)
//            }else{
//                onFail()
//            }
//        }
//    }
}