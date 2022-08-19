package com.example.android.travelwriter.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticleDao {
    @Insert
    suspend fun insertArticle(article: Article)

    @Update
    suspend fun updateArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * from drafts WHERE id = :id")
    fun getArticle(id: Long): Article?

    @Query("SELECT * FROM drafts ORDER BY id DESC")
    fun getAllArticles(): LiveData<List<Article>>

    @Query("DELETE FROM drafts WHERE id=:id")
    fun deleteArticleWithId(id: Long)
}