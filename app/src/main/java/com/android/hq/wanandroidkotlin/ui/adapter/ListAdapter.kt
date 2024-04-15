package com.android.hq.wanandroidkotlin.ui.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.hq.wanandroidkotlin.bean.ArticleItemBean

class ListAdapter : PagingDataAdapter<ArticleItemBean, RecyclerView.ViewHolder>(object :

    DiffUtil.ItemCallback<ArticleItemBean>(){
    override fun areItemsTheSame(oldItem: ArticleItemBean, newItem: ArticleItemBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ArticleItemBean, newItem: ArticleItemBean): Boolean {
        return oldItem == newItem
    }

}){
    val TYPE_INVALID = -1
    val TYPE_CONTENT = 1
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ContentViewHolder) {
            holder.title?.text = "item.title"
            holder.des?.text = "item.desc"
            holder.from?.text = "item.author"
            holder.time?.text = "TTTTT"
        }
    }

    override fun getItemViewType(position: Int): Int {
//        val item = list?.get(position)
//        when(item) {
//            is GankContentItem -> return TYPE_CONTENT
//        }
        return TYPE_CONTENT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            TYPE_CONTENT -> return ContentViewHolder(parent)
            else -> return null!!
        }
    }

    override fun getItemCount(): Int {
        Log.e("Test","item count "+super.getItemCount())
        return super.getItemCount()
    }
}