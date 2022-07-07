package com.example.englanguage

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.englanguage.databinding.ActivitySignUpBinding
import com.example.englanguage.extensions.launchActivity
import com.example.englanguage.extensions.toast
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
            launchActivity(LoginActivity::class.java)
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
                    context.toast("NAME, EMAIL, PASSWORD OR CONFORM PASSWORD IS EMPTY!")
                }
            } else {
                context.toast("Please agree to the terms of the app!")
            }
        }
    }
}