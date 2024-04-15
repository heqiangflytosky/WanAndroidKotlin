package com.android.hq.wanandroidkotlin.bean

import java.io.Serializable

interface Item : Serializable

class ContentItem  : Item{
    var author: String?
    var publishTime: Long?
    var link:String?
    var title:String?
    constructor(bean: ArticleItemBean){
        author = bean.author
        publishTime = bean.publishTime
        link = bean.link
        title = bean.title
    }
}