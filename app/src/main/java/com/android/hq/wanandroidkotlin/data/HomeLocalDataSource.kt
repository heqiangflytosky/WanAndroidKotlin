package com.android.hq.wanandroidkotlin.data

import com.android.hq.wanandroidkotlin.bean.ArticleItemBean
import com.android.hq.wanandroidkotlin.bean.HomeDataBean
import com.android.hq.wanandroidkotlin.database.AppDataBase
import com.android.hq.wanandroidkotlin.database.DBHomeContentItem
import com.android.hq.wanandroidkotlin.database.HomeDao

class HomeLocalDataSource {
    private val dao:HomeDao by lazy { AppDataBase.db().homeDao() }
    fun getHomeData(page:Int) : HomeDataBean {
        var listContent = dao.getAllData()
        var listArticle = arrayListOf<ArticleItemBean>()
        listContent?.forEach { it ->
            listArticle.add(ArticleItemBean(it.id,it.author,it.author,it.publishTime,it.link,it.title))
        }
        return HomeDataBean(page,listArticle,0,false,-1,listArticle.size,-1)
    }

    fun cleanHomeData() {
        dao.deleteAll()
    }

    fun addDatas(datas:List<DBHomeContentItem>) {
        dao.recordDeletion()
        dao.deleteAll()
        dao.insertAll(datas)
    }
}