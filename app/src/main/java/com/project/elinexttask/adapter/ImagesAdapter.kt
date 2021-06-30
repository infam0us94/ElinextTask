package com.project.elinexttask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.elinexttask.R
import com.project.elinexttask.api.model.Image

class ImagesAdapter(private val context: Context, private var list: ArrayList<Image>) :
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    fun setImages(list: ArrayList<Image>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setImage(img: Image) {
        list.add(img)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image: ImageView = itemView.findViewById(R.id.image)

        fun bind(data: Image) {

            Glide.with(context)
                .load(data.url)
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_view_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}