package com.example.englanguage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englanguage.adapter.ListVocabularyOfTopicAdapter
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import com.example.englanguage.viewmodel.VocabularyOfTopicViewModel
import android.os.Bundle
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.englanguage.databinding.ActivityVocabularyOfTopicBinding
import java.util.ArrayList

class VocabularyOfTopicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVocabularyOfTopicBinding
    private lateinit var adapter: ListVocabularyOfTopicAdapter
    private val postsList: List<SuccessVocabulary> = ArrayList()
    private val context: Context = this@VocabularyOfTopicActivity
    private var vocabularyOfTopicViewModel: VocabularyOfTopicViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary_of_topic)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vocabulary_of_topic)
        supportActionBar?.hide()

        val intent = intent
        val bundle = intent.getBundleExtra("data")
        val position = bundle!!.getInt("id")
        val nameTopic = bundle.getString("nameTopic")

        binding.tvTopic.text = nameTopic

        binding.rcvVocabularyOfTopic.apply {
            val itemDecoration = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            itemDecoration.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.divider_rcv)!!)
            this.addItemDecoration(itemDecoration)
            this.layoutManager = LinearLayoutManager(applicationContext)
        }
        adapter = ListVocabularyOfTopicAdapter(postsList, context)
        binding.rcvVocabularyOfTopic.adapter = adapter

        binding.imgBack.setOnClickListener{
            val intentTopicActivity =
                Intent(this@VocabularyOfTopicActivity, TopicActivity::class.java)
            startActivity(intentTopicActivity)
        }

        vocabularyOfTopicViewModel = VocabularyOfTopicViewModel(adapter,
            postsList as MutableList<SuccessVocabulary>, context)
        vocabularyOfTopicViewModel!!.mGetVocabularyOfTopic(position)
    }

    override fun onBackPressed() {
        val intent5 = Intent(this@VocabularyOfTopicActivity, TopicActivity::class.java)
        startActivity(intent5)
        super.onBackPressed()
    }
}