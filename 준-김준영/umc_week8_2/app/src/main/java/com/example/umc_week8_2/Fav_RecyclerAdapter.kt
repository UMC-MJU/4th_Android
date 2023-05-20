package com.example.umc_week8_2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_week8_2.databinding.FavoriteBinding
import com.example.umc_week8_2.databinding.ItemRecyclerBinding
import org.greenrobot.eventbus.EventBus

class Fav_RecyclerAdapter(val favList:MutableList<FavData>, private val favDao:FavDataDAO) : RecyclerView.Adapter<Fav_RecyclerAdapter.FavHolder>() {
    private lateinit var binding: ItemRecyclerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Fav_RecyclerAdapter.FavHolder {
        binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavHolder(binding)
    }

    override fun getItemCount(): Int = favList.size

    override fun onBindViewHolder(holder: FavHolder, position: Int) {
        holder.setItem(favList[position])

    }

    class FavHolder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
        fun setItem(favData: FavData){

            with(binding){
                txtDate.text = "test"
            }
        }

    }
}