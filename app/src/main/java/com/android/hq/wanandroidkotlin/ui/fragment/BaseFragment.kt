package com.android.hq.wanandroidkotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.hq.wanandroidkotlin.R
import com.android.hq.wanandroidkotlin.bean.ContentItem
import com.android.hq.wanandroidkotlin.bean.HomeDataBean
import com.android.hq.wanandroidkotlin.bean.Item
import com.android.hq.wanandroidkotlin.ui.adapter.ListAdapter2
import com.android.hq.wanandroidkotlin.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import java.util.ArrayList

abstract class BaseFragment : Fragment() {
    var recyclerView: RecyclerView? = null
    var refreshLayout: SwipeRefreshLayout? = null
    var adapter: ListAdapter2? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View = inflater.inflate(R.layout.refresh_recyclerview_fragment, null)
        recyclerView = rootView.findViewById(R.id.recycler_view)
        refreshLayout = rootView.findViewById(R.id.refresh)

        recyclerView?.layoutManager = LinearLayoutManager(recyclerView?.context)

        adapter = ListAdapter2(this)
        recyclerView?.adapter = adapter

        updateData()

        refreshLayout?.setOnRefreshListener {
            Log.e("Test","OnRefresh")
            //adapter?.refresh()
        }

        return rootView
    }

    abstract fun updateData()
}