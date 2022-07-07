package com.example.englanguage.exam

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import com.example.englanguage.R
import android.graphics.Color
import android.view.View
import android.widget.Button
import com.example.englanguage.extensions.launchActivity

class TimerDialog(private val mContext: Context) {
    private lateinit var timerDialog: Dialog
    fun timerDialog() {
        timerDialog = Dialog(mContext)
        timerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        timerDialog.setContentView(R.layout.time_up_dialog)
        val btnTimerDialog = timerDialog.findViewById<View>(R.id.btn_timerDialog) as Button
        btnTimerDialog.setOnClickListener {
            timerDialog.dismiss()
            mContext.launchActivity(StartQuizActivity::class.java)
        }
        timerDialog.show()
        timerDialog.setCancelable(false)
        timerDialog.setCanceledOnTouchOutside(false)
    }
}