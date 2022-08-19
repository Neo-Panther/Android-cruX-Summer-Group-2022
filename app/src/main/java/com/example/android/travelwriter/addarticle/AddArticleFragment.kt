package com.example.android.travelwriter.addarticle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.android.travelwriter.R
import com.example.android.travelwriter.databinding.FragmentAddArticleBinding

class AddArticleFragment : Fragment() {
    private lateinit var binding: FragmentAddArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_add_article,
        container, false)

        binding.postButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(AddArticleFragmentDirections.actionAddArticleFragmentToMainFragment())
        )
        binding.draftButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(AddArticleFragmentDirections.actionAddArticleFragmentToDraftsFragment())
        )

        (activity as AppCompatActivity).supportActionBar?.title="New Article"
        return binding.root
    }
}