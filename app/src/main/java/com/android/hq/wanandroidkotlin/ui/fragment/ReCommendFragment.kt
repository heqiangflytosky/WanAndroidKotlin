package com.android.hq.wanandroidkotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.android.hq.wanandroidkotlin.bean.ContentItem
import com.android.hq.wanandroidkotlin.bean.HomeDataBean
import com.android.hq.wanandroidkotlin.bean.Item
import com.android.hq.wanandroidkotlin.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import java.util.ArrayList

class ReCommendFragment : BaseFragment() {
    private var homeViewModel : HomeViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        Log.e("Test","ReCommendFragment onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun updateData() {
        lifecycleScope.launchWhenCreated {
            homeViewModel?.getHomeData(0, onSuccess = { reponse: HomeDataBean? ->

            })?.catch {
                Log.e("Test11",it.toString())
            }?.flowOn(Dispatchers.IO)?.collect { reponse ->
                Log.e("Test1133", reponse.toString())
                val list = ArrayList<Item>()
                if (reponse != null && reponse.datas != null) {
                    for (bean in reponse.datas) {
                        list.add(ContentItem(bean))
                    }
                }
                adapter?.updateData(list)
            }
        }
    }

}