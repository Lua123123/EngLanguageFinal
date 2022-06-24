package com.example.englanguage.adapter

import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.englanguage.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguage.ExoActivity

class ExoMenuAdapter(
    private val name: List<String>,
    private val images: List<Int>,
    private val context: Context
) : RecyclerView.Adapter<ExoMenuAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_exo_topic, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.tvTopic.text = name[position]
        holder.imgTopic.setImageResource(images[position])
        holder.layoutCardView.setOnClickListener {
            val word = name[position]
            val intent = Intent(context, ExoActivity::class.java)
            val bundle = Bundle()
            bundle.putString("word", word)
            intent.putExtra("data", bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return name.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTopic: TextView
        var imgTopic: ImageView
        val layoutCardView: CardView

        init {
            tvTopic = itemView.findViewById(R.id.tv_topic)
            imgTopic = itemView.findViewById(R.id.img_topic)
            layoutCardView = itemView.findViewById(R.id.layoutCardView)
        }
    }
}