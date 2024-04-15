package com.android.hq.wanandroidkotlin.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface BaseDao<T> {

    @Insert
    fun insert(bean: T)

    @Delete
    fun delete(bean:T)

    @Update
    fun update(bean: T)
}