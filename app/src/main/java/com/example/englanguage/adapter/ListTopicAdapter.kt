package com.example.englanguage.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.englanguage.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.example.englanguage.VocabularyOfTopicActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.LinearLayout
import com.example.englanguage.model.topic.Success

class ListTopicAdapter(
    private val postsList: List<Success>?,
    private val context: Context
) : RecyclerView.Adapter<ListTopicAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_topic_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val success = postsList!![position]
        val nameTopic = postsList[position].name
        val id = postsList[position].id
        holder.tvTitle.text = success.name
        holder.tvMount.text = success.soluong.toString()
        holder.itemTopic.setOnClickListener {
            val intent = Intent(context, VocabularyOfTopicActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("id", id)
            bundle.putString("nameTopic", nameTopic)
            intent.putExtra("data", bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return postsList?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView
        val tvMount: TextView
        var itemTopic: LinearLayout

        init {
            tvTitle = itemView.findViewById(R.id.topic)
            tvMount = itemView.findViewById(R.id.amount)
            itemTopic = itemView.findViewById(R.id.item_topic)
        }
    }
}