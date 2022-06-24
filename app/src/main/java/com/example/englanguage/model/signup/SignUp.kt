package com.example.englanguage.model.signup

import com.example.englanguage.model.login.Data

class SignUp {
    var status: Boolean? = null
    var message: String? = null
    var data: Data? = null
    override fun toString(): String {
        return "SignUp{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}'
    }
}