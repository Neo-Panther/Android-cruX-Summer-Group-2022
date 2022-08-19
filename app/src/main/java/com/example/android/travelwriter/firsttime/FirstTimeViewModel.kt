package com.example.android.travelwriter.firsttime

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.travelwriter.MainActivity.Companion.USERNAME_KEY
import kotlinx.coroutines.launch

class FirstTimeViewModel(
    private val sharedPrefs: SharedPreferences
): ViewModel() {
    private var _navigateToMain = MutableLiveData<Boolean>()
    val navigateToMain: LiveData<Boolean>
        get() = _navigateToMain
    fun doneNavigating() {
        _navigateToMain.value = false
    }
    init {
        _navigateToMain.value = false
    }

    fun onProceed(data: String){
        viewModelScope.launch {
            with(sharedPrefs.edit()) {
                putString(USERNAME_KEY, data)
                apply()
            }
        }
        _navigateToMain.value=true
    }
}