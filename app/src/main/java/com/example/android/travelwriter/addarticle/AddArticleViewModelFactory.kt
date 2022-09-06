package com.example.android.travelwriter.addarticle

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.travelwriter.database.ArticleDao

class AddArticleViewModelFactory(
    private val dataSource: ArticleDao,
    private val articleKey: Long,
    private val sharedPrefs: SharedPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddArticleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddArticleViewModel(dataSource, articleKey, sharedPrefs) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}