package com.imoviedb.app.presentation.ui.popularshows.showslist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import com.imoviedb.app.presentation.databinding.PopularMoviesGridItemBinding
import com.imoviedb.app.presentation.ui.utils.UrlUtils
import com.squareup.picasso.Picasso

class PopularShowsGridAdapter(private val onListItemSelected: (id: Int) -> Unit) :
    PagingDataAdapter<ShowDomainModel, PopularShowsGridAdapter.ShowsViewHolder>(
        ShowsComparatorDiffUtils
    ) {

    override fun onBindViewHolder(holder: ShowsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.view.root.tag = it.id
            Picasso.with(holder.itemView.context)
                .load(UrlUtils.IMAGE_URL_PREFIX + it.posterPath)
                .into(holder.view.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PopularMoviesGridItemBinding.inflate(inflater, parent, false)
        return ShowsViewHolder(binding, onListItemSelected)
    }

    class ShowsViewHolder(
        val view: PopularMoviesGridItemBinding, val onListItemSelected:
            (id: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(view.root) {
        init {
            view.root.setOnClickListener {
                onListItemSelected(it.tag as Int)
            }
        }
    }

    //Dif utils comparator for updating or using existing item
    object ShowsComparatorDiffUtils : DiffUtil.ItemCallback<ShowDomainModel>() {
        override fun areItemsTheSame(
            oldItem: ShowDomainModel,
            newItem: ShowDomainModel
        ): Boolean {
            // Id is unique.
            return oldItem.insertOrder == newItem.insertOrder
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: ShowDomainModel,
            newItem: ShowDomainModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}