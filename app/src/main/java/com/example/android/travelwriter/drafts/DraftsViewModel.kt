package com.example.android.travelwriter.drafts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.travelwriter.database.ArticleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DraftsViewModel(
    private val database: ArticleDao
): ViewModel() {
    val articles = database.getAllArticles()

    fun delete(articleId: Long){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                database.deleteArticleWithId(articleId)
            }
        }
    }
}