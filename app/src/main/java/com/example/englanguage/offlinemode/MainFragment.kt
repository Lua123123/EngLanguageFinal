package com.example.englanguage.offlinemode

import android.app.AlertDialog
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.englanguage.R
import com.example.englanguage.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this
        val view = binding.root
        view.findViewById<RelativeLayout>(R.id.vocabularyOffline).setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_mainFragment_to_vocabularyFragment)
        }
        view.findViewById<RelativeLayout>(R.id.topicOffline).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_topicFragment)
        }
        view.findViewById<RelativeLayout>(R.id.online_mode).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_loginFragmentOn)
        }
        view.findViewById<RelativeLayout>(R.id.flipcard).setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_mainFragment_to_flipCardOffActivity)
        }
        view.findViewById<RelativeLayout>(R.id.speak).setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_mainFragment_to_textToSpeechFragmentOff)
        }
        view.findViewById<RelativeLayout>(R.id.test).setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_mainFragment_to_startQuizActivity)
        }
        return view
    }

    private fun haveNetwork(): Boolean {
        var have_WIFI = false
        var have_MobileData = false
        val connectivityManager =
            context?.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfos = connectivityManager.allNetworkInfo
        for (info in networkInfos) {
            if (info.typeName.equals(
                    "WIFI",
                    ignoreCase = true
                )
            ) if (info.isConnected) have_WIFI =
                true
            if (info.typeName.equals(
                    "MOBILE",
                    ignoreCase = true
                )
            ) if (info.isConnected) have_MobileData = true
        }
        return have_WIFI || have_MobileData
    }
}