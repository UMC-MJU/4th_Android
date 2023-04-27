package com.example.umc_week5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter(val profileList: ArrayList<Profiles>) : RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.CustomViewHolder { //onCreate와 비슷한 개념
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false) // context : 메인 엑티비티에 대한 정보들이 담겨져있는 의미
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos : Int = adapterPosition
                val profile : Profiles = profileList.get(curPos) // 프로필의 원하는 데이터를 가져오는 형식
                Toast.makeText(parent.context, "이름 : ${profile.name}\n나이 : ${profile.age}\n직업 ${profile.job}", Toast.LENGTH_SHORT).show()

            }
        } // itemview가 전달되는 것
    }

    override fun onBindViewHolder(holder: ProfileAdapter.CustomViewHolder, position: Int) { //스크롤을 할때 온바인드가 계속적으로 호출해 모든 데이터들을 매칭시키는 것
        holder.gender.setImageResource(profileList.get(position).gender)  //포지션은 리스트의 인덱스값과 같은 의미
        holder.name.text = profileList.get(position).name // setText같은 개념이다 즉 미리 준비되어있는 텍스트를 처리하는 과정
        holder.age.text = profileList.get(position).age.toString() //age는 숫자 값이기 때문에 string 형전환
        holder.job.text = profileList.get(position).job
    }

    override fun getItemCount(): Int {
        return profileList.size

    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gender = itemView.findViewById<ImageView>(R.id.iv_profile) // 성별
        val name = itemView.findViewById<TextView>(R.id.tv_name) // 이름
        val age = itemView.findViewById<TextView>(R.id.tv_age) // 이름
        val job = itemView.findViewById<TextView>(R.id.tv_job) // 이름
    }

}

