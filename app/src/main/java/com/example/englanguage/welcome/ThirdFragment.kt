package com.example.englanguage.welcome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.englanguage.LoginActivity
import com.example.englanguage.R
import com.example.englanguage.databinding.FragmentThirdBinding
import com.example.englanguage.extensions.launchActivity

class ThirdFragment : Fragment() {
    private lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    //HÀM LƯU TRẠ_NG THÁ_I ĐẦU TIÊ_N ĐÃ VÀO APP, LẦN SAU SẼ KHÔ_NG HIỂ_N THỊ NỮA
    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.finish.setOnClickListener {
            requireActivity().launchActivity(LoginActivity::class.java)
            onBoardingFinished()
            requireActivity().finish()
        }
    }

}