package com.example.englanguage.offlinemode.vocabularyoftopicoffline

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguage.R
import com.example.englanguage.database.VocabularyDatabase
import com.example.englanguage.model.vocabulary.SuccessVocabulary

class VocabularyOfTopicOffActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: ListVocabularyOfTopicAdapterOffline
    private lateinit var tv_topic: TextView
    private var postsList: List<SuccessVocabulary> = ArrayList()
    private var postsListTotal: List<SuccessVocabulary> = ArrayList()
    private val context: Context = this@VocabularyOfTopicOffActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary_of_topic_off)
        supportActionBar!!.hide()

        val intent = intent
        val bundle = intent.getBundleExtra("data")
        val position = bundle!!.getInt("id")
        val nameTopic = bundle.getString("nameTopic")
        tv_topic = findViewById(R.id.tv_topic)
        tv_topic.text = nameTopic

        recyclerView = findViewById(R.id.rcv_vocabulary_of_topic)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = ListVocabularyOfTopicAdapterOffline()
        recyclerView.adapter = adapter

        val itemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(this.resources.getDrawable(R.drawable.divider_rcv))
        recyclerView.addItemDecoration(itemDecoration)

        postsList = VocabularyDatabase.getInstance(context)?.VocabularyOfTopicDAO()
            ?.getListVocabularyOfTopic() as MutableList<SuccessVocabulary>

        if (position == 1) {
            postsListTotal = listOf(
                postsList[0], postsList[1], postsList[2], postsList[3], postsList[4], postsList[5],
                postsList[6], postsList[7], postsList[8], postsList[9], postsList[10], postsList[11]
            )
            adapter.setData(postsListTotal as MutableList<SuccessVocabulary>, context)
            recyclerView.adapter = adapter
        }
        if (position == 2) {
            postsListTotal = listOf(
                postsList[12], postsList[13], postsList[14], postsList[15], postsList[16], postsList[17],
                postsList[18], postsList[19], postsList[20], postsList[21], postsList[22], postsList[23]
            )
            adapter.setData(postsListTotal as MutableList<SuccessVocabulary>, context)
            recyclerView.adapter = adapter
        }
        if (position == 3) {
            postsListTotal = listOf(
                postsList[24], postsList[25], postsList[26], postsList[27], postsList[28], postsList[29],
                postsList[30], postsList[31], postsList[32], postsList[33], postsList[34], postsList[35]
            )
            adapter.setData(postsListTotal as MutableList<SuccessVocabulary>, context)
            recyclerView.adapter = adapter
        }
        if (position == 4) {
            postsListTotal = listOf(
                postsList[36], postsList[37], postsList[38], postsList[39], postsList[40], postsList[41],
                postsList[42], postsList[43], postsList[44], postsList[45], postsList[46], postsList[47]
            )
            adapter.setData(postsListTotal as MutableList<SuccessVocabulary>, context)
            recyclerView.adapter = adapter
        }
        if (position == 5) {
            postsListTotal = listOf(
                postsList[48], postsList[49], postsList[50], postsList[51], postsList[52], postsList[53],
                postsList[54], postsList[55], postsList[56], postsList[57], postsList[58], postsList[59]
            )
            adapter.setData(postsListTotal as MutableList<SuccessVocabulary>, context)
            recyclerView.adapter = adapter
        }
    }
}