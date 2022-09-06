package com.example.android.travelwriter.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.travelwriter.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {
    private lateinit var binding: FragmentArticleBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val article = ArticleFragmentArgs.fromBundle(requireArguments()).selectedArticle

        val viewModelFactory = ArticleViewModelFactory(article)
        val viewModel = ViewModelProvider(this, viewModelFactory)[ArticleViewModel::class.java]
        binding.viewModel = viewModel

        return binding.root
    }
}