package com.example.umc_week5_mission2

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_week5_mission2.databinding.ListItemBinding

class MemosAdapter(val memoList : ArrayList<Memos>) : RecyclerView.Adapter<MemosAdapter.CustomViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemosAdapter.CustomViewHolder
    {
        var inflater = LayoutInflater.from(parent.context)
        var binding = ListItemBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)
    }

    companion object {
        const val REQUEST_CODE_MEMO = 100
    }

    override fun onBindViewHolder(holder: MemosAdapter.CustomViewHolder, position: Int) {
        val memo = memoList[position]

        holder.txt.text = memo.txtview
        holder.image.setImageResource(memoList[position].image)
        holder.txt.text = memoList[position].txtview

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MemoWriteActivity::class.java)
            intent.putExtra("memoIndex", position) // 수정하고자 하는 메모의 인덱스 전달
            intent.putExtra("currentValue", memoList[position].txtview)
            (holder.itemView.context as Activity).startActivityForResult(intent, REQUEST_CODE_MEMO)
        }
        holder.itemView.setOnLongClickListener {
            memoList.removeAt(position)
            notifyItemRemoved(position)
            true // true를 반환(소멸)
        }
    }

    override fun getItemCount(): Int
    {
        return memoList.size

    }

    class CustomViewHolder(var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        var image = binding.imageView
        var txt = binding.tvValue
    }


}

