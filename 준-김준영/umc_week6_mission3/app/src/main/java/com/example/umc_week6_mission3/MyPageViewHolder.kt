package com.example.umc_week6_mission3

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyPageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val itemImage: ImageView = itemView.findViewById(R.id.pager_item_image)
    private val itemContent: TextView = itemView.findViewById(R.id.pager_item_text)
    private val itemBg: LinearLayout = itemView.findViewById(R.id.pager_item_bg)

    fun bindwithView(pageItem: PageItem){
        itemImage.setImageResource(pageItem.imageSrc)
        itemContent.text = pageItem.content

        if(pageItem.bgColor != R.color.white){
            itemContent.setTextColor(Color.WHITE)
        }

        itemBg.setBackgroundResource(pageItem.bgColor)
    }
}