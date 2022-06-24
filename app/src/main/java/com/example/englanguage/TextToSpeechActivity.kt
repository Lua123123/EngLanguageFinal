package com.example.englanguage

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.englanguage.databinding.ActivityTextToSpeechBinding
import java.util.*

class TextToSpeechActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTextToSpeechBinding
    private lateinit var mTTS: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_to_speech)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_text_to_speech)

        callMTTS()
        binding.mButtonSpeak.setOnClickListener {
            speak()
        }

        binding.imgBack.setOnClickListener {
            val intentMainActivity = Intent(this@TextToSpeechActivity, MainActivity::class.java)
            startActivity(intentMainActivity)
        }
    }

    private fun callMTTS() {
        mTTS = TextToSpeech(this, object : TextToSpeech.OnInitListener {
            override fun onInit(i: Int) {
                if (i == TextToSpeech.SUCCESS) {
                    val result = mTTS.setLanguage(Locale.ENGLISH)
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    } else {
                        binding.mButtonSpeak.isEnabled = true
                    }
                    run {}
                }
            }
        })
    }

    private fun speak() {
        val text: String = binding.edtTextToSpeech.text.toString().trim()
        var pitch = binding.seekBarPitch.progress.toFloat() / 50
        if (pitch < 0.1) pitch = 0.1F
        var speed: Float = binding.seekBarSpeed.progress.toFloat() / 50
        if (speed < 0.1) speed = 0.1F

        mTTS.setPitch(pitch) //pitch
        mTTS.setSpeechRate(speed) //speed

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun onDestroy() {
        mTTS.stop()
        mTTS.shutdown()
        super.onDestroy()
    }
}