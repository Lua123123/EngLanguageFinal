package com.example.englanguage

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englanguage.adapter.ListTopicAdapter
import com.example.englanguage.databinding.ActivityTopicBinding
import com.example.englanguage.extensions.launchActivity
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
            addItemDecoration(itemDecoration)
            layoutManager = LinearLayoutManager(applicationContext)
        }
        adapter = ListTopicAdapter(postsList, context)
        binding.recyclerView.adapter = adapter

        binding.imgBack.setOnClickListener {
            launchActivity(MainActivity::class.java)
        }

        topicViewModel.mClickGetTopic(postsList, adapter)
    }

    override fun onBackPressed() {
        launchActivity(MainActivity::class.java)
        super.onBackPressed()
    }
}