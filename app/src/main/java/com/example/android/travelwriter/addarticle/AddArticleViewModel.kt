package com.example.android.travelwriter.addarticle

import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.travelwriter.database.Article
import com.example.android.travelwriter.database.ArticleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.nio.file.Files.delete

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
                var temp: Article?
                withContext(Dispatchers.IO) {
                    temp = database.getArticle(articleKey)
                }
                if (temp != null){
                    currentDraft.value = temp!!
                } else {
                    createNewArticle()
                }
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
            if (articleKey != -1L) {
                delete(articleKey)
            }
            insert(currentDraft.value!!)
            // TODO Instead of above, write to firestore db
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

    private suspend fun delete(articleId: Long){
        withContext(Dispatchers.IO){
            database.deleteArticleWithId(articleId)
        }
    }
}