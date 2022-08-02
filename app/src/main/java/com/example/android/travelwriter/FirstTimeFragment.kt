package com.example.android.travelwriter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.android.travelwriter.databinding.FragmentFirstTimeBinding

class FirstTimeFragment : Fragment() {
    private lateinit var binding: FragmentFirstTimeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentFirstTimeBinding>(inflater, R.layout.fragment_first_time,
        container, false)

        binding.proceedButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(FirstTimeFragmentDirections.actionFirstTimeFragmentToMainFragment())
        )
        return binding.root
    }
}