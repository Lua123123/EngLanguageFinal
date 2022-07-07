package com.example.englanguage.offlinemode.vocabularyoftopicoffline

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguage.R
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import com.example.englanguage.offlinemode.OneVocabularyOffActivity

class ListVocabularyOfTopicAdapterOffline(
) : RecyclerView.Adapter<ListVocabularyOfTopicAdapterOffline.ListVocabularyOfTopicOfflineViewHolder>() {
    private var postsList: List<SuccessVocabulary>? = null
    private var context: Context? = null

    fun setData(mListVocabulary: MutableList<SuccessVocabulary> = mutableListOf<SuccessVocabulary>(), context: Context) {
        this.postsList = mListVocabulary
        this.context = context
        postsList = mListVocabulary
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListVocabularyOfTopicOfflineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_vocabulary_of_topic_item_off, parent, false)
        return ListVocabularyOfTopicOfflineViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListVocabularyOfTopicOfflineViewHolder, position: Int) {

        val successVocabulary = postsList!![position] ?: return
        val word = postsList!![position].word
        val mean = postsList!![position].mean
        holder.tvWord.text = successVocabulary.word
        holder.tvMean.text = successVocabulary.mean

        holder.layoutItem.setOnClickListener {
            val intent = Intent(context, OneVocabularyOffActivity::class.java)
            intent.putExtra("word", word)
            intent.putExtra("mean", mean)
            context!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return postsList?.size ?: 0
    }

    inner class ListVocabularyOfTopicOfflineViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvWord: TextView
        val tvMean: TextView
        val layoutItem: LinearLayout

        init {
            tvWord = itemView.findViewById(R.id.word)
            tvMean = itemView.findViewById(R.id.mean)
            layoutItem = itemView.findViewById(R.id.layout_item)
        }
    }
}
