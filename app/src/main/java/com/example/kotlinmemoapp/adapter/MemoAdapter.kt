package com.example.kotlinmemoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmemoapp.R
import com.example.kotlinmemoapp.data.MemoData
import com.example.kotlinmemoapp.holder.MemoViewHolder

class MemoAdapter(context: Context) : RecyclerView.Adapter<MemoViewHolder>() {

    private val mContext = context
    private var mDatas = mutableListOf<MemoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.memo_item_layout, parent, false)
        return MemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder?.bind(mDatas.get(position))
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }
}