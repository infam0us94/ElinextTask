package com.project.elinexttask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.elinexttask.R
import com.project.elinexttask.api.model.Image

class RecyclerViewAdapter() :
    PagingDataAdapter<Image, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallback()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.rec_view_row, parent, false)
        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById(R.id.image)

        fun bind(data: Image) {
            Glide.with(image)
                .load(data.url)
                .into(image)
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean =
            oldItem == newItem
    }
}