package com.example.englanguage

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.englanguage.databinding.ActivityMainBinding
import com.example.englanguage.exam.StartQuizActivity
import com.example.englanguage.extensions.launchActivity
import com.example.englanguage.extensions.toast

class MainActivity : AppCompatActivity() {
    private var backPressedTime: Long = 0
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


        binding.topic.setOnClickListener {
            launchActivity(TopicActivity::class.java)
        }

        binding.vocabulary.setOnClickListener {
            launchActivity(VocabularyActivity::class.java)
        }

        binding.speak.setOnClickListener {
            launchActivity(TextToSpeechActivity::class.java)
        }

        binding.logout.setOnClickListener {
            openDialogInsertVocabulary(Gravity.CENTER)
        }

        binding.flipcard.setOnClickListener {
            launchActivity(FlipCardActivity::class.java)
        }

        binding.videoExo.setOnClickListener {
            launchActivity(ExoMenuActivity::class.java)
        }

        binding.test.setOnClickListener{
            launchActivity(StartQuizActivity::class.java)
        }
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
            launchActivity(LoginActivity::class.java)
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            AlertDialog.Builder(this)
                .setTitle("Do you want to exit?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton(
                    "Confirm",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        setResult(RESULT_OK, Intent().putExtra("Exit", true))
                        finish()
                    }).create().show()
        } else {
            toast("Press again to exit!")
        }
        backPressedTime = System.currentTimeMillis()
    }
}