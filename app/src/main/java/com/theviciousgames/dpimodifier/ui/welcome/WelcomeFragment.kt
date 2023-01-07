package com.theviciousgames.dpimodifier.ui.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fxn.stash.Stash
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentWelcomeBinding
import com.theviciousgames.dpimodifier.ui.newbie.NewbieFragmentViewModel
import com.theviciousgames.dpimodifier.utils.Constants.USER_IS_OLD

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private var _binding: FragmentWelcomeBinding? = null
    private val viewModel: NewbieFragmentViewModel by viewModels()

    private val binding: FragmentWelcomeBinding
        get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
          _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       _binding = FragmentWelcomeBinding.bind(view)
        checkIfUserIsOld()
        buttonFunctions()

    }

    private fun buttonFunctions()
    {
        binding.buttonContinue.setOnClickListener {
            Stash.put(USER_IS_OLD,true)
            findNavController().navigate(R.id.action_welcomeFragment_to_rootCheckFragment)
        }
    }

    private fun checkIfUserIsOld()
    {
        if(Stash.getBoolean(USER_IS_OLD,false))
        {
            findNavController().navigate(R.id.action_welcomeFragment_to_rootCheckFragment)
        }
    }
}