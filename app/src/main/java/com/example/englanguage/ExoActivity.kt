package com.example.englanguage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.englanguage.exoviewpager.VerticalViewPager
import com.example.englanguage.exoviewpager.ViewPagerAdapter

class ExoActivity : AppCompatActivity() {
    private lateinit var viewPager: VerticalViewPager
    private lateinit var viewAdapter: ViewPagerAdapter
    private lateinit var word: String
    private val context: Context = this@ExoActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exo)
        supportActionBar?.hide()

        val intent = intent
        val bundle = intent.getBundleExtra("data")
        word = bundle?.getString("word").toString()

        viewPager = findViewById<View>(R.id.viewPager) as VerticalViewPager
        viewAdapter = ViewPagerAdapter(supportFragmentManager, context, word)
        viewPager.adapter = viewAdapter
    }
}