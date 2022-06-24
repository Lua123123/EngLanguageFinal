package com.example.englanguage

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.englanguage.databinding.ActivityMainBinding
import com.example.englanguage.exam.ExamPagerActivity

class MainActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false
    private lateinit var Authorization: String
    private lateinit var binding: ActivityMainBinding
    //SHARED PREFERENCE
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportActionBar?.hide()

        //SHARED PREFERENCE
        sharedPref = this.getSharedPreferences("dataAuth", Context.MODE_PRIVATE)
        Authorization = sharedPref.getString("Authorization", "").toString()

        binding.topic.setOnClickListener(View.OnClickListener {
            val intent1 = Intent(this@MainActivity, TopicActivity::class.java)
            startActivity(intent1)
        })

        binding.vocabulary.setOnClickListener(View.OnClickListener {
            val intent2 = Intent(this@MainActivity, VocabularyActivity::class.java)
            startActivity(intent2)
        })

        binding.speak.setOnClickListener(View.OnClickListener {
            val intent3 = Intent(this@MainActivity, TextToSpeechActivity::class.java)
            startActivity(intent3)
        })

        binding.logout.setOnClickListener(View.OnClickListener {
            openDialogInsertVocabulary(Gravity.CENTER)
        })

        binding.flipcard.setOnClickListener(View.OnClickListener {
            val intent4 = Intent(this@MainActivity, FlipCardActivity::class.java)
            startActivity(intent4)
        })

        binding.videoExo.setOnClickListener(View.OnClickListener {
            val intent5 = Intent(this@MainActivity, ExoMenuActivity::class.java)
            startActivity(intent5)
        })

        binding.test.setOnClickListener(View.OnClickListener {
            val intent6 = Intent(this@MainActivity, ExamPagerActivity::class.java)
            startActivity(intent6)
        })
    }

    private fun openDialogInsertVocabulary(gravity: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_logout)
        val window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.attributes = windowAttributes
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true)
        } else {
            dialog.setCancelable(false)
        }
        val btnCancel = dialog.findViewById<Button>(R.id.btnCancelDialog)
        val btnConfirm = dialog.findViewById<Button>(R.id.btnConFirmDialog)
        btnCancel.setOnClickListener { dialog.dismiss() }
        btnConfirm.setOnClickListener {
            val intent4 = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent4)
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun customToast(toast: Toast) {
        val toastView = toast.view
        val toastMessage = toastView!!.findViewById<View>(android.R.id.message) as TextView
        toastMessage.textSize = 13f
        toastMessage.setTextColor(Color.YELLOW)
        toastMessage.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        toastMessage.gravity = Gravity.CENTER
        toastMessage.compoundDrawablePadding = 4
        toastView.setBackgroundColor(Color.BLACK)
        toastView.setBackgroundResource(R.drawable.bg_toast)
        toast.show()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            val intent5 = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent5)
            super.onBackPressed()
        }
        this.doubleBackToExitPressedOnce = true
        val toast: Toast = Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT)
        customToast(toast)
        Handler().postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }
}