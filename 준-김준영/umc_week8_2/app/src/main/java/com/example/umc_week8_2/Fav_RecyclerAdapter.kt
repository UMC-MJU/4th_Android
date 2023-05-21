package com.example.umc_week8_2

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_week8_2.databinding.FavoriteBinding
import com.example.umc_week8_2.databinding.ItemRecyclerBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class Fav_RecyclerAdapter(val favList:MutableList<FavData>, private val favDao:FavDataDAO) : RecyclerView.Adapter<Fav_RecyclerAdapter.FavHolder>() {
    private lateinit var binding: ItemRecyclerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Fav_RecyclerAdapter.FavHolder {
        binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavHolder(binding)
    }

    override fun getItemCount(): Int = favList.size

    override fun onBindViewHolder(holder: FavHolder, position: Int) {
        holder.setItem(favList.get(position))

    }

    class FavHolder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
        fun setItem(favData: FavData){
            with(binding){
                txtNo.text = favData.no.toString()
                txtContent.text = favData.content
                val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
                txtDate.text = sdf.format(favData.datetime)

                if(favData.star == 1){
                    ivStar.setImageResource(R.drawable.star_full)
                }else{
                    ivStar.setImageResource(R.drawable.star_empty)
                }

                if(favData.isFav == 1){
                    ivHeart.setImageResource(R.drawable.heart_full)
                }else{
                    ivHeart.setImageResource(R.drawable.heart_empty)
                }
            }
        }

    }

}