package com.example.android.travelwriter.drafts

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.travelwriter.R
import com.example.android.travelwriter.database.Article
import com.example.android.travelwriter.main.ArticlesStatus

@BindingAdapter("articleTitleString")
fun TextView.setArticleTitleString(item: Article?){
    item?.let{
        text = it.title
    }
}

@BindingAdapter("articleAuthorString")
fun TextView.setArticleAuthorString(item: Article?){
    item?.let {
        text = it.author
    }
}

@BindingAdapter("articleBodyString")
fun TextView.setArticleBodyString(item: Article?){
    item?.let {
        text = it.body
    }
}

@BindingAdapter("articlesStatus")
fun ImageView.setArticlesStatus(item: ArticlesStatus?){
        when(item){
            ArticlesStatus.DONE -> {
                contentDescription = ""
                visibility = View.GONE
            }
            ArticlesStatus.LOADING -> {
                contentDescription = "Loading Articles"
                visibility = View.VISIBLE
                setImageResource(R.drawable.loading_animation)
            }
            else -> {
                contentDescription = "Error: Check your internet connection"
                visibility = View.VISIBLE
                setImageResource(R.drawable.ic_connection_error)
            }
    }
}