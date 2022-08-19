package com.example.android.travelwriter.addarticle

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.travelwriter.MainActivity
import com.example.android.travelwriter.R
import com.example.android.travelwriter.database.ArticleDatabase
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

        val application = this.requireActivity().application
        val dataSource = ArticleDatabase.getInstance(application).articleDao
        val sharedPrefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val arguments = AddArticleFragmentArgs.fromBundle(requireArguments())
        val username = sharedPrefs.getString(MainActivity.USERNAME_KEY, null)

        val viewModelFactory = AddArticleViewModelFactory(dataSource, arguments.articleKey, username!!)
        val viewModel = ViewModelProvider(this, viewModelFactory)[AddArticleViewModel::class.java]

        viewModel.navigateToMain.observe(viewLifecycleOwner) { go ->
            if (go) {
                this.findNavController().navigate(
                    AddArticleFragmentDirections
                        .actionAddArticleFragmentToMainFragment()
                )
                viewModel.doneNavigatingToMain()
            }
        }
        viewModel.navigateToDrafts.observe(viewLifecycleOwner) { go ->
            if (go) {
                this.findNavController().navigate(
                    AddArticleFragmentDirections
                        .actionAddArticleFragmentToDraftsFragment()
                )
                viewModel.doneNavigatingToDrafts()
            }
        }
        viewModel.validInput.observe(viewLifecycleOwner) { valid ->
            if (!valid){
                binding.editTitle.error = "Article must have a title"
            } else {
                binding.editTitle.error = null
            }
        }

        binding.lifecycleOwner = this
        binding.addArticleViewModel = viewModel

        return binding.root
    }
}