package com.example.android.travelwriter.details

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.*
import com.example.android.travelwriter.MainActivity
import com.example.android.travelwriter.R
import com.example.android.travelwriter.database.ArticleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(
    app: Application,
    database: ArticleDao,
    sharedPrefs: SharedPreferences
): AndroidViewModel(app) {
    private val username =  MutableLiveData<String?>()
    private val draftsCount = database.getDraftsCount()
    private val articlesCount = MutableLiveData<Int>()
    val displayDetailsBody = MediatorLiveData<String>()

    init {
        username.value = sharedPrefs.getString(MainActivity.USERNAME_KEY, null)
        articlesCount.value = sharedPrefs.getInt(MainActivity.ARTICLES_COUNT_KEY, 0)
        println(draftsCount.value)
        displayDetailsBody.addSource(draftsCount){
            displayDetailsBody.value = app.getString(R.string.detailsBodyText, it, articlesCount.value)
        }
        displayDetailsBody.addSource(articlesCount){
            displayDetailsBody.value = app.getString(R.string.detailsBodyText, draftsCount.value, it)
        }
    }

    val displayDetailsTitle = Transformations.map(username){
        app.getString(R.string.detailsTitleText, it)
    }
}