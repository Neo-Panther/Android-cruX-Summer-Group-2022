package com.example.android.travelwriter.drafts

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.travelwriter.database.Article

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