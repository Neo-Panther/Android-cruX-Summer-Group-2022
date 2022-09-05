package com.example.android.travelwriter.network

import com.example.android.travelwriter.database.Article
import com.squareup.moshi.Json
import com.squareup.moshi.JsonReader

data class ArticleJson(
    @Json(name = "title")
    val title: String,

    @Json(name = "body")
    val body: String
)

class ManualParser {
    fun parse(reader: JsonReader): List<Article> {
        val result = mutableListOf<Article>()
        var idn = 0L
        reader.beginObject()
        while (reader.hasNext()){
            var id: Long = idn
            var author: String = reader.nextName()
            var title: String = ""
            var body: String = ""

            reader.beginObject()
            while (reader.hasNext()){
                reader.beginObject()
                while (reader.hasNext()){
                    idn += 1
                    id = idn
                    when (reader.nextName()){
                        "title" -> title = reader.nextString()
                        "body" -> body = reader.nextString()
                        else ->reader.skipValue()
                    }
                    val article = Article(id, author, title, body)
                    result.add(article)
                }
                reader.endObject()
            }
            reader.endObject()

        }
        reader.endObject()

        return  result
    }
}