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

class DraftsAdapter: ListAdapter<Article, RecyclerView.ViewHolder>(DraftsDiffCallback()) {

    //private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val item = getItem((position)) as Article
                holder.bind(item)
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
            item: Article
        ){
            binding.article = item
            binding.executePendingBindings()
        }
    }
}