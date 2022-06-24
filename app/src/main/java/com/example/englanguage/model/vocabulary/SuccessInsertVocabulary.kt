package com.example.englanguage.model.vocabulary

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class SuccessInsertVocabulary {
    @SerializedName("success")
    @Expose
    var success: String? = null
}