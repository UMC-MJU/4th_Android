package com.example.umc_week6_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.umc_week6_2.databinding.FragmentTwoBinding

class TwoFragment:Fragment() {
    private lateinit var viewBinding: FragmentTwoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentTwoBinding.inflate(layoutInflater)
        return viewBinding.root
    }
}