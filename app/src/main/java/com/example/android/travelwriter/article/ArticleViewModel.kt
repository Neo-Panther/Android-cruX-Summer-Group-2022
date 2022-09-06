package com.example.android.travelwriter.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.travelwriter.database.Article

class ArticleViewModel(article: Article): ViewModel() {
    private val _selectedArticle = MutableLiveData<Article>()
    val selectedArticle: LiveData<Article>
        get() = _selectedArticle
    init {
        _selectedArticle.value = article
    }
}