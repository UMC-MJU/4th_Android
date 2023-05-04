package com.example.umc_week6_mission3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyIntroPagerRecyclerAdapter(private var pageList: ArrayList<PageItem>)
    : RecyclerView.Adapter<MyPageViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageViewHolder {
        return MyPageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_intro_pager_item, parent, false))
    }

    override fun getItemCount(): Int {
        return pageList.size

    }

    override fun onBindViewHolder(holder: MyPageViewHolder, position: Int) {
       holder.bindwithView(pageList[position]) // 데이터와 뷰를 묶는다
    }


}