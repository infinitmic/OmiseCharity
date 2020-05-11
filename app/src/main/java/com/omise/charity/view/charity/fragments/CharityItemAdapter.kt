package com.omise.charity.view.charity.fragments

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.omise.charity.R
import com.omise.charity.model.Charity
import com.omise.charity.view.common.ItemAdapter
import com.omise.charity.view.common.bindView
import com.omise.charity.view.common.loadImage

class CharityItemAdapter(
    private val charity: Charity,
    val clicked: (Charity) -> Unit
) : ItemAdapter<CharityItemAdapter.ViewHolder>(R.layout.item_charity) {

    override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

    override fun ViewHolder.onBindViewHolder() {
        itemView.setOnClickListener { clicked(charity) }
        textView.text = charity.name
        imageView.loadImage(charity.urlToImage!!)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView by bindView<TextView>(R.id.tvTitle)
        val imageView by bindView<ImageView>(R.id.ivCharity)
    }
}
