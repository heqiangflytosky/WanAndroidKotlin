package com.android.hq.wanandroidkotlin.repository

import android.util.Log
import com.android.hq.wanandroidkotlin.bean.HomeDataBean
import com.android.hq.wanandroidkotlin.data.HomeLocalDataSource
import com.android.hq.wanandroidkotlin.data.HomeRemoteDataSource
import com.android.hq.wanandroidkotlin.database.DBHomeContentItem
import com.android.hq.wanandroidkotlin.network.NoNetWorkException

class HomeRepository (private val homeRemoteDataSource: HomeRemoteDataSource, private val homeLocalDataSource: HomeLocalDataSource){

    suspend fun getHomeData(page:Int) : HomeDataBean? {
        var reponse : HomeDataBean? = null
        try {
            reponse = homeRemoteDataSource.getHomeData(page)
            if (reponse != null) {
                var listContent = arrayListOf<DBHomeContentItem>()
                reponse.datas.forEach { it ->
                    if(it.author.isNullOrEmpty()) {
                        it.author = it.shareUser
                    }
                    listContent.add(DBHomeContentItem(it.id,it.title,it.author,it.publishTime,it.link,page))
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