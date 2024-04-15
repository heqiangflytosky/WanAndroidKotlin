package com.android.hq.wanandroidkotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.hq.wanandroidkotlin.R

class ContentViewHolder: RecyclerView.ViewHolder {
    var title: TextView? = null
    var des: TextView? = null
    var from: TextView? = null
    var time: TextView? = null
    constructor(parent: ViewGroup):
            super(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_content, parent, false)){
        title = itemView.findViewById<TextView>(R.id.content_title)!!
        des = itemView.findViewById(R.id.content_des)
        from = itemView.findViewById<TextView>(R.id.item_footer_from)!!
        time = itemView.findViewById<TextView>(R.id.item_footer_time)!!
    }
}