package com.example.android.travelwriter.details

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.travelwriter.addarticle.AddArticleViewModel
import com.example.android.travelwriter.database.ArticleDao

class DetailsViewModelFactory(
    private val app: Application,
    private val database: ArticleDao,
    private val sharedPrefs: SharedPreferences
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailsViewModel(app, database, sharedPrefs) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}