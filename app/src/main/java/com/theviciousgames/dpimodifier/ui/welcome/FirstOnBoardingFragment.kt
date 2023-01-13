package com.theviciousgames.dpimodifier.ui.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentOnboardingBinding

class FirstOnBoardingFragment : Fragment(R.layout.fragment_onboarding) {

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
        navigateToMainScreenIfUserIsOld()
        updateUi()
        buttonFunctions()
    }

    private fun navigateToSecondOnBoardingScreen() {
        findNavController().navigate(R.id.action_firstOnBoardingFragment_to_secondOnBoardingFragment)
    }

    private fun navigateToRootCheckScreen() {
        findNavController().navigate(R.id.action_firstOnBoardingFragment_to_rootCheckFragment)
    }

    private fun navigateToMainScreenIfUserIsOld() {
        if (viewModel.getUserIsOld()) {
            navigateToRootCheckScreen()
        }
    }

    private fun updateUi() {
        with(binding)
        {
            textviewTitle.text = "Change your DPI!"
            textviewDescription.text =
                "You either want to be a great gamer with a better aim or you simply want a zoomed-out screen and you came to the right place!\n Lets us know in a review what's your opinion!"

        }
    }

    private fun buttonFunctions() {
        with(binding) {

            buttonNext.setOnClickListener {
                navigateToSecondOnBoardingScreen()
            }
        }
    }
}