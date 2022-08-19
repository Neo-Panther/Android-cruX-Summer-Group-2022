package com.example.android.travelwriter.firsttime

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.travelwriter.MainActivity.Companion.USERNAME_KEY

class FirstTimeViewModel(
    private val sharedPrefs: SharedPreferences
): ViewModel() {
    private val username = sharedPrefs.getString(USERNAME_KEY, null)

    private var _navigateToMain = MutableLiveData<Boolean>()
    val navigateToMain: LiveData<Boolean>
        get() = _navigateToMain
    fun doneNavigating() {
        _navigateToMain.value = false
    }
    init {
        _navigateToMain.value = username!=null
    }

    fun onProceed(data: String){
        with (sharedPrefs.edit()) {
            putString(USERNAME_KEY, data)
            apply()
        }
        _navigateToMain.value=true
    }
}