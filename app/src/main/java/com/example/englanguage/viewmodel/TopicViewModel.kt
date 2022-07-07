package com.example.englanguage.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.englanguage.adapter.ListTopicAdapter
import com.example.englanguage.database.VocabularyDatabase
import com.example.englanguage.extensions.toast
import com.example.englanguage.model.topic.Success
import com.example.englanguage.model.topic.Topic
import com.example.englanguage.network.API
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class TopicViewModel(private val context:Context) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private var job: Job? = null

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun mClickGetTopic(postsList: MutableList<Success> = ArrayList(), adapter: ListTopicAdapter? = null) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            clickGetTopic(postsList, adapter)
        }
    }

    private fun clickGetTopic(postsList: MutableList<Success> = ArrayList(), adapter: ListTopicAdapter? = null) {
        API.api.getTopics(1)?.enqueue(object : Callback<Topic?> {
            override fun onResponse(call: Call<Topic?>, response: Response<Topic?>) {
                val topic = response.body()
                for (i in topic!!.success!!.indices) {
                    val success = Success(
                        topic.success!![i].name,
                        topic.success!![i].soluong,
                        topic.success!![i].id
                    )
                    postsList.add(success)

                    if (VocabularyDatabase.getInstance(context)?.topicDAO()?.getListTopic()?.size!! <= postsList.size) {
                        val strTopic = topic.success!![i].name
                        val strAmount = topic.success!![i].soluong
                        val successDataRoom = Success(strTopic, strAmount, 0)
                        VocabularyDatabase.getInstance(context)?.topicDAO()?.insertTopic(successDataRoom)
                    }

                }
                adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Topic?>, t: Throwable) {
                context.toast("SHOW LIST TOPIC FAILED")
            }
        })
    }
}