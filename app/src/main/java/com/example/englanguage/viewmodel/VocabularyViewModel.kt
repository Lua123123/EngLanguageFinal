package com.example.englanguage.viewmodel

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguage.adapter.ListVocabularyAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.englanguage.model.vocabulary.Vocabulary
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.englanguage.R
import android.view.WindowManager
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.englanguage.network.API
import com.example.englanguage.model.vocabulary.DeleteVocabulary
import android.widget.EditText
import com.example.englanguage.database.VocabularyDatabase
import com.example.englanguage.extensions.toast
import com.example.englanguage.model.vocabulary.SuccessInsertVocabulary
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VocabularyViewModel : ViewModel() {
    private var mListVocabulary: MutableList<SuccessVocabulary> = ArrayList()
    private var vocabulary: Vocabulary? = null
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private lateinit var job: Job
    private lateinit var word: String

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun openDialogDeleteVocabulary(
        gravity: Int,
        Authorization: String?,
        position: Int,
        swipeRefreshLayout: SwipeRefreshLayout?,
        context: Context,
        recyclerView: RecyclerView,
        adapter: ListVocabularyAdapter,
        search: String
    ) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_custom_delete)
        val window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.attributes = windowAttributes
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true)
        } else {
            dialog.setCancelable(false)
        }
        val tv_vocabulary = dialog.findViewById<TextView>(R.id.tv_vocabulary)
        val btnCancel = dialog.findViewById<ConstraintLayout>(R.id.btn_cancel)
        val btnConfirm = dialog.findViewById<ConstraintLayout>(R.id.btn_confirm)
        word = vocabulary?.success?.get(position)?.word!!
        tv_vocabulary.text = word
        btnCancel.setOnClickListener { dialog.dismiss() }
        btnConfirm.setOnClickListener {
            clickDeleteVocabulary(
                Authorization,
                word,
                position,
                swipeRefreshLayout,
                context,
                recyclerView,
                adapter,
                search
            )
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun clickDeleteVocabulary(
        Authorization: String?,
        word: String?,
        position: Int,
        swipeRefreshLayout: SwipeRefreshLayout?,
        context: Context,
        recyclerView: RecyclerView,
        adapter: ListVocabularyAdapter,
        search: String
    ) {
        API.api.deleteVocabulary(Authorization, 1, word)
            ?.enqueue(object : Callback<DeleteVocabulary?> {
                override fun onResponse(
                    call: Call<DeleteVocabulary?>,
                    response: Response<DeleteVocabulary?>
                ) {
                    mListVocabulary.removeAt(position)
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<DeleteVocabulary?>, t: Throwable) {
                    context.toast("DELETE VOCABULARY FAILED!")
                }
            })
    }

    fun openDialogInsertVocabulary(
        gravity: Int,
        Authorization: String?,
        context: Context,
        recyclerView: RecyclerView,
        adapter: ListVocabularyAdapter,
        search: String
    ) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_custom)
        val window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.attributes = windowAttributes
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true)
        } else {
            dialog.setCancelable(false)
        }
        val edtWord = dialog.findViewById<EditText>(R.id.edt_word)
        val edtMean = dialog.findViewById<EditText>(R.id.edt_mean)
        val edtExample = dialog.findViewById<EditText>(R.id.edt_example)
        val btnCancel = dialog.findViewById<ConstraintLayout>(R.id.btn_cancel)
        val btnConfirm = dialog.findViewById<ConstraintLayout>(R.id.btn_confirm)
        btnCancel.setOnClickListener { dialog.dismiss() }
        btnConfirm.setOnClickListener {
            val word = edtWord.text.toString().trim { it <= ' ' }
            val mean = edtMean.text.toString().trim { it <= ' ' }
            val example = edtExample.text.toString().trim { it <= ' ' }
            clickInsertVocabulary(
                Authorization,
                word,
                mean,
                example,
                context,
                recyclerView,
                adapter,
                search
            )
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun clickInsertVocabulary(
        Authorization: String?,
        word: String?,
        mean: String?,
        example: String?,
        context: Context,
        recyclerView: RecyclerView,
        adapter: ListVocabularyAdapter,
        search: String
    ) {
        API.api.insertVocabulary(Authorization, word, mean, "image_path", example, 2, 1)
            ?.enqueue(object : Callback<SuccessInsertVocabulary?> {
                override fun onResponse(
                    call: Call<SuccessInsertVocabulary?>,
                    response: Response<SuccessInsertVocabulary?>
                ) {

                    mListVocabulary.removeAll(mListVocabulary)
                    mClickGetVocabulary(recyclerView, adapter, context, search)
                    context.toast("Insert successfully")
                }

                override fun onFailure(call: Call<SuccessInsertVocabulary?>, t: Throwable) {
                    context.toast("INSERT VOCABULARY FAILED!")
                }
            })
    }

    fun mClickGetVocabulary(
        recyclerView: RecyclerView,
        adapter: ListVocabularyAdapter,
        context: Context,
        search: String
    ) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            mGetVocabulary(recyclerView, adapter, context, search)
        }
    }

    private fun mGetVocabulary(
        recyclerView: RecyclerView,
        adapter: ListVocabularyAdapter,
        context: Context,
        search: String
    ) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            clickGetVocabulary(recyclerView, adapter, context, search)
        }
    }

    private fun clickGetVocabulary(
        recyclerView: RecyclerView,
        adapter: ListVocabularyAdapter,
        context: Context,
        search: String
    ): List<SuccessVocabulary>? {
        API.api.getVocabulary(1, search, "30")?.enqueue(object : Callback<Vocabulary?> {
            override fun onResponse(call: Call<Vocabulary?>, response: Response<Vocabulary?>) {
                vocabulary = response.body()
                mListVocabulary.removeAll(mListVocabulary)
                for (i in vocabulary?.success?.indices!!) {
                    val successVocabulary = SuccessVocabulary(
                        0, vocabulary?.success?.get(i)?.word!!,
                        vocabulary?.success?.get(i)?.mean!!, vocabulary?.success?.get(i)?.example!!
                    )
                    mListVocabulary.add(successVocabulary)
                    adapter.setData(mListVocabulary)
                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()

                    if (VocabularyDatabase.getInstance(context)?.vocabularyDAO()
                            ?.getListVocabulary()?.size!! <= mListVocabulary.size
                    ) {
                        val strWord = vocabulary?.success?.get(i)?.word!!
                        val strMean = vocabulary?.success?.get(i)?.mean!!
                        val strExample = vocabulary?.success?.get(i)?.example!!
                        val successDataRoom = SuccessVocabulary(0, strWord, strMean, strExample)
                        VocabularyDatabase.getInstance(context)?.vocabularyDAO()
                            ?.insertVocabulary(successDataRoom)
                    }
                }
            }

            override fun onFailure(call: Call<Vocabulary?>, t: Throwable) {
                context.toast("SHOW LIST VOCABULARY FAILED")
            }
        })
        return null
    }
}