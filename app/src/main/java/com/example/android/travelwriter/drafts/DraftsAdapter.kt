package com.example.android.travelwriter.drafts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.travelwriter.database.Article
import com.example.android.travelwriter.databinding.DraftRowBinding
import com.example.android.travelwriter.generated.callback.OnClickListener

class DraftsDiffCallback: DiffUtil.ItemCallback<Article>(){
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}

class DraftsAdapter(private val clickListener: ArticleClickListener)
    : ListAdapter<Article, RecyclerView.ViewHolder>(DraftsDiffCallback()) {

    //private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val item = getItem((position)) as Article
                holder.bind(item, clickListener)
            }
        }
    }

    class ViewHolder private constructor(val binding: DraftRowBinding): RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from((parent.context))
                val binding = DraftRowBinding.inflate(layoutInflater, parent, false)
                return  ViewHolder(binding)
            }
        }

        fun bind(
            item: Article,
            clickListener: ArticleClickListener
        ){
            binding.article = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}

class ArticleClickListener(val clickDeleteListener: (articleId: Long) -> Unit,
                           val clickEditListener: (articleId: Long) -> Unit){
    fun onClickDelete(article: Article) = clickDeleteListener(article.id)
    fun onClickEdit(article: Article) = clickEditListener(article.id)
}