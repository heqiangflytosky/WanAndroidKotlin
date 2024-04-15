package com.android.hq.wanandroidkotlin

import android.app.Application
import com.android.hq.wanandroidkotlin.utils.AppUtils

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppUtils.init(this)
    }
}