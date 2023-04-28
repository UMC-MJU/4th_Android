package com.example.umc_week5_mission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_week5_mission.databinding.ListItemBinding

class SwitchAdapter(val switchList : ArrayList<Switch>) : RecyclerView.Adapter<SwitchAdapter.CustomViewHolder>()
{
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder
        {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemBinding.inflate(inflater, parent, false)
            return CustomViewHolder(binding)
        }

    override fun onBindViewHolder(holder: SwitchAdapter.CustomViewHolder, position: Int) {
        holder.image.setImageResource(switchList.get(position).image)
        holder.switch.text = switchList.get(position).switch
    }

    override fun getItemCount(): Int {
        return switchList.size

    }

    inner class CustomViewHolder(var binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var image = binding.imageView2
        var switch = binding.switch1
    }


}