package com.example.android.travelwriter.firsttime

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.travelwriter.R
import com.example.android.travelwriter.databinding.FragmentFirstTimeBinding

class FirstTimeFragment : Fragment() {
    private lateinit var binding: FragmentFirstTimeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first_time,
        container, false)

        val sharedPrefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val viewModelFactory = FirstTimeViewModelFactory(sharedPrefs)
        val viewModel = ViewModelProvider(this, viewModelFactory)[FirstTimeViewModel::class.java]

        binding.proceedButton.setOnClickListener{
            if (binding.enterName.length() != 0){
                binding.enterName.error = null
                (activity as AppCompatActivity).supportActionBar?.subtitle="by ${binding.enterName.text}"
                viewModel.onProceed(binding.enterName.text.toString())
            } else {
                binding.enterName.error = "Please enter your name"
            }
        }

        viewModel.navigateToMain.observe(viewLifecycleOwner) { go ->
            if (go) {
                this.findNavController().navigate(
                    FirstTimeFragmentDirections.actionFirstTimeFragmentToMainFragment()
                )
                viewModel.doneNavigating()
            }
        }

        (activity as AppCompatActivity).supportActionBar?.title="Welcome"
        return binding.root
    }
}