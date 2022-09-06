package com.example.android.travelwriter.main

import android.util.Log
import androidx.lifecycle.*
import com.example.android.travelwriter.database.Article
import com.example.android.travelwriter.network.PostsApi
import kotlinx.coroutines.launch

enum class ArticlesStatus {LOADING, ERROR, DONE}

class MainViewModel: ViewModel() {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    private val _status = MutableLiveData<ArticlesStatus?>()
    val status: LiveData<ArticlesStatus?>
        get() = _status

    private val _navigateToSelectedArticle = MutableLiveData<Article?>()
    val navigateToSelectedArticle: LiveData<Article?>
        get() = _navigateToSelectedArticle
    fun displayArticle(article: Article) {
        _navigateToSelectedArticle.value = article
    }
    fun displayArticleComplete() {
        _navigateToSelectedArticle.value = null
    }

    init {
        _status.value = ArticlesStatus.DONE
        getArticlesFormatted()
    }

    private fun getArticlesFormatted() {
        viewModelScope.launch {
            _status.value = ArticlesStatus.LOADING
            try {
                val listData = PostsApi.retrofitService.getArticlesAsync()
                _articles.value = listData
                _status.value = ArticlesStatus.DONE
            } catch (t: Throwable) {
                _status.value = ArticlesStatus.ERROR
                _articles.value = mutableListOf<Article>()
            }
        }
    }
}