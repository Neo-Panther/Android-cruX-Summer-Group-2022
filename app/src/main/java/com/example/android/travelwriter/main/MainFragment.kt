package com.example.android.travelwriter.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.android.travelwriter.R
import com.example.android.travelwriter.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,
        container, false)

        binding.addArticleButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(MainFragmentDirections.actionMainFragmentToAddArticleFragment())
        )

        return binding.root
    }
}