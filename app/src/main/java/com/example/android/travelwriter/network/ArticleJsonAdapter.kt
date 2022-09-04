package com.example.android.travelwriter.network

import com.example.android.travelwriter.database.Article
import com.squareup.moshi.ToJson

class ArticleJsonAdapter {
    @ToJson
    fun articleToJson(article: Article): ArticleJson {
        return ArticleJson(title = article.title, body = article.body)
    }
}

data class ArticleJson(
    val title: String,
    val body: String
)