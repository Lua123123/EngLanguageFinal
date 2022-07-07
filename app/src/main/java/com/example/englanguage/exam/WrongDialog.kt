package com.example.englanguage.exam

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.widget.TextView
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Button
import com.example.englanguage.R

class WrongDialog(private val mContext: Context) {
    private lateinit var wrongDialog: Dialog
    fun wrongDialog(correctAnswer: String?) {
        wrongDialog = Dialog(mContext)
        wrongDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        wrongDialog.setContentView(R.layout.wrong_dialog)
        val btnWrongDialog = wrongDialog.findViewById<View>(R.id.btn_wrongDialog) as Button
        val textView = wrongDialog.findViewById<View>(R.id.txtWrong) as TextView
        textView.text = correctAnswer
        btnWrongDialog.setOnClickListener { wrongDialog.dismiss() }
        wrongDialog.show()
        wrongDialog.setCancelable(false)
        wrongDialog.setCanceledOnTouchOutside(false)
    }
}