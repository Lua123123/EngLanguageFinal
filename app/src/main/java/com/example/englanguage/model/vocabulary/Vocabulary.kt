package com.example.englanguage.model.vocabulary

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Vocabulary {
    @SerializedName("success")
    @Expose
    var success: List<SuccessVocabulary>? = null
}