package com.example.android.travelwriter.addarticle

import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.travelwriter.database.Article
import com.example.android.travelwriter.database.ArticleDao
import com.example.android.travelwriter.network.PostsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import java.nio.file.Files.delete
import retrofit2.Response

enum class PostStatus {POSTING, ERROR, DONE}

class AddArticleViewModel(
    private val database: ArticleDao,
    private val articleKey: Long,
    private val username: String
): ViewModel() {

    private val _status = MutableLiveData<PostStatus>()
    val status: LiveData<PostStatus>
        get() = _status

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
        _status.value = PostStatus.DONE

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
            _status.value = PostStatus.POSTING
            if (articleKey != -1L) {
                delete(articleKey)
            }
            try {
                Log.d("write result", postToDB(currentDraft.value!!).code().toString())
                _status.value = PostStatus.DONE
            } catch (t: Throwable) {
                _status.value = PostStatus.ERROR
            }

        }
        _navigateToMain.value=true
    }

    private suspend fun postToDB(article: Article): Response<ResponseBody> {
        return PostsApi.retrofitService.postArticleAsync(article.author, article)
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