package com.example.englanguage.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import com.example.englanguage.model.vocabulary.Vocabulary
import com.example.englanguage.network.API
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FlipViewModel(var context: Context) : ViewModel() {

    val mVocabulary = MutableLiveData<Vocabulary>()
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private lateinit var job: Job

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

//    fun koinGetVocabulary() = koinRepository.getVocabulary()

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun mutableLiveDataClickGetVocabulary() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            clickGetVocabulary()
        }
    }

    private fun clickGetVocabulary(): List<SuccessVocabulary?>? {

        API.api.getVocabulary(1, "", "10")?.enqueue(object : Callback<Vocabulary?> {
            override fun onResponse(call: Call<Vocabulary?>, response: Response<Vocabulary?>) {
                val vocabulary: Vocabulary? = response.body()
                if (vocabulary != null) {
                    mVocabulary.postValue(vocabulary!!)
                } else {
                    onError("Error: ${response.message()} ")
                }
            }

            override fun onFailure(call: Call<Vocabulary?>, t: Throwable) {
            }
        })
        return null
    }
}