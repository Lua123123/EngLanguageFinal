package com.example.englanguage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.englanguage.databinding.ActivitySignUpBinding
import com.example.englanguage.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    private val context: Context = this@SignUpActivity
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var conformPassword: String
    private lateinit var name: String
    private var signUpViewModel: SignUpViewModel? = null
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        supportActionBar?.hide()
        signUpViewModel = SignUpViewModel(context)
        binding.tvHaveAnAccount.setOnClickListener {
            val intentLogin = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intentLogin)
        }
        binding.btnPostSignUp.setOnClickListener {
            email = binding.edtEmail.text.toString().trim { it <= ' ' }
            password = binding.edtPassword.text.toString().trim { it <= ' ' }
            conformPassword = binding.edtConformPassword.text.toString().trim { it <= ' ' }
            name = binding.edtName.text.toString().trim { it <= ' ' }
            if (binding.checkBoxSignUp.isChecked) {
                if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && conformPassword.isNotEmpty()
                ) {
                    signUpViewModel?.clickSignUp(email, password, name, conformPassword)
                } else {
                    val toast = Toast.makeText(
                        context,
                        "NAME, EMAIL, PASSWORD OR CONFORM PASSWORD IS EMPTY!",
                        Toast.LENGTH_SHORT
                    )
                    signUpViewModel?.customToast(toast)
                }
            } else {
                val toast = Toast.makeText(
                    context,
                    "Please agree to the terms of the app!",
                    Toast.LENGTH_SHORT
                )
                signUpViewModel?.customToast(toast)
            }
        }
    }
}