package com.android.hq.wanandroidkotlin.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.hq.wanandroidkotlin.bean.ArticleItemBean
import com.android.hq.wanandroidkotlin.bean.BaseResponse
import com.android.hq.wanandroidkotlin.bean.HomeDataBean
import com.android.hq.wanandroidkotlin.network.RequestManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeNetworkDataSource() : PagingSource <Int, ArticleItemBean>(){
    init {
        Log.e("Test","init HomeNetworkDataSource")
    }
    override fun getRefreshKey(state: PagingState<Int, ArticleItemBean>): Int? {
        //https://juejin.cn/post/7098390670150205477
        Log.e("Test","getRefreshKey")
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey
//        }
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleItemBean> {
        Log.e("Test","load ${params.key} ${params.loadSize}")
        //页码未定义置为1
        var currentPage = params.key ?: 0

        var reponse: retrofit2.Response<BaseResponse<HomeDataBean>>?=null
        try {
            reponse = withContext(Dispatchers.IO) {
                RequestManager.instance.api().getHomeDataList(currentPage).execute()
            }
        } catch (e : Exception) {
            Log.e("Test","getGankData error ",e)
        }

        //当前页码 小于 总页码 页面加1
        var nextPage = if (currentPage < reponse?.body()?.data?.pageCount ?: 0) {
            currentPage + 1
        } else {
            //没有更多数据
            null
        }

        val preKey = if (currentPage > 0) currentPage - 1 else null
        Log.e("Test","load nextPage ${nextPage} preKey ${preKey}")
        return if (reponse != null) {
            LoadResult.Page(data = reponse?.body()?.data?.datas!!, preKey, nextPage)
        } else {
            LoadResult.Error(throwable = Throwable())
        }
    }
}