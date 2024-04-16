package com.android.hq.wanandroidkotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.hq.wanandroidkotlin.R
import com.android.hq.wanandroidkotlin.bean.ContentItem
import com.android.hq.wanandroidkotlin.bean.HomeDataBean
import com.android.hq.wanandroidkotlin.bean.Item
import com.android.hq.wanandroidkotlin.databinding.FragmentHomeBinding
import com.android.hq.wanandroidkotlin.ui.adapter.ListAdapter
import com.android.hq.wanandroidkotlin.ui.adapter.ListAdapter2
import com.android.hq.wanandroidkotlin.viewmodel.HomeViewModel
import com.android.hq.wanandroidkotlin.viewmodel.HomeViewModel2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.ArrayList

class HomeFragment3 : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var homeViewModel : HomeViewModel? = null
    private var homeViewModel2 : HomeViewModel2? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel2 =
            ViewModelProvider(this).get(HomeViewModel2::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//        root.findViewById<Button>(R.id.test).setOnClickListener {
//            testClick1(it)
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun testClick1(view: View) {
//        GlobalScope.launch {
//            homeViewModel?.getHomeData(0, onSuccess = { reponse: HomeDataBean? ->
//
//            })?.catch {
//                Log.e("Test11",it.toString())
//            }?.flowOn(Dispatchers.IO)?.collect { reponse ->
//                Log.e("Test11", reponse.toString())
//            }
//        }

        GlobalScope.launch {
            homeViewModel2?.getHomeData(1, onSuccess = { reponse: HomeDataBean? ->

            })?.catch {
                Log.e("Test11",it.toString())
            }?.flowOn(Dispatchers.IO)?.collect { reponse ->
                Log.e("Test11", reponse.toString())
            }
        }
    }
}