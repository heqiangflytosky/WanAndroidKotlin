package com.android.hq.wanandroidkotlin.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.android.hq.wanandroidkotlin.bean.BaseResponse
import com.android.hq.wanandroidkotlin.bean.HomeDataBean
import com.android.hq.wanandroidkotlin.data.HomeLocalDataSource
import com.android.hq.wanandroidkotlin.data.HomeNetworkDataSource
import com.android.hq.wanandroidkotlin.data.HomeRemoteDataSource
import com.android.hq.wanandroidkotlin.database.ContentItem
import com.android.hq.wanandroidkotlin.network.NoNetWorkException

class HomeRepository (private val homeRemoteDataSource: HomeRemoteDataSource, private val homeLocalDataSource: HomeLocalDataSource){

    suspend fun getHomeData(page:Int) : HomeDataBean? {
        var reponse : HomeDataBean? = null
        try {
            reponse = homeRemoteDataSource.getHomeData(page)
            if (reponse != null) {
                var listContent = arrayListOf<ContentItem>()
                reponse.datas.forEach { it ->
                    listContent.add(ContentItem(it.id,it.link,page))
                }
                Log.e("TestDB",""+listContent)
                homeLocalDataSource.addDatas(listContent)
            }
        } catch (exception:NoNetWorkException) {
            reponse = homeLocalDataSource.getHomeData(page)
        }
        return reponse
    }


//    fun getHomeData(page:Int)  = Pager(PagingConfig(page),0){
//        HomeNetworkDataSource()
//    }.flow
}