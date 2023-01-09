package com.theviciousgames.dpimodifier.ui.menu

import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentMenuBinding
import com.theviciousgames.dpimodifier.ui.newbie.NewbieFragmentViewModel
import kotlin.system.exitProcess

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private var _binding: FragmentMenuBinding? = null
    private val viewModel: NewbieFragmentViewModel by viewModels()

    private val binding: FragmentMenuBinding
        get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
          _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       _binding = FragmentMenuBinding.bind(view)
        buttonFunctions()

    }

    private fun buttonFunctions()
    {
        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.buttonQuit.setOnClickListener {
            closeApp()
        }
    }
    private fun closeApp()
    {
        finishAffinity(requireActivity())
        requireActivity().finish()
        exitProcess(0)
    }
}