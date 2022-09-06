package com.example.android.travelwriter.network

import com.example.android.travelwriter.database.Article
import com.squareup.moshi.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

data class ArticleJson(
    @Json(name = "title")
    val title: String,

    @Json(name = "body")
    val body: String
)

class ManualParser: JsonAdapter<List<Article>>() {
    @FromJson
    override fun fromJson(reader: JsonReader): List<Article> {
        val result = mutableListOf<Article>()
        var idn = 0L
        reader.beginObject()
        while (reader.hasNext()){
            var id: Long
            val author: String = reader.nextName()
            var title = ""
            var body = ""
            if (reader.hasNext()) {
                reader.beginObject()
                while (reader.hasNext()) {
                    reader.nextName()
                    reader.beginObject()
                    while (reader.hasNext()) {
                        idn += 1
                        id = idn
                        when (reader.nextName()) {
                            "title" -> title = reader.nextString()
                            "body" -> body = reader.nextString()
                            else -> reader.skipValue()
                        }
                        val article = Article(id, author, title, body)
                        result.add(article)
                    }
                    reader.endObject()
                }
            }
            reader.endObject()
        }
        reader.endObject()

        return  result
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: List<Article>?) {
        return
    }
}

class ManualParserFactory: JsonAdapter.Factory {
    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        if (annotations.isNotEmpty()){
            return null
        }
        return ManualParser().nullSafe()
    }

}