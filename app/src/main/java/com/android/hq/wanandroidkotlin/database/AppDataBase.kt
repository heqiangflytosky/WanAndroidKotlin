package com.android.hq.wanandroidkotlin.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.hq.wanandroidkotlin.utils.AppUtils

@Database(entities = [DBHomeContentItem::class ], version = 1, exportSchema = false)
public abstract class AppDataBase : RoomDatabase(){
    abstract fun homeDao():HomeDao

    companion object{
        private var database:AppDataBase?=null
        @Synchronized
        fun db():AppDataBase{
            return database ?: Room.databaseBuilder(
                AppUtils.getAppContent()!!,
                AppDataBase::class.java,
                "myapp.db"
            ).build()
        }
    }
}