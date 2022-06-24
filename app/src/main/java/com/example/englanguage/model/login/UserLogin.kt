package com.example.englanguage.model.login

class UserLogin(var email: String, var password: String, var remember_me: Boolean) {
    override fun toString(): String {
        return "UserLogin{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", remember_me=" + remember_me +
                '}'
    }
}