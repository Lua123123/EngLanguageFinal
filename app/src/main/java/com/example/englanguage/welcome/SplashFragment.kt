package com.example.englanguage.welcome

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.englanguage.LoginActivity
import com.example.englanguage.R
import com.example.englanguage.extensions.launchActivity

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            //KIỂM TRA
            if (onBoardingFinished()) {
                requireActivity().launchActivity(LoginActivity::class.java)
                requireActivity().finish()
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }
        }, 3000)
    }

    /**
     * Hàm kiểm tra nếu vào lần đầu thì qua FirstFragment, không thì vô LoginActivity
     * check hàm này ở ThirdFragment(HÀM LƯU TRẠNG THÁI ĐẦU TIÊN ĐÃ VÀO APP, LẦN SAU SẼ KHÔNG HIỂN THỊ NỮA)
     */
    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}