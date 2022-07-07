package com.example.englanguage.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.englanguage.R
import androidx.databinding.DataBindingUtil
import com.example.englanguage.databinding.ActivityResultBinding
import com.example.englanguage.extensions.launchActivity

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var highScore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        supportActionBar?.hide()

        binding.resultBtnMainMenu.setOnClickListener {
            launchActivity(StartQuizActivity::class.java)
        }
        binding.resultBtnPlayAgain.setOnClickListener {
            launchActivity(QuizActivity::class.java)
        }

        loadHighScore()

        val intent = intent
        val score = intent.getIntExtra("UserScore", 0)
        val totalQuestions = intent.getIntExtra("TotalQuizQuestions", 0)
        val correctQuestions = intent.getIntExtra("CorrectQuestions", 0)
        val wrongQuestions = intent.getIntExtra("WrongQuestions", 0)
        binding.resultTvTotalQuestion.text = "Total Questions: $totalQuestions"
        binding.resultTvCorrect.text = "Correct Questions: $correctQuestions"
        binding.resultTvWrong.text = "Wrong Questions: $wrongQuestions"
        if (score > highScore) {
            updateScore(score)
        }
    }

    private fun updateScore(score: Int) {
        highScore = score
        binding.resultTvHighScore.text = "High Score: $highScore"
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(SHARED_PREFERENCE_HIGH_SCORE, highScore)
        editor.apply()
    }

    private fun loadHighScore() {
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE)
        highScore = sharedPreferences.getInt(SHARED_PREFERENCE_HIGH_SCORE, 0)
        binding.resultTvHighScore.text = "High Score: $highScore"
    }

    companion object {
        private const val SHARED_PREFERENCE = "shared_preference"
        private const val SHARED_PREFERENCE_HIGH_SCORE = "shared_preference_high_score"
    }
}