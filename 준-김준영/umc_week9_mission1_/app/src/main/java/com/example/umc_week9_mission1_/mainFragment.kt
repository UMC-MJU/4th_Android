package com.example.umc_week9_mission1_

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.umc_week9_mission1_.databinding.MainFragmentBinding

class mainFragment : Fragment() {
    private val binding : MainFragmentBinding by lazy {
        MainFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
}