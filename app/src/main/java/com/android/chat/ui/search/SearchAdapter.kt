package com.android.chat.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.chat.R
import com.android.chat.core.Abstract
import com.android.chat.databinding.SearchGroupResultBinding
import com.android.chat.databinding.SearchUserResultBinding
import com.android.chat.ui.core.ClickListener

class SearchAdapter(
    private val clickListener: ClickListener<SearchResultUi>
) : RecyclerView.Adapter<SearchResultViewHolder>(),
    Abstract.Mapper.Data<List<SearchResultUi>, Unit> {

    private val list = ArrayList<SearchResultUi>()

    override fun getItemViewType(position: Int) = when (list[position]) {
        is SearchResultUi.User -> 0
        is SearchResultUi.Empty -> 1
        is SearchResultUi.NoResults -> 2
        is SearchResultUi.Search -> 3
        is SearchResultUi.Group -> 4
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> SearchResultViewHolder.User(
            SearchUserResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), clickListener
        )
        1 -> SearchResultViewHolder.Progress(R.layout.progress.view(parent))
        2 -> SearchResultViewHolder.NoResults(R.layout.no_results.view(parent))
        3 -> SearchResultViewHolder.Initial(R.layout.search.view(parent))
        4 -> SearchResultViewHolder.Group(
            SearchGroupResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), clickListener
        )
        else -> throw IllegalStateException("unknown viewType $viewType")
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size

    override fun map(data: List<SearchResultUi>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged() //todo diffutilcallback
    }
}

private fun Int.view(parent: ViewGroup) =
    LayoutInflater.from(parent.context).inflate(this, parent, false)

abstract class SearchResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: SearchResultUi) = Unit

    class User(
        private val binding: SearchUserResultBinding,
        private val clickListener: ClickListener<SearchResultUi>
    ) : SearchResultViewHolder(binding.root) {

        override fun bind(item: SearchResultUi) {
            item.map(binding.avatarImageView, binding.userNameTextView)
            binding.chatButton.setOnClickListener { clickListener.click(item) }
        }
    }

    class Group(
        private val binding: SearchGroupResultBinding,
        private val clickListener: ClickListener<SearchResultUi>
    ) : SearchResultViewHolder(binding.root) {

        override fun bind(item: SearchResultUi) {
            item.map(binding.avatarImageView, binding.userNameTextView)
            binding.chatButton.setOnClickListener { clickListener.click(item) }
        }
    }
    class Initial(view: View) : SearchResultViewHolder(view)
    class Progress(view: View) : SearchResultViewHolder(view)
    class NoResults(view: View) : SearchResultViewHolder(view)
}