package com.android.hq.wanandroidkotlin.data

import android.util.Log
import com.android.hq.wanandroidkotlin.bean.BaseResponse
import com.android.hq.wanandroidkotlin.bean.HomeDataBean
import com.android.hq.wanandroidkotlin.network.APIException
import com.android.hq.wanandroidkotlin.network.RequestManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRemoteDataSource {
    suspend fun getHomeData(page:Int) : HomeDataBean? {

        var reponse: retrofit2.Response<BaseResponse<HomeDataBean>>?=null
        try {
            reponse = withContext(Dispatchers.IO) {
                RequestManager.instance.api().getHomeDataList(0).execute()
            }
        } catch (e : Exception) {
            Log.e("Test","getGankData error ",e)
        }

        if (reponse?.isSuccessful == false ){

        }

        if (reponse?.body()?.isFailed() == true) {
            //onSuccess(reponse?.body() as BaseResponse)
            throw APIException(reponse?.body()?.errorCode, reponse?.body()?.errorMsg)
        }

        return reponse?.body()?.data
    }
}