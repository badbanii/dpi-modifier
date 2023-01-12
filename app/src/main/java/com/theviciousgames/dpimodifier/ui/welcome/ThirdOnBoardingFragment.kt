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

class ThirdOnBoardingFragment : Fragment(R.layout.fragment_onboarding) {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding: FragmentOnboardingBinding get() = _binding!!
    private val viewModel: OnBoardingViewModel by viewModels()

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

    private fun navigateToMainScreen()
    {
        findNavController().navigate(R.id.action_thirdOnBoardingFragment_to_rootCheckFragment)
    }

    private fun setUserIsOld()
    {
        viewModel.setUserIsOld()
    }

    private fun updateUi()
    {
        with(binding)
        {
            textviewTitle.text="Our website is waiting for you!"
            textviewDescription.text="www.aBetterAndroid.com\nIf you're curious enough\n\uD83E\uDD14"
            animationView.setAnimation(R.raw.lottie_website)
            animationView.scaleType=ImageView.ScaleType.CENTER_CROP
            buttonMoreApps.visibility=View.INVISIBLE
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
                setUserIsOld()
                navigateToMainScreen()
            }
        }
    }
}