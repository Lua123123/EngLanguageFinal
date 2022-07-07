package com.example.englanguage.exam

import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.englanguage.R
import com.example.englanguage.database.VocabularyDatabase
import com.example.englanguage.databinding.ActivityQuizBinding
import com.example.englanguage.extensions.toast
import com.example.englanguage.model.exam.Question
import java.util.*

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var quesList: List<Question>
    private lateinit var currentQ: Question
    private lateinit var textColorOfButtons: ColorStateList
    private lateinit var wrongDialog: WrongDialog
    private lateinit var timerDialog: TimerDialog
    private var countDownTimer: CountDownTimer? = null
    private var answerd = false
    private var questionTotalCount: Int? = null
    private var questionCounter: Int = 0
    private var correctAns: Int = 0
    private var wrongAns: Int = 0
    private var score: Int = 0
    private val COUNTDOWN_IN_MILLIS: Long = 10000
    private var timeLeftInMillis: Long? = null
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz)
        supportActionBar?.hide()

        textColorOfButtons = binding.radioButton1.textColors

        wrongDialog = WrongDialog(this@QuizActivity)
        timerDialog = TimerDialog(this@QuizActivity)

        VocabularyDatabase.getInstance(this).questionDao().getAllQuestions()
            .observe(this@QuizActivity) { questions ->
                fetchContent(questions)
            }
    }

    private fun fetchContent(questions: List<Question>) {
        quesList = questions
        startQuiz()
    }

    private fun startQuiz() {
        setQuestions()
        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.radio_button1 -> {
                    binding.radioButton1.background =
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.when_answer_selected
                        )
                    binding.radioButton2.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)
                    binding.radioButton3.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)
                    binding.radioButton4.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)
                }
                R.id.radio_button2 -> {
                    binding.radioButton1.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)
                    binding.radioButton2.background =
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.when_answer_selected
                        )
                    binding.radioButton3.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)
                    binding.radioButton4.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)
                }
                R.id.radio_button3 -> {
                    binding.radioButton1.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)
                    binding.radioButton2.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)
                    binding.radioButton3.background =
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.when_answer_selected
                        )
                    binding.radioButton4.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)
                }
                R.id.radio_button4 -> {
                    binding.radioButton1.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)
                    binding.radioButton2.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)
                    binding.radioButton3.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)
                    binding.radioButton4.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.when_answer_selected)
                }
            }
        }

        binding.btnConfirm.setOnClickListener {
            if (!answerd) {
                if (binding.radioButton1.isChecked || binding.radioButton2.isChecked || binding.radioButton3.isChecked || binding.radioButton4.isChecked) {
                    quizOperation()
                } else {
                    toast("Please select answer!")
                }
            }
        }

    }

    private fun quizOperation() {
        answerd = true
        countDownTimer?.cancel()
        val rbSelected: RadioButton = findViewById(binding.radioGroup.checkedRadioButtonId)
        val answerNr: Int = binding.radioGroup.indexOfChild(rbSelected) + 1

        /** check Solutions */
        checkSolution(answerNr, rbSelected)
    }

    private fun checkSolution(answerNr: Int, rbSelected: RadioButton) {
        when (currentQ.answer) {
            1 -> if (currentQ.answer === answerNr) {
                /** radioButton1 */
                binding.radioButton1.background =
                    ContextCompat.getDrawable(applicationContext, R.drawable.when_answer_correct)
                binding.radioButton1.setTextColor(ContextCompat.getColor(this, R.color.white));

                correctAns++
                binding.txtCorrect.text = "Correct: $correctAns"
                score += 10
                binding.txtScore.text = "Score: $score"

                Handler(Looper.getMainLooper()).postDelayed({
                    setQuestions()
                }, 500)

            } else {
                /** radioButton1 incorrect */
                changeToIncorrectColor(rbSelected)

                wrongAns++
                binding.txtWrong.text = "Wrong: $wrongAns"

                val correctAnswer: String = binding.radioButton1.text as String
                wrongDialog.wrongDialog(correctAnswer)

                Handler(Looper.getMainLooper()).postDelayed({
                    setQuestions()
                }, 500)
            }

            2 -> if (currentQ.answer === answerNr) {
                /** radioButton2 */
                binding.radioButton2.background =
                    ContextCompat.getDrawable(applicationContext, R.drawable.when_answer_correct)
                binding.radioButton2.setTextColor(ContextCompat.getColor(this, R.color.white));

                correctAns++
                binding.txtCorrect.text = "Correct: $correctAns"
                score += 10
                binding.txtScore.text = "Score: $score"

                Handler(Looper.getMainLooper()).postDelayed({
                    setQuestions()
                }, 500)
            } else {
                /** radioButton2 incorrect */
                changeToIncorrectColor(rbSelected)

                wrongAns++
                binding.txtWrong.text = "Wrong: $wrongAns"

                val correctAnswer: String = binding.radioButton2.text as String
                wrongDialog.wrongDialog(correctAnswer)

                Handler(Looper.getMainLooper()).postDelayed({
                    setQuestions()
                }, 500)
            }

            3 -> if (currentQ.answer === answerNr) {
                /** radioButton3 */
                binding.radioButton3.background =
                    ContextCompat.getDrawable(applicationContext, R.drawable.when_answer_correct)
                binding.radioButton3.setTextColor(ContextCompat.getColor(this, R.color.white));

                correctAns++
                binding.txtCorrect.text = "Correct: $correctAns"
                score += 10
                binding.txtScore.text = "Score: $score"

                Handler(Looper.getMainLooper()).postDelayed({
                    setQuestions()
                }, 500)
            } else {
                /** radioButton3 incorrect */
                changeToIncorrectColor(rbSelected)

                wrongAns++
                binding.txtWrong.text = "Wrong: $wrongAns"

                val correctAnswer: String = binding.radioButton3.text as String
                wrongDialog.wrongDialog(correctAnswer)

                Handler(Looper.getMainLooper()).postDelayed({
                    setQuestions()
                }, 500)
            }

            4 -> if (currentQ.answer === answerNr) {
                /** radioButton4 */
                binding.radioButton4.background =
                    ContextCompat.getDrawable(applicationContext, R.drawable.when_answer_correct)
                binding.radioButton4.setTextColor(ContextCompat.getColor(this, R.color.white));

                correctAns++
                binding.txtCorrect.text = "Correct: $correctAns"
                score += 10
                binding.txtScore.text = "Score: $score"

                Handler(Looper.getMainLooper()).postDelayed({
                    setQuestions()
                }, 500)
            } else {
                /** radioButton4 incorrect */
                changeToIncorrectColor(rbSelected)

                wrongAns++
                binding.txtWrong.text = "Wrong: $wrongAns"

                val correctAnswer: String = binding.radioButton4.text as String
                wrongDialog.wrongDialog(correctAnswer)

                Handler(Looper.getMainLooper()).postDelayed({
                    setQuestions()
                }, 500)
            }
        }
        if (questionCounter < questionTotalCount!!) {
            binding.btnConfirm.text = "FINISH"
        }
    }

    private fun changeToIncorrectColor(rbSelected: RadioButton) {
        rbSelected.background =
            ContextCompat.getDrawable(applicationContext, R.drawable.when_answer_wrong)
        rbSelected.setTextColor(ContextCompat.getColor(this, R.color.white));
    }

    private fun setQuestions() {
        binding.radioGroup.clearCheck()

        binding.radioButton1.background =
            ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)
        binding.radioButton2.background =
            ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)
        binding.radioButton3.background =
            ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)
        binding.radioButton4.background =
            ContextCompat.getDrawable(applicationContext, R.drawable.default_option_bg)

        binding.radioButton1.setTextColor(ContextCompat.getColor(this, R.color.gold));
        binding.radioButton2.setTextColor(ContextCompat.getColor(this, R.color.gold));
        binding.radioButton3.setTextColor(ContextCompat.getColor(this, R.color.gold));
        binding.radioButton4.setTextColor(ContextCompat.getColor(this, R.color.gold));

        questionTotalCount = quesList.size
        Collections.shuffle(quesList)

        if (questionCounter < questionTotalCount!! - 1) {
            currentQ = quesList[questionCounter]
            Glide.with(this).load(currentQ.img).into(binding.imgQuiz);
            binding.txtQuestionContainer.text = currentQ.question
            binding.radioButton1.text = currentQ.optA
            binding.radioButton2.text = currentQ.optB
            binding.radioButton3.text = currentQ.optC
            binding.radioButton4.text = currentQ.optD
            questionCounter++

            answerd = false

            binding.btnConfirm.text = "NEXT"

            binding.txtTotalQuestion.text =
//                "Question: $questionCounter/$questionTotalCount"
                "Question: " + questionCounter + "/" + (questionTotalCount!! - 1)

            timeLeftInMillis = COUNTDOWN_IN_MILLIS
            startCountDown()
        } else {
            binding.radioButton1.isClickable = false
            binding.radioButton2.isClickable = false
            binding.radioButton3.isClickable = false
            binding.radioButton4.isClickable = false
            binding.btnConfirm.isClickable = false

            Handler(Looper.getMainLooper()).postDelayed({
                resultData()
            }, 2000)
        }
    }

    private fun startCountDown() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis!!, 1000) {
            override fun onTick(l: Long) {
                timeLeftInMillis = l
                updateCountDownText()
            }

            override fun onFinish() {
                timeLeftInMillis = 0
                updateCountDownText()
            }
        }.start()
    }

    private fun updateCountDownText() {
        val minutes: Int = ((timeLeftInMillis!! / 1000) / 60).toInt()
        val second: Int = ((timeLeftInMillis!! / 1000) % 60).toInt()

        val timeFormat: String = String.format(Locale.getDefault(), "%02d:%02d", minutes, second)
        binding.txtTimer.text = timeFormat

        if (timeLeftInMillis!! <= 5000) {
            binding.txtTimer.setTextColor(
                ContextCompat.getColor(this, android.R.color.holo_red_dark))
        } else {
            binding.txtTimer.setTextColor(ContextCompat.getColor(this, R.color.nearBlack))
        }
        if (timeLeftInMillis == 0L) {
            Handler(Looper.getMainLooper()).postDelayed({ timerDialog.timerDialog() }, 1000)
        }
    }

    private fun resultData() {
        val intent = Intent(this@QuizActivity, ResultActivity::class.java)
        intent.putExtra("UserScore", score)
        intent.putExtra("TotalQuizQuestions", (questionTotalCount!! - 1))
        intent.putExtra("CorrectQuestions", correctAns)
        intent.putExtra("WrongQuestions", wrongAns)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (countDownTimer != null) {
            countDownTimer?.cancel()
        }
    }

    override fun onPause() {
        super.onPause()
        if (countDownTimer != null) {
            countDownTimer?.cancel()
            finish()
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            AlertDialog.Builder(this)
                .setTitle("Do you want to Exit?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton(
                    "Confirm",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        setResult(RESULT_OK, Intent().putExtra("Exit", true))
                        finish()
                    }).create().show()
            countDownTimer?.cancel()
        } else {
            toast("Press again to exit!")
        }
        backPressedTime = System.currentTimeMillis()
    }
}