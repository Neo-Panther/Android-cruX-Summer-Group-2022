package com.example.android.travelwriter.drafts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.travelwriter.R
import com.example.android.travelwriter.database.ArticleDatabase
import com.example.android.travelwriter.databinding.FragmentDraftsBinding

class DraftsFragment : Fragment() {
    private lateinit var binding: FragmentDraftsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drafts, container,
            false)

        val application = requireActivity().application
        val dataSource = ArticleDatabase.getInstance(application).articleDao

        val viewModelFactory = DraftsViewModelFactory(dataSource)
        val viewModel = ViewModelProvider(this, viewModelFactory)[DraftsViewModel::class.java]
        val adapter = DraftsAdapter(ArticleClickListener { articleId ->
            viewModel.delete(articleId)
        })

        binding.lifecycleOwner = this
        binding.draftsList.adapter = adapter

        viewModel.articles.observe(viewLifecycleOwner){ list ->
            list?.let{
                adapter.submitList(it)
            }
        }

        return binding.root
    }
}