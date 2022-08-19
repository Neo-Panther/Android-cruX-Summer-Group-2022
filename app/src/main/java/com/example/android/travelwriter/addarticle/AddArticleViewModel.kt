package com.example.android.travelwriter.addarticle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.travelwriter.database.Article
import com.example.android.travelwriter.database.ArticleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddArticleViewModel(
    private val database: ArticleDao,
    private val articleKey: Long,
    private val username: String
): ViewModel() {

    private val _navigateToMain = MutableLiveData<Boolean>()
    val navigateToMain: LiveData<Boolean>
        get() = _navigateToMain
    fun doneNavigatingToMain() {
        _navigateToMain.value = false
    }

    private val _navigateToDrafts = MutableLiveData<Boolean>()
    val navigateToDrafts: LiveData<Boolean>
        get() = _navigateToDrafts
    fun doneNavigatingToDrafts() {
        _navigateToDrafts.value = false
    }

    private val _validInput = MutableLiveData<Boolean>()
    val validInput: LiveData<Boolean>
        get() = _validInput

    val currentDraft = MutableLiveData<Article>()

    init {
        _navigateToMain.value = false
        _navigateToDrafts.value = false
        _validInput.value =true

        if(articleKey==-1L){
            createNewArticle()
        } else {
            viewModelScope.launch {
                currentDraft.value = database.getArticle(articleKey)
            }
        }
    }

    private fun createNewArticle() {
        viewModelScope.launch {
            val newArticle = Article()
            newArticle.author = username
            currentDraft.value = newArticle
        }
    }

    fun onPost(){
        if (currentDraft.value!!.title.isEmpty()){
            _validInput.value = false
            return
        } else {
            _validInput.value = true
        }
        viewModelScope.launch {
            insert(currentDraft.value!!)
        }
        _navigateToMain.value=true
    }
    fun onSaveAsDraft(){
        if (currentDraft.value!!.title.isEmpty()){
            _validInput.value = false
            return
        } else {
            _validInput.value = true
        }
        viewModelScope.launch {
            insert(currentDraft.value!!)
        }
        _navigateToDrafts.value=true
    }

    private suspend fun insert(article: Article){
        withContext(Dispatchers.IO){
            database.insertArticle(article)
        }
    }
}