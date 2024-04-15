package com.android.hq.wanandroidkotlin.network

interface CallBack<T> {
    fun onSuccess(t: T?)
    fun onFail()
}