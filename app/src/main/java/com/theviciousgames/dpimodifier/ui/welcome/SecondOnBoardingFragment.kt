package com.theviciousgames.dpimodifier.ui.welcome

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentOnboardingBinding

class SecondOnBoardingFragment : Fragment(R.layout.fragment_onboarding) {

    private var _binding: FragmentOnboardingBinding? = null
    private val viewModel: OnBoardingViewModel by viewModels()
    private val binding: FragmentOnboardingBinding
        get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnboardingBinding.bind(view)
        updateUi()
        buttonFunctions()
    }

    private fun navigateToThirdOnBoardingScreen() {
        findNavController().navigate(R.id.action_secondOnBoardingFragment_to_thirdOnBoardingFragment)
    }

    private fun updateUi() {
        with(binding)
        {
            textviewTitle.text = "Check out our other apps!"
            textviewDescription.text =
                "Are you looking to change your resolution? Check your device's HDR version?\nVisit us by pressing 'more'!\naBetterAndroid has everything you need."
            animationView.setAnimation(R.raw.lottie_more)
            animationView.scaleType = ImageView.ScaleType.CENTER_CROP
            buttonMoreApps.visibility = View.VISIBLE
        }
    }

    private fun buttonFunctions() {
        with(binding) {

            buttonMoreApps.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/dev?id=8049005269403185530")
                    )
                )
            }
            buttonNext.setOnClickListener {
                navigateToThirdOnBoardingScreen()
            }
        }
    }
}