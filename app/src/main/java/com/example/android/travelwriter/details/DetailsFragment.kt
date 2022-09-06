package com.example.android.travelwriter.details

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.travelwriter.R
import com.example.android.travelwriter.database.ArticleDatabase
import com.example.android.travelwriter.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private lateinit var binding : FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container,
            false)
        val sharedPrefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val application = requireActivity().application
        val dataSource = ArticleDatabase.getInstance(application).articleDao

        binding.lifecycleOwner = this

        val viewModelFactory = DetailsViewModelFactory(application, dataSource, sharedPrefs)
        val viewModel = ViewModelProvider(this, viewModelFactory)[DetailsViewModel::class.java]

        binding.viewModel = viewModel
        return binding.root
    }
}