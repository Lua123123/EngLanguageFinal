package com.example.englanguage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englanguage.adapter.ExoMenuAdapter
import com.example.englanguage.databinding.ActivityExoMenuBinding
import com.example.englanguage.utils.GridItemDecoration

class ExoMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExoMenuBinding
    private lateinit var adapter: ExoMenuAdapter
    private val listImageTopic: MutableList<Int> = ArrayList()
    private val listNameTopic: MutableList<String> = ArrayList()
    private val context: Context = this@ExoMenuActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exo_menu)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exo_menu)
        supportActionBar?.hide()

        listNameTopic.add("Contracts")
        listImageTopic.add(R.drawable.contracts)

        listNameTopic.add("Marketing")
        listImageTopic.add(R.drawable.marketing)

        listNameTopic.add("Warranties")
        listImageTopic.add(R.drawable.warranties)

        listNameTopic.add("Business planning")
        listImageTopic.add(R.drawable.business_planning)

        listNameTopic.add("Conferences")
        listImageTopic.add(R.drawable.conferences)

        listNameTopic.add("Computers and the internet")
        listImageTopic.add(R.drawable.computers_and_the_internet)

        listNameTopic.add("Office technology")
        listImageTopic.add(R.drawable.office_technology)

        listNameTopic.add("Office procedures")
        listImageTopic.add(R.drawable.office_procedures)

        listNameTopic.add("Electronics")
        listImageTopic.add(R.drawable.electronics)

        listNameTopic.add("Correspondence")
        listImageTopic.add(R.drawable.correspondence)

        listNameTopic.add("Job ads and recruitment")
        listImageTopic.add(R.drawable.job_ads_and_recruitment)

        listNameTopic.add("Apply and interviewing")
        listImageTopic.add(R.drawable.apply_and_interviewing)

        binding.recyclerView.apply {
            addItemDecoration(GridItemDecoration(spanCount = 2, spacing = 16, includeEdge = false))
            this.layoutManager = GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
        }
        adapter = ExoMenuAdapter(listNameTopic, listImageTopic, context)
        binding.recyclerView.adapter = adapter

    }
}