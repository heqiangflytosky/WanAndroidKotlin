package com.android.hq.wanandroidkotlin.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.android.hq.wanandroidkotlin.data.HomeNetworkDataSource
import com.android.hq.wanandroidkotlin.data.HomeRemoteDataSource
import kotlinx.coroutines.GlobalScope


class HomeRepository2 (private val homeRemoteDataSource: HomeRemoteDataSource){


    //fun getHomeData(page:Int)  = Pager(config = PagingConfig(pageSize = 10,initialLoadSize = 15,prefetchDistance = 1 ,enablePlaceholders = false), pagingSourceFactory = {HomeNetworkDataSource()}).flow.cachedIn(GlobalScope)
    fun getHomeData(page:Int)  = Pager(config = PagingConfig(
        // 每页显示的数据的大小
        pageSize = 19,
        // 开启占位符
        enablePlaceholders = false,
        // 预刷新的距离，距离最后一个 item 多远时加载数据
        // 默认为 pageSize
        prefetchDistance = 10,
        // 初始化加载数量，默认为 pageSize * 3
        initialLoadSize = 19
    )){
        HomeNetworkDataSource()
    }.flow.cachedIn(GlobalScope)
}