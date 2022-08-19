package com.example.android.travelwriter.drafts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.travelwriter.database.ArticleDao

class DraftsViewModelFactory(
    private val dataSource: ArticleDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DraftsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DraftsViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}