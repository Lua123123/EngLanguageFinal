package com.example.englanguage

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.englanguage.databinding.ActivityOneVocabularyBinding
import java.util.*

class OneVocabularyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOneVocabularyBinding
    private lateinit var mTTS: TextToSpeech
    private var isFront = true
    private lateinit var front_anim: AnimatorSet
    private lateinit var behind_anim: AnimatorSet
    private lateinit var word: String
    private lateinit var mean: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_vocabulary)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_one_vocabulary)
        supportActionBar?.hide()
        val bundle = intent.extras
        word = bundle!!.getString("word").toString()
        mean = bundle.getString("mean").toString()
        binding.cartFront.text = word
        binding.cartBehind.text = mean

        flipCart()
        callMTTS()
        binding.mButtonSpeak.setOnClickListener {
            word.let { speak(it) }
        }

    }

    private fun flipCart() {
        val scale: Float? = this.resources?.displayMetrics?.density
        binding.cartFront.cameraDistance = 8000 * scale!!
        binding.cartBehind.cameraDistance = 8000 * scale
        front_anim = AnimatorInflater.loadAnimator(this, R.animator.front_animator) as AnimatorSet
        behind_anim = AnimatorInflater.loadAnimator(this, R.animator.behind_animation) as AnimatorSet
        binding.flipLayout.setOnClickListener {
            isFront = if (isFront) {
                front_anim.setTarget(binding.cartFront)
                behind_anim.setTarget(binding.cartBehind)
                front_anim.start()
                behind_anim.start()
                false
            } else {
                front_anim.setTarget(binding.cartBehind)
                behind_anim.setTarget(binding.cartFront)
                behind_anim.start()
                front_anim.start()
                true
            }
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

    private fun speak(word: String) {
        val text: String = word
        var pitch = binding.seekBarPitch.progress.toFloat() / 50
        if (pitch < 0.1) pitch = 0.1F
        var speed: Float = binding.seekBarSpeed.progress.toFloat() / 50
        if (speed < 0.1) speed = 0.1F
        mTTS.setPitch(pitch) //pitch
        mTTS.setSpeechRate(speed) //speed
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun onDestroy() {
        if (mTTS != null) {
            mTTS.stop()
            mTTS.shutdown()
        }
        super.onDestroy()
    }
}