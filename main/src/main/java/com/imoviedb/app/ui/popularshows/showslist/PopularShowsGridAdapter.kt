package com.imoviedb.app.ui.popularshows.showslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.imoviedb.app.data.networking.utils.ApiServiceUtils
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.databinding.PopularMoviesGridItemBinding
import com.squareup.picasso.Picasso

class PopularShowsGridAdapter(private val onListItemSelected: (position: Int, id: Int) -> Unit) :
    PagingDataAdapter<ShowEntityModel, PopularShowsGridAdapter.ShowsViewHolder>(ShowsComparatorDiffUtils) {

    override fun onBindViewHolder(holder: ShowsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.view.root.tag = it.id
            holder.view.gridItemTitle.text = it.originalTitle
            Picasso.with(holder.itemView.context)
                .load(ApiServiceUtils.IMAGE_URL_PREFIX + it.posterPath)
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
            (position: Int, id: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(view.root) {
        init {
            view.root.setOnClickListener {
                onListItemSelected(layoutPosition, it.tag as Int)
            }
        }
    }

    //Dif utils comparator for updating or using existing item
    object ShowsComparatorDiffUtils : DiffUtil.ItemCallback<ShowEntityModel>() {
        override fun areItemsTheSame(
            oldItem: ShowEntityModel,
            newItem: ShowEntityModel
        ): Boolean {
            // Id is unique.
            return oldItem.insertOrder == newItem.insertOrder
        }

        override fun areContentsTheSame(
            oldItem: ShowEntityModel,
            newItem: ShowEntityModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}