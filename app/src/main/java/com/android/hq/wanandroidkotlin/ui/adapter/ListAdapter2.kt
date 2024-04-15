package com.android.hq.wanandroidkotlin.ui.adapter

import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.hq.wanandroidkotlin.R
import com.android.hq.wanandroidkotlin.bean.ArticleItemBean
import com.android.hq.wanandroidkotlin.bean.ContentItem
import com.android.hq.wanandroidkotlin.bean.Item

class ListAdapter2:RecyclerView.Adapter<RecyclerView.ViewHolder> {
    val TYPE_INVALID = -1
    val TYPE_CONTENT = 1

    var fragment: Fragment
    var list: MutableList<Item>? = null
    constructor(fragment: Fragment){
        this.fragment = fragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            TYPE_CONTENT -> return ContentViewHolder(parent)
            else -> return null!!
        }
    }

    override fun getItemCount(): Int {
        return list?.size?:0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ContentViewHolder) {
            holder.title?.text = "item.title"
            holder.des?.text = "item.desc"
            holder.from?.text = "item.author"
            holder.time?.text = "TTTTT"
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = list?.get(position)
        when(item) {
            is ContentItem -> return TYPE_CONTENT
            else -> return TYPE_INVALID
        }
    }

    fun updateData(list: MutableList<Item>) {
        if (this.list === null || !(this.list?.containsAll(list))!!) {
            forceUpdateData(list)
        } else {
            //Toast.makeText(fragment?.activity, R.string.text_update, Toast.LENGTH_SHORT).show()
        }
    }

    fun forceUpdateData(list: MutableList<Item>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun loadMoreData(list: List<Item>?) {
        this.list?.removeAt(this.list?.size!! - 1)
        notifyItemRangeRemoved(this.list?.size!!, 1)
        if (list != null) {
            val size = this.list?.size
            this.list?.addAll(size!!, list)
            notifyItemRangeInserted(size!!, list.size)
        }
    }

//    fun onLoadMore() {
//        val size = this.list?.size
//        this.list?.add(GankFooterItem())
//        notifyItemRangeInserted(size!!, 1)
//    }
}