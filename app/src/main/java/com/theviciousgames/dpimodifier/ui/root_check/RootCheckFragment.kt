package com.theviciousgames.dpimodifier.ui.root_check

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentRootCheckBinding
import com.theviciousgames.dpimodifier.getDpi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootCheckFragment : Fragment(R.layout.fragment_root_check) {

    private var _binding: FragmentRootCheckBinding? = null
    private val viewModel: RootCheckFragmentViewModel by viewModels()

    private val binding: FragmentRootCheckBinding
        get() = _binding!!

    private fun buttonFunctions() {

    }

    private fun checkRoot(): Boolean {
        return viewModel.hasRootAccess()
    }

    private fun getCurrentDpi(): Int {
        return getDpi(requireActivity())
    }

    private fun updateUi() {

      //  viewModel.shellTest("su")

        if (2==2) {
            binding.textviewRootStatus.text = "Your device is rooted."
            binding.imageviewRootStatus.load(R.drawable.ic_check)
        } else {
            binding.textviewRootStatus.text = "Your device is not rooted."
            binding.imageviewRootStatus.load(R.drawable.ic_close)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRootCheckBinding.bind(view)
        updateUi()
        buttonFunctions()
    }
}