package com.example.android.travelwriter.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.android.travelwriter.R
import com.example.android.travelwriter.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,
        container, false)
        val adapter = MainAdapter(ArticleClickListener { article -> viewModel.displayArticle(article)})

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.articlesList.adapter = adapter

        viewModel.navigateToSelectedArticle.observe(viewLifecycleOwner){ article ->
            if (article!=null){
                this.findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToArticleFragment(article)
                )
                viewModel.displayArticleComplete()
            }
        }
        viewModel.articles.observe(viewLifecycleOwner){ list ->
            list?.let{
                adapter.submitList(it)
            }
        }
        binding.addArticleButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(MainFragmentDirections.actionMainFragmentToAddArticleFragment())
        )

        return binding.root
    }
}