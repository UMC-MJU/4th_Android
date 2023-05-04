package com.example.umc_week6_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.umc_week6_2.databinding.FragmentOneBinding

class OneFragment:Fragment() {
    private lateinit var viewBinding: FragmentOneBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentOneBinding.inflate(layoutInflater)
        return viewBinding.root
    }
}