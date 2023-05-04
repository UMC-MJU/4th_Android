package com.example.umc_mission_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.umc_mission_1.databinding.FragmentMenuBinding

class MenuFragment: Fragment() {
    private lateinit var viewBinding: FragmentMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMenuBinding.inflate(layoutInflater)
        return viewBinding.root
    }
}