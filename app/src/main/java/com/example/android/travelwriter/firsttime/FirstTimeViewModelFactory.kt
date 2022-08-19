package com.example.android.travelwriter.firsttime

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FirstTimeViewModelFactory(
    private val sharedPrefs: SharedPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstTimeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FirstTimeViewModel(sharedPrefs) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}