package com.example.englanguage.welcome

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.englanguage.R


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler(Looper.getMainLooper()).postDelayed({
            if (onBoardingFinished()) {
                findNavController().navigate(R.id.action_splashFragment_to_loginActivity)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }
        }, 3000)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    //Hàm kie_m tra nếu vào lần đầu thì qua FirstFragment, khô_ng thì vô LoginActivity
    //check hàm này ở ThirdFragment(HÀM LƯU TRẠ_NG THÁ_I ĐẦU TIÊ_N ĐÃ VÀO APP, LẦN SAU SẼ KHÔ_NG HIỂ_N THỊ NỮA)
    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}