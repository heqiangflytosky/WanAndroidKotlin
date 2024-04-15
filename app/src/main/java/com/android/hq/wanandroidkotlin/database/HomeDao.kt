package com.android.hq.wanandroidkotlin.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
public interface HomeDao : BaseDao<ContentItem>{
    @Query("select * from home")
    public fun getAllData(): List<ContentItem>

    @Transaction
    @Query("select * from home where page =:currentPage")
    public fun getPageData(currentPage:Int): List<ContentItem>?

    @Query("delete from home")
    fun deleteAll()

    // 适用于清除表数据时 PrimaryKey 也需要重置的情况
    @Query("DELETE FROM sqlite_sequence WHERE name = 'home'")
    fun recordDeletion()

    @Transaction
    fun insertAll(objects: List<ContentItem>) = objects.forEach {
        insert(it)
    }
}