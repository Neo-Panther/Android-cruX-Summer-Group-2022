package com.example.android.travelwriter.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.travelwriter.database.Article
import com.example.android.travelwriter.databinding.MainRowBinding

class MainDiffCallback: DiffUtil.ItemCallback<Article>(){
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return (oldItem.id == newItem.id) and (oldItem.author == newItem.author)
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return (oldItem.title == newItem.title) and (oldItem.body == newItem.body)
    }
}

class MainAdapter(private val clickListener: ArticleClickListener): ListAdapter<Article, RecyclerView.ViewHolder>(MainDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArticleViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ArticleViewHolder -> {
                val item = getItem((position)) as Article
                holder.bind(item, clickListener)
            }
        }
    }

    class ArticleViewHolder private constructor(val binding: MainRowBinding): RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent: ViewGroup): ArticleViewHolder{
                val layoutInflater = LayoutInflater.from((parent.context))
                val binding = MainRowBinding.inflate(layoutInflater, parent, false)
                return ArticleViewHolder(binding)
            }
        }

        fun bind(item: Article,
        clickListener: ArticleClickListener){
            binding.article = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}

class ArticleClickListener(val clickListener: (article: Article) -> Unit){
    fun onClick(article: Article) = clickListener(article)
}