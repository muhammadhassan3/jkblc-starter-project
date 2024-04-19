package com.muhammhassan.jkblc_starter_project.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.muhammhassan.jkblc_starter_project.R
import com.muhammhassan.jkblc_starter_project.core.model.Article
import com.muhammhassan.jkblc_starter_project.databinding.LayoutItemListBinding

class NewsAdapter(
    private val list: MutableList<Article>,
    private val onItemClick: (item: Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder>() {

    fun updateData(data: List<Article>) {
        val differ = Article.DiffUtils(list, data)
        val result = DiffUtil.calculateDiff(differ)
        list.clear()
        list.addAll(data)
        result.dispatchUpdatesTo(this)
    }

    inner class NewsItemViewHolder(val binding: LayoutItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            binding.apply {
                Glide.with(binding.root.context).load(item.urlToImage).centerCrop()
                    .transition(withCrossFade(factory)).error(R.drawable.baseline_broken_image_24)
                    .into(imgHeader)
                tvTitle.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val binding =
            LayoutItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClick.invoke(list[position])
        }
    }
}