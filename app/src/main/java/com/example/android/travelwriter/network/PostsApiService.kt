package com.example.android.travelwriter.network

import com.example.android.travelwriter.BuildConfig.BASE_URL
import com.example.android.travelwriter.database.Article
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(ArticleJsonAdapter())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL).build()

interface PostsApiService {
    @POST("users/{author}.json")
    suspend fun postArticleAsync(@Path("author") author: String, @Body article: Article) : Response<ResponseBody>
}

object PostsApi {
    val retrofitService : PostsApiService by lazy {
        retrofit.create(PostsApiService::class.java)
    }
}