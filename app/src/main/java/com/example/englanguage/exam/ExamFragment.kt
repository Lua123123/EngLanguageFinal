package com.example.englanguage.exam

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.englanguage.R
import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment

class ExamFragment : Fragment() {
    private lateinit var btnStart: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exam, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnStart = view.findViewById(R.id.img_start)
        btnStart.setOnClickListener {
            val intent = Intent(context, ScreenSlideActivity::class.java)
            context!!.startActivity(intent)
        }
    }
}