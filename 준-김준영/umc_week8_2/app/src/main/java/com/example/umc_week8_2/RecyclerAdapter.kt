package com.example.umc_week8_2

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.umc_week8_2.databinding.ItemRecyclerBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import java.text.SimpleDateFormat

class RecyclerAdapter(val roomMemoList:MutableList<RoomMemo>, private val memoDao:RoomMemoDAO) : RecyclerView.Adapter<RecyclerAdapter.Holder>(){
    private lateinit var binding: ItemRecyclerBinding
    private val TAG = "RecyclerAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setItem(roomMemoList.get(position))

        holder.binding.ivStar.setOnClickListener { // 즐겨찾기 클릭 이벤트
            val roomMemo = roomMemoList[position]

            if(roomMemo.star == 0){
                roomMemo.star = 1
                val memo = RoomMemo(roomMemo.content, roomMemo.datetime, 1, roomMemo.isFav)
                updateStar(memo, position)
                notifyItemChanged(position)

                val toast = Toast.makeText(holder.itemView.context, "즐겨찾기에 추가되었습니다.",Toast.LENGTH_SHORT).show()
            }else{
                roomMemo.star = 0
                val memo = RoomMemo(roomMemo.content, roomMemo.datetime, 0, roomMemo.isFav)
                updateStar(memo, position)
                notifyItemChanged(position)
                val toast = Toast.makeText(holder.itemView.context, "즐겨찾기에 삭제되었습니다.",Toast.LENGTH_SHORT).show()
            }
        }

        holder.binding.ivHeart.setOnClickListener {
            val roomMemo = roomMemoList[position]

            if(roomMemo.isFav == 0){
                roomMemo.isFav = 1
                val memo = RoomMemo(roomMemo.content, roomMemo.datetime, roomMemo.star!!, 1)
                updateHeart(memo, position)
                notifyItemChanged(position)
            }else{
                roomMemo.isFav = 0
                val memo = RoomMemo(roomMemo.content, roomMemo.datetime, roomMemo.star!!, 0)
                updateHeart(memo, position)
                notifyItemChanged(position)
            }
        }

        holder.itemView.setOnLongClickListener {
            val builder = AlertDialog.Builder(holder.itemView.context)
            val roomMemo = roomMemoList[position]
            val memo = RoomMemo(roomMemo.content, roomMemo.datetime, roomMemo.star!!, roomMemo.isFav)

            builder.setTitle("알림")
                .setMessage("삭제하시겠습니까?")
                .setPositiveButton("네",
                    DialogInterface.OnClickListener { dialog, id ->
                        if (position in 0 until roomMemoList.size) {
                            Log.e("Positive of Position : ", position.toString())
                            deleteData(memo, position)
                        }
                    })
                .setNegativeButton("아니오", null)
            Log.e("Position : ", position.toString())
            builder.show()
            true
        }


    }

    override fun getItemCount() = roomMemoList.size

    class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){

        fun setItem(roomMemo:RoomMemo){
            with(binding){
                txtNo.text = "${roomMemo.no}"
                txtContent.text = roomMemo.content

                val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
                txtDate.text = sdf.format(roomMemo.datetime)

                if(roomMemo.star == 0){
                    ivStar.setImageResource(R.drawable.star_empty)
                }else{
                    ivStar.setImageResource(R.drawable.star_full)
                }

                if(roomMemo.isFav == 0){
                    ivHeart.setImageResource(R.drawable.heart_empty)
                }else{
                    ivHeart.setImageResource(R.drawable.heart_full)
                }
            }
        }
    }

    fun updateStar(memo: RoomMemo, position: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val no = roomMemoList[position].no
            no?.let {
                Log.e(TAG, "update!")
                memo.setIsNo(no)
                memoDao.update(memo)
            }
        }
    }

    fun updateHeart(memo: RoomMemo, position: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val no = roomMemoList[position].no
            no?.let {
                memo.setIsNo(no)
                memoDao.update(memo)
            }
        }
    }

    fun deleteData(memo: RoomMemo, position: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val no = roomMemoList[position].no
            no?.let {
                Log.e(TAG, "delete!")
                memo.setIsNo(no)
                memoDao.delete(memo)
            }
        }.invokeOnCompletion {
            /* 코루틴이 완료될 때까지 기다린 후에 삭제 작업 수행, 코루틴 사용법을 주의깊게 고려해야함
            이 코드를 적지 않으면 positon 값이 유요하지 않게 된다
            deleteData 함수 작동 후에만 리스트의 크기가 변경되는 의미*/
            CoroutineScope(Dispatchers.Main).launch {
                roomMemoList.removeAt(position)
                notifyDataSetChanged() // 리사이클러 뷰 업데이트
            }
        }
    }

}