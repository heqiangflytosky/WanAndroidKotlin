package com.android.hq.wanandroidkotlin.bean

import com.android.hq.wanandroidkotlin.utils.OpenDataClass
import java.util.ArrayList


@OpenDataClass
data class ArticleItemBean (
    var id:Int?,
    var author: String?,
    var shareUser: String?,
    var publishTime: Long?,
    var link:String?,
    var title:String?
)

data class HomeDataBean (
    var curPage: Int?,
    var datas: ArrayList<ArticleItemBean>,
    var offset: Int?,
    var over: Boolean?,
    var pageCount:Int?,
    var size: Int?,
    var total: Int?
)

data class BaseResponse<out T>(
    val data: T?,
    val errorCode: Int = 0,
    val errorMsg: String = ""
) {
    fun isFailed(): Boolean {
        return errorCode != 0
    }
}