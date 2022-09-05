package com.example.android.travelwriter.drafts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.travelwriter.database.Article
import com.example.android.travelwriter.databinding.DraftRowBinding

class DraftsDiffCallback: DiffUtil.ItemCallback<Article>(){
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}

class DraftsAdapter(private val clickListener: DraftClickListener)
    : ListAdapter<Article, RecyclerView.ViewHolder>(DraftsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DraftViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DraftViewHolder -> {
                val item = getItem((position)) as Article
                holder.bind(item, clickListener)
            }
        }
    }

    class DraftViewHolder private constructor(val binding: DraftRowBinding): RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent: ViewGroup): DraftViewHolder{
                val layoutInflater = LayoutInflater.from((parent.context))
                val binding = DraftRowBinding.inflate(layoutInflater, parent, false)
                return  DraftViewHolder(binding)
            }
        }

        fun bind(
            item: Article,
            clickListener: DraftClickListener
        ){
            binding.draft = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}

class DraftClickListener(val clickDeleteListener: (articleId: Long) -> Unit,
                         val clickEditListener: (articleId: Long) -> Unit){
    fun onClickDelete(article: Article) = clickDeleteListener(article.id)
    fun onClickEdit(article: Article) = clickEditListener(article.id)
}