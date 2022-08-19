package com.example.android.travelwriter.drafts

import androidx.lifecycle.ViewModel
import com.example.android.travelwriter.database.ArticleDao

class DraftsViewModel(
    private val database: ArticleDao
): ViewModel() {
    val articles = database.getAllArticles()
}