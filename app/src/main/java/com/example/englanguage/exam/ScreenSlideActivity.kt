package com.example.englanguage.exam

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.englanguage.R
import com.example.englanguage.databinding.ActivityScreenSlideBinding
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import com.example.englanguage.model.vocabulary.Vocabulary
import com.example.englanguage.network.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val NUM_PAGES = 10

class ScreenSlideActivity : FragmentActivity() {
    private lateinit var binding: ActivityScreenSlideBinding
    private lateinit var mPager: ViewPager
    private val arrQu: ArrayList<String> = ArrayList()
    private val arrQuestion: ArrayList<SuccessVocabulary> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_slide)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_screen_slide)
        mPager = findViewById(R.id.pager)
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter
        mPager.setPageTransformer(true, ZoomOutPageTransformer())

        binding.tvKiemTra.setOnClickListener {
//            checkAnswer()
            clickGetVocabulary()
        }
    }

    override fun onBackPressed() {
        if (mPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            mPager.currentItem = mPager.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment {
            return ScreenSlidePageFragment.create(position)
        }
    }

    private fun clickGetVocabulary(): List<SuccessVocabulary?>? {
        API.api.getVocabulary(1, "", "10")?.enqueue(object : Callback<Vocabulary?> {
            override fun onResponse(call: Call<Vocabulary?>, response: Response<Vocabulary?>) {
                val vocabulary: Vocabulary? = response.body()
                if (vocabulary != null) {
                    for (i in vocabulary.success?.indices!!) {
                        val successVocabulary = SuccessVocabulary(
                            0,
                            vocabulary.success?.get(i)?.word!!,
                            vocabulary.success?.get(i)?.mean!!,
                            vocabulary.success?.get(i)?.example!!
                        )
                        arrQuestion.add(successVocabulary)
                    }

                    val dialog: Dialog = Dialog(this@ScreenSlideActivity)
                    dialog.setContentView(R.layout.check_answer_dialog)
                    dialog.setTitle("ANSWER LIST")

                    val answerAdapter: CheckAnswerAdapter =
                        CheckAnswerAdapter(arrQuestion, this@ScreenSlideActivity)
                    val gvLsQuestion: GridView = dialog.findViewById(R.id.gvLsQuestion)
                    gvLsQuestion.adapter = answerAdapter

                    gvLsQuestion.onItemClickListener =
                        OnItemClickListener { _, _, _, _ ->
                            dialog.dismiss()
                        }
                    val btnClose: Button = dialog.findViewById(R.id.btnClose)
                    val btnConfirm: Button = dialog.findViewById(R.id.btnConfirm)
                    btnClose.setOnClickListener {
                        dialog.dismiss()
                    }
                    btnConfirm.setOnClickListener {

                    }
                    dialog.show()

                } else {
                    Toast.makeText(this@ScreenSlideActivity, "FAILED", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Vocabulary?>, t: Throwable) {
            }
        })
        return null
    }

    private fun checkAnswer() {
        arrQu.add("")
        arrQu.add("")
        arrQu.add("")
        arrQu.add("")
        arrQu.add("")
        arrQu.add("")
        arrQu.add("")
        arrQu.add("")
        arrQu.add("")
        arrQu.add("")
        val dialog: Dialog = Dialog(this@ScreenSlideActivity)
        dialog.setContentView(R.layout.check_answer_dialog)
        dialog.setTitle("ANSWER LIST")

        val answerAdapter: CheckAnswerAdapter =
            CheckAnswerAdapter(arrQu, this@ScreenSlideActivity)
        val gvLsQuestion: GridView = dialog.findViewById(R.id.gvLsQuestion)
        gvLsQuestion.adapter = answerAdapter

        gvLsQuestion.onItemClickListener =
            OnItemClickListener { _, _, _, _ ->
                dialog.dismiss()
            }
        val btnClose: Button = dialog.findViewById(R.id.btnClose)
        val btnConfirm: Button = dialog.findViewById(R.id.btnConfirm)
        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        btnConfirm.setOnClickListener {

        }
        dialog.show()
    }
}

private const val MIN_SCALE = 0.85f
private const val MIN_ALPHA = 0.5f

class ZoomOutPageTransformer : ViewPager.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 0f
                }
                position <= 1 -> { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    // Fade the page relative to its size.
                    alpha = (MIN_ALPHA +
                            (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                }
                else -> { // (1,+Infinity]
                    alpha = 0f
                }
            }
        }
    }
}