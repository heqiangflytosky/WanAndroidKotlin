package com.android.hq.wanandroidkotlin.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "home")
class DBHomeContentItem{
    //主键，自增
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var pid:Long? = null
    @ColumnInfo(name = "id")
    var id:Int?
    @ColumnInfo(name = "title")
    var title: String?
    @ColumnInfo(name = "author")
    var author: String?
    @ColumnInfo(name = "publishTime")
    var publishTime: Long?
    @ColumnInfo(name = "link")
    var link: String?
    @ColumnInfo(name = "page")
    var page:Int

    constructor(id:Int?,title: String?,author: String?,publishTime: Long?,link: String?,page:Int) {
        this.id = id
        this.title = title
        this.author = author
        this.publishTime = publishTime
        this.link = link
        this.page = page
    }
}