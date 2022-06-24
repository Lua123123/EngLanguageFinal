package com.example.englanguage.viewmodel

import android.content.Context
import androidx.databinding.BaseObservable
import com.example.englanguage.network.API
import com.example.englanguage.model.signup.SignUp
import android.widget.Toast
import android.content.Intent
import android.graphics.Color
import com.example.englanguage.LoginActivity
import android.widget.TextView
import android.view.Gravity
import android.view.View
import com.example.englanguage.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel(private val context: Context) : BaseObservable() {
    fun clickSignUp(email: String?, password: String?, name: String?, conformPassword: String?) {
        API.api.postUsers(email, password, name, conformPassword)
            ?.enqueue(object : Callback<SignUp?> {
                override fun onResponse(call: Call<SignUp?>, response: Response<SignUp?>) {
                    val signUp = response.body()
                    val toast = Toast.makeText(context, "SIGN UP SUCCESSFULLY", Toast.LENGTH_SHORT)
                    customToast(toast)
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                }

                override fun onFailure(call: Call<SignUp?>, t: Throwable) {
                    val toast = Toast.makeText(
                        context,
                        "Please check your Internet connection!!!",
                        Toast.LENGTH_SHORT
                    )
                    customToast(toast)
                }
            })
    }

    fun customToast(toast: Toast) {
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
}