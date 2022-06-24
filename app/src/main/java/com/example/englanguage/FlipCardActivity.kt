package com.example.englanguage

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
class FlipCardActivity : AppCompatActivity() {
    private lateinit var imgBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip_card)
        supportActionBar?.hide()

        imgBack = findViewById(R.id.imgBack)
        imgBack.setOnClickListener {
            val intentVocabulary = Intent(applicationContext, MainActivity::class.java)
            startActivity(intentVocabulary)
        }
    }

    override fun onBackPressed() {
        val intent5 = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent5)
        super.onBackPressed()
    }
}