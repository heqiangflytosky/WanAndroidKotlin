package com.android.hq.wanandroidkotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.hq.wanandroidkotlin.R
import com.android.hq.wanandroidkotlin.bean.HomeDataBean
import com.android.hq.wanandroidkotlin.ui.adapter.ListAdapter
import com.android.hq.wanandroidkotlin.viewmodel.HomeViewModel2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

class HomeFragment2 : Fragment() {

    var recyclerView: RecyclerView? = null
    var refreshLayout: SwipeRefreshLayout? = null
    var adapter: ListAdapter? = null

    private var homeViewModel2 : HomeViewModel2? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View = inflater.inflate(R.layout.refresh_recyclerview_fragment, null)
        recyclerView = rootView.findViewById(R.id.recycler_view)
        refreshLayout = rootView.findViewById(R.id.refresh)

        recyclerView?.layoutManager = LinearLayoutManager(recyclerView?.context)

        adapter = ListAdapter()
        recyclerView?.adapter = adapter

        homeViewModel2 =
            ViewModelProvider(this).get(HomeViewModel2::class.java)

        lifecycleScope.launchWhenCreated {
            homeViewModel2?.getHomeData(0, onSuccess = { reponse: HomeDataBean? ->

            })?.catch {
                Log.e("Test11",it.toString())
            }?.flowOn(Dispatchers.IO)?.collect { reponse ->
                Log.e("Test1133", reponse.toString())
                adapter?.submitData(reponse!!)
            }
        }

        adapter?.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    //progressBar?.visibility = View.INVISIBLE
                    recyclerView?.visibility = View.VISIBLE
                    refreshLayout?.isRefreshing = false

                }
                is LoadState.Loading -> {
                    refreshLayout?.isRefreshing = true
                    //progressBar?.visibility = View.VISIBLE
                    recyclerView?.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    //progressBar?.visibility = View.INVISIBLE
                    refreshLayout?.isRefreshing = false
                }
            }
        }

        refreshLayout?.setOnRefreshListener {
            Log.e("Test","OnRefresh")
            adapter?.refresh()
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}