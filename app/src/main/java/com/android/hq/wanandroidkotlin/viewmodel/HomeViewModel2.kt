package com.android.hq.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.android.hq.wanandroidkotlin.bean.ArticleItemBean
import com.android.hq.wanandroidkotlin.bean.HomeDataBean
import com.android.hq.wanandroidkotlin.data.HomeRemoteDataSource
import com.android.hq.wanandroidkotlin.network.APIException
import com.android.hq.wanandroidkotlin.repository.HomeRepository
import com.android.hq.wanandroidkotlin.repository.HomeRepository2
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeViewModel2 : ViewModel() {
    private val homeRepository by lazy { HomeRepository2(HomeRemoteDataSource()) }

    fun getHomeData(page:Int, onSuccess:(reponse: HomeDataBean?) -> Unit = {}, onFail: () -> Unit ={}) : Flow<PagingData<ArticleItemBean>?> {
        return homeRepository.getHomeData(page)
    }
}