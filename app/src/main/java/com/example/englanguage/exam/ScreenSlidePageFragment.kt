package com.example.englanguage.exam

import com.example.englanguage.model.vocabulary.SuccessVocabulary
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.englanguage.R
import com.example.englanguage.model.vocabulary.Vocabulary
import com.example.englanguage.network.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScreenSlidePageFragment : Fragment() {
    private val listWordA: ArrayList<SuccessVocabulary> = ArrayList()
    private val listImage: ArrayList<String> = ArrayList()
    private var mPageNumber = 0
    private var tv_num: TextView? = null
    private var radGroup: RadioGroup? = null
    private var radA: RadioButton? = null
    private var radB: RadioButton? = null
    private var radC: RadioButton? = null
    private var radD: RadioButton? = null
    private var img_exam: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)
        tv_num = view.findViewById(R.id.tv_num)
        radGroup = view.findViewById(R.id.radGroup)
        radA = view.findViewById(R.id.radA)
        radB = view.findViewById(R.id.radB)
        radC = view.findViewById(R.id.radC)
        radD = view.findViewById(R.id.radD)
        img_exam = view.findViewById(R.id.img_exam)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPageNumber = arguments!!.getInt("page")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_num!!.text = "Câu hỏi thứ " + (mPageNumber + 1)
        clickGetVocabulary()

    }

    companion object {
        fun create(pageNumber: Int): ScreenSlidePageFragment {
            val fragment = ScreenSlidePageFragment()
            val args = Bundle()
            args.putInt("page", pageNumber)
            fragment.arguments = args
            return fragment
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
                        listImage.add(vocabulary.success?.get(mPageNumber)?.imagePath!!)
                        listWordA.add(successVocabulary)
                    }
                    radA?.text = listWordA[mPageNumber].word
                    radB?.text = listWordA[mPageNumber + 1].word
                    radC?.text = listWordA[mPageNumber + 2].word
                    radD?.text = listWordA[mPageNumber + 3].word
                    Glide.with(context!!).load(listImage[mPageNumber]).into(img_exam!!);
                } else {
                    Toast.makeText(context!!, "FAILED", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Vocabulary?>, t: Throwable) {
            }
        })
        return null
    }
}