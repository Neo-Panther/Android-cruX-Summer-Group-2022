package com.example.android.travelwriter.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.travelwriter.database.Article
import java.lang.IllegalArgumentException

class ArticleViewModelFactory(
    private val article: Article,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(article) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}