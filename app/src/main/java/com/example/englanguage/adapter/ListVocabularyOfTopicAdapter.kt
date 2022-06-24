package com.example.englanguage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguage.R
import com.example.englanguage.adapter.ListVocabularyOfTopicAdapter.ListVocabularyOfTopicViewHolder
import com.example.englanguage.model.vocabulary.SuccessVocabulary

class ListVocabularyOfTopicAdapter(
    private val postsList: List<SuccessVocabulary>?,
    private val context: Context
) : RecyclerView.Adapter<ListVocabularyOfTopicViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListVocabularyOfTopicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_vocabulary_of_topic_item, parent, false)
        return ListVocabularyOfTopicViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListVocabularyOfTopicViewHolder, position: Int) {

        val successVocabulary = postsList!![position]
        val word = postsList[position].word
        val mean = postsList[position].mean
        holder.tvWord.text = successVocabulary.word
        holder.tvMean.text = successVocabulary.mean

        holder.layoutItem.setOnClickListener {
//            val intent = Intent(context, ExoPlayerActivity::class.java)
//            intent.putExtra("word", word)
//            intent.putExtra("mean", mean)
//            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return postsList?.size ?: 0
    }

    inner class ListVocabularyOfTopicViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvWord: TextView
        val tvMean: TextView
        var layoutItem: LinearLayout

        init {
            tvWord = itemView.findViewById(R.id.word)
            tvMean = itemView.findViewById(R.id.mean)
            layoutItem = itemView.findViewById(R.id.layout_item)
        }
    }
}
