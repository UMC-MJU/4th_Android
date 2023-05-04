package com.example.umc_week6_mission3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.umc_week6_mission3.databinding.ActivityMainBinding
import me.relex.circleindicator.CircleIndicator3

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG: String = "로그"
    }

    private var pageItemList = ArrayList<PageItem>()
    private lateinit var myIntroPagerRecyclerAdapter: MyIntroPagerRecyclerAdapter
    private val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        Log.d(TAG, "MainActivity - onCreate() called")

        viewBinding.previousBtn.setOnClickListener {
            Log.d(TAG, "MainActivity - 이전 버튼 클릭")
            viewBinding.introViewPager.currentItem = viewBinding.introViewPager.currentItem - 1
        }
        viewBinding.nextBtn.setOnClickListener {
            Log.d(TAG, "MainActivity - 다음 버튼 클릭")
            viewBinding.introViewPager.currentItem = viewBinding.introViewPager.currentItem + 1
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        pageItemList.add(PageItem(R.color.lightgreen, R.drawable.study_1, "첫번째 스터디"))
        pageItemList.add(PageItem(R.color.lightorange, R.drawable.study_2, "두번째 스터디"))
        pageItemList.add(PageItem(R.color.white, R.drawable.study_3, "세번째 스터디"))

        myIntroPagerRecyclerAdapter = MyIntroPagerRecyclerAdapter(pageItemList)
        viewBinding.introViewPager.apply { //코드 길이 축
            adapter = myIntroPagerRecyclerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            viewBinding.dotsIndicator.setViewPager(viewBinding.introViewPager)
        }

    }
}
