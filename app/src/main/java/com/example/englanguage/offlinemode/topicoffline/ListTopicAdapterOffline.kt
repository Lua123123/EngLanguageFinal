package com.example.englanguage.offlinemode.topicoffline

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.englanguage.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.example.englanguage.offlinemode.vocabularyoftopicoffline.VocabularyOfTopicOffActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.LinearLayout
import com.example.englanguage.model.topic.Success

class ListTopicAdapterOffline(
    private var postsList: List<Success>?,
    private val context: Context,
    private val Authorization: String
) : RecyclerView.Adapter<ListTopicAdapterOffline.ViewHolder>() {
    fun setData(postsList: List<Success>?) {
        this.postsList = postsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_topic_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val success = postsList!![position]
        val nameTopic = postsList!![position].name
        val id = postsList!![position].id
        holder.tvTitle.text = success.name
        holder.tvMount.text = success.soluong.toString()
        holder.itemTopic.setOnClickListener {
            val intent = Intent(context, VocabularyOfTopicOffActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("id", id)
            bundle.putString("nameTopic", nameTopic)
            intent.putExtra("data", bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return if (postsList != null) {
            postsList!!.size
        } else 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView
        val tvMount: TextView
        val itemTopic: LinearLayout

        init {
            tvTitle = itemView.findViewById(R.id.topic)
            tvMount = itemView.findViewById(R.id.amount)
            itemTopic = itemView.findViewById(R.id.item_topic)
        }
    }
}