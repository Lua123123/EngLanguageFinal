package com.example.englanguage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englanguage.adapter.ListTopicAdapter
import com.example.englanguage.databinding.ActivityTopicBinding
import com.example.englanguage.model.topic.Success
import com.example.englanguage.viewmodel.TopicViewModel

class TopicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopicBinding
    private lateinit var adapter: ListTopicAdapter
    private val postsList: MutableList<Success> = ArrayList()
    private val context: Context = this@TopicActivity
    private var topicViewModel: TopicViewModel = TopicViewModel(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_topic)
        supportActionBar?.hide()

        binding.recyclerView.apply {
            val itemDecoration = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            itemDecoration.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.divider_rcv)!!)
            this.addItemDecoration(itemDecoration)
            this.layoutManager = LinearLayoutManager(applicationContext)
        }
        adapter = ListTopicAdapter(postsList, context)
        binding.recyclerView.adapter = adapter

        binding.imgBack.setOnClickListener {
            val intentMainActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(intentMainActivity)
        }

        topicViewModel.mClickGetTopic(postsList, adapter)
    }

    override fun onBackPressed() {
        val intent5 = Intent(this@TopicActivity, MainActivity::class.java)
        startActivity(intent5)
        super.onBackPressed()
    }
}