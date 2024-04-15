package com.android.hq.wanandroidkotlin.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "home")
class ContentItem{
    //主键，自增
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var pid:Long? = null
    @ColumnInfo(name = "id")
    var id:Int?
    @ColumnInfo(name = "title")
    var title: String?
    @ColumnInfo(name = "page")
    var page:Int

    constructor(id:Int?,title: String?,page:Int) {
        this.id = id
        this.title = title
        this.page = page
    }
}