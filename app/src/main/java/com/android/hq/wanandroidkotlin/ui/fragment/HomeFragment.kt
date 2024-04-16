package com.android.hq.wanandroidkotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.android.hq.wanandroidkotlin.R
import com.android.hq.wanandroidkotlin.bean.HomeDataBean
import com.android.hq.wanandroidkotlin.databinding.FragmentHomeBinding
import com.android.hq.wanandroidkotlin.ui.adapter.FgPagerAdapter
import com.android.hq.wanandroidkotlin.viewmodel.HomeViewModel
import com.android.hq.wanandroidkotlin.viewmodel.HomeViewModel2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var homeViewModel : HomeViewModel? = null
    private var homeViewModel2 : HomeViewModel2? = null

    private lateinit var viewPager: ViewPager2
    private lateinit var  tabLayout : TabLayout
    private lateinit var pagerAdapter : FgPagerAdapter
    private lateinit var mediator: TabLayoutMediator

    private var tabArray = arrayOf(R.string.tab_recommend,R.string.tab_categorize)
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

        viewPager = root.findViewById(R.id.view_pager)
        tabLayout = root.findViewById(R.id.tabs)
        pagerAdapter = FgPagerAdapter(requireActivity())

        viewPager?.adapter = pagerAdapter
        viewPager?.offscreenPageLimit = 1

        mediator = TabLayoutMediator(
            /* tabLayout = */ tabLayout,/* viewPager = */
            viewPager, /* tabConfigurationStrategy = */
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                Log.e("Test","TabConfigurationStrategy")
                tab.text = resources.getText(tabArray[position])
            }
        )
        mediator.attach()

        addPages()
        //addTabs()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun addPages() {
        //pagerAdapter?.addPage(GankDailyFragment())
        Log.e("Test","addPages ")
        pagerAdapter?.addPage(ReCommendFragment())

        pagerAdapter?.addPage(ReCommendFragment())



    }

    fun addTabs() {
        Log.e("Test","addTabs "+Log.getStackTraceString(Throwable()))
        //addTab(R.string.tab_daily_recommend, 0, true)
        addTab(R.string.tab_recommend, 0, true)
        addTab(R.string.tab_categorize, 1, false)

        viewPager?.currentItem = 0
    }

    fun addTab(textId:Int, position: Int, selected: Boolean) {
        var tab = tabLayout?.newTab()
        tab?.text = resources.getText(textId)
        Log.e("Test","addTab "+tabLayout.tabCount)
        tabLayout?.addTab(tab!!, position, selected)

    }
}