package com.example.englanguage.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.englanguage.model.login.UserLogin
import com.example.englanguage.network.API
import com.example.englanguage.model.login.Login
import android.content.SharedPreferences
import android.graphics.Color
import com.example.englanguage.MainActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.view.Gravity
import com.example.englanguage.R
import com.example.englanguage.extensions.launchActivity
import com.example.englanguage.extensions.toast
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragmentViewModel(
    private val context: Context,
    private val email: String,
    private val password: String
) : ViewModel() {
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    private val remember_me = true
    var mAuthor = MutableLiveData<String>()
    //SHARED PREFERENCE
    private var sharedPref: SharedPreferences? = null

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun mClickLogin() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            clickLogin()
        }
    }

    private fun clickLogin() {
        val userLogin = UserLogin(email, password, remember_me)
        //SHARED PREFERENCE
        sharedPref = context.getSharedPreferences("dataAuth", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = sharedPref?.edit()
        API.api.getUsers(userLogin)?.enqueue(object : Callback<Login?> {
            override fun onResponse(call: Call<Login?>, response: Response<Login?>) {
                val login = response.body()!!
                val status = login.status.toString()
                if (status == "true") {
                    val tokenType = login.data.token_type.trim { it <= ' ' }
                    val accessToken = login.data.access_token.trim { it <= ' ' }
                    val Authorization = "$tokenType $accessToken"
                    //SHARED PREFERENCE
                    editor?.putString("Authorization", Authorization)
                    editor?.apply()
                    context.launchActivity(MainActivity::class.java)
                    context.toast("LOGIN SUCCESSFULLY")
                } else {
                    context.toast("EMAIL OR PASSWORD INVALID!")
                }
            }

            override fun onFailure(call: Call<Login?>, t: Throwable) {
                context.toast("LOGIN FAILED!")
            }
        })
    }
}