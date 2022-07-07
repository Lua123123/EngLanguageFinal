package com.example.englanguage.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.englanguage.R
import com.example.englanguage.database.VocabularyDatabase
import com.example.englanguage.databinding.ActivityStartQuizBinding
import com.example.englanguage.extensions.launchActivity
import java.util.*

class StartQuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartQuizBinding
    private var quizViewModel: QuizViewModel = QuizViewModel(context = this@StartQuizActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_quiz)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start_quiz)
        Objects.requireNonNull(supportActionBar)?.hide()
        binding.btnContract.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataContract()
            launchActivity(QuizActivity::class.java)
        }

        binding.btnMarketing.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataMarketing()
            launchActivity(QuizActivity::class.java)
        }

        binding.btnWarranties.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataWarranties()
            launchActivity(QuizActivity::class.java)
        }

        binding.btnBusiness.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataBusiness()
            launchActivity(QuizActivity::class.java)
        }

        binding.btnConferences.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataConferences()
            launchActivity(QuizActivity::class.java)
        }

        binding.btnComputers.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataComputers()
            launchActivity(QuizActivity::class.java)
        }

        binding.btnOfficeTech.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataOfficeTech()
            launchActivity(QuizActivity::class.java)
        }

        binding.btnOfficeProducts.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataOfficeProducts()
            launchActivity(QuizActivity::class.java)
        }
        binding.btnElectronics.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataElectronics()
            launchActivity(QuizActivity::class.java)
        }
        binding.btnCorres.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataCorrespondence()
            launchActivity(QuizActivity::class.java)
        }
        binding.btnJob.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataJob()
            launchActivity(QuizActivity::class.java)
        }
        binding.btnApply.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataApply()
            launchActivity(QuizActivity::class.java)
        }
        binding.btnHiring.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataHiring()
            launchActivity(QuizActivity::class.java)
        }
        binding.btnSalary.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataSalary()
            launchActivity(QuizActivity::class.java)
        }
        binding.btnPromotions.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataPromotions()
            launchActivity(QuizActivity::class.java)
        }
        binding.btnShopping.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataShopping()
            launchActivity(QuizActivity::class.java)
        }
        binding.btnOder.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataOder()
            launchActivity(QuizActivity::class.java)
        }
    }
}