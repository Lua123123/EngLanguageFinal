package com.example.englanguage.offlinemode.vocabularyoffline

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.englanguage.databinding.FragmentVocabularyBinding
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englanguage.R
import com.example.englanguage.database.VocabularyDatabase
import com.example.englanguage.model.vocabulary.SuccessVocabulary

class VocabularyFragment : Fragment() {
    private lateinit var binding: FragmentVocabularyBinding

    private var adapter: ListVocabularyAdapterOffline? = null
    private var mListVocabulary: List<SuccessVocabulary> = ArrayList()
    private var layoutManager: LinearLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vocabulary, container, false)
        binding.lifecycleOwner = this
        val view = binding.root
        view.findViewById<ConstraintLayout>(R.id.imgBackOffline).setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_vocabularyFragment_to_mainFragment)
        }
        return view
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListVocabularyAdapterOffline(context)
        layoutManager = LinearLayoutManager(context)
        binding.recyclerViewOffline.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(this.resources.getDrawable(R.drawable.divider_rcv))
        binding.recyclerViewOffline.addItemDecoration(itemDecoration)

        mListVocabulary = VocabularyDatabase.getInstance(context!!)?.vocabularyDAO()
            ?.getListVocabulary() as MutableList<SuccessVocabulary>
        adapter?.setData(mListVocabulary as MutableList<SuccessVocabulary>)
        binding.recyclerViewOffline.adapter = adapter

        //LOAD LAI
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            mListVocabulary = VocabularyDatabase.getInstance(context!!)?.vocabularyDAO()
                ?.getListVocabulary() as MutableList<SuccessVocabulary>
            adapter?.reload(mListVocabulary)
        }
    }
}