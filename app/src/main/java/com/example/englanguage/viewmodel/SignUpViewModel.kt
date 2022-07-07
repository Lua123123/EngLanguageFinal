package com.example.englanguage.viewmodel

import android.content.Context
import androidx.databinding.BaseObservable
import com.example.englanguage.network.API
import com.example.englanguage.model.signup.SignUp
import com.example.englanguage.LoginActivity
import com.example.englanguage.extensions.launchActivity
import com.example.englanguage.extensions.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel(private val context: Context) : BaseObservable() {
    fun clickSignUp(email: String?, password: String?, name: String?, conformPassword: String?) {
        API.api.postUsers(email, password, name, conformPassword)
            ?.enqueue(object : Callback<SignUp?> {
                override fun onResponse(call: Call<SignUp?>, response: Response<SignUp?>) {
                    val signUp = response.body()
                    context.toast("SIGN UP SUCCESSFULLY")
                    context.launchActivity(LoginActivity::class.java)
                }

                override fun onFailure(call: Call<SignUp?>, t: Throwable) {
                    context.toast("Please check your Internet connection!!!")
                }
            })
    }
}