package com.android.hq.wanandroidkotlin.ui.adapter

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

class FgPagerAdapter: FragmentStateAdapter {
    var mContext : Context? = null
    var mTabs = ArrayList<PageInfo>()

    constructor(activity: FragmentActivity) : super(activity) {
        mContext = activity
    }

    override fun getItemCount(): Int {
        return mTabs.size
    }

    override fun createFragment(position: Int): Fragment {
        Log.e("Test","createFragment $position ${mTabs.size}")
        val info = mTabs[position]
        return info.fragment
    }

    fun addPage(fragment: Fragment) {
        val info = PageInfo(fragment)
        mTabs.add(info)
        notifyDataSetChanged()
    }
}

class PageInfo(val fragment: Fragment) {
    val tag: String? = null
}