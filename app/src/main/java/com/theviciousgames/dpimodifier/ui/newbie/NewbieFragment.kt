package com.theviciousgames.dpimodifier.ui.newbie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentNewbieBinding
import com.theviciousgames.dpimodifier.getDpi
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewbieFragment : Fragment(R.layout.fragment_newbie) {

    private var _binding: FragmentNewbieBinding? = null
    private val viewModel: NewbieFragmentViewModel by viewModels()

    private val binding: FragmentNewbieBinding
        get() = _binding!!

    private fun buttonFunctions() {
        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.buttonAdd.setOnClickListener {
            increaseDpiByFive()
        }
        binding.buttonSubstract.setOnClickListener {
            decreaseDpiByFive()
        }
    }

    private fun decreaseDpiByFive() {
        viewModel.updateDpiTo(getDpi(requireActivity()) - 5)
    }

    private fun getCurrentDpi(): Int {
        return getDpi(requireActivity())
    }

    private fun increaseDpiByFive() {
        viewModel.updateDpiTo(getDpi(requireActivity()) + 5)
    }

    private fun updateUi() {
        binding.textviewDpiVal.text = getCurrentDpi().toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewbieBinding.bind(view)
        updateUi()
        buttonFunctions()
    }

}