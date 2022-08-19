package com.example.android.travelwriter.drafts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.travelwriter.R
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

        (activity as AppCompatActivity).supportActionBar?.title="My Drafts"
        return binding.root
    }
}