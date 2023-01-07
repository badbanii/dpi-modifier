package com.theviciousgames.dpimodifier.ui.root_check

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentRootCheckBinding
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import eu.chainfire.libsuperuser.Shell.SU

@AndroidEntryPoint
class RootCheckFragment : Fragment(R.layout.fragment_root_check) {

    private var _binding: FragmentRootCheckBinding? = null
    private val viewModel: RootCheckFragmentViewModel by viewModels()

    private val binding: FragmentRootCheckBinding
        get() = _binding!!

    private fun buttonFunctions() {
        binding.buttonRefresh.setOnClickListener {
            if (SU.available()) {
                binding.textviewRootStatus.text = "Your device is rooted."
                binding.imageviewRootStatus.load(R.drawable.ic_check)
                findNavController().navigate(R.id.action_rootCheckFragment_to_dashboardFragment)
            } else {
                binding.textviewRootStatus.text = "Your device is not rooted."
                binding.imageviewRootStatus.load(R.drawable.ic_close)
                this@RootCheckFragment.context?.let { it1 -> Toasty.error(it1, "Device not rooted. If you didn't grant root privilege to the app please reinstall and grant the needed permission.", Toast.LENGTH_SHORT, true).show() }
            }
        }
    }

    private fun updateUi() {

        if (SU.available()) {
            binding.textviewRootStatus.text = "Your device is rooted."
            binding.imageviewRootStatus.load(R.drawable.ic_check)
            findNavController().navigate(R.id.action_rootCheckFragment_to_dashboardFragment)
        } else {
            binding.textviewRootStatus.text = "Your device is not rooted."
            binding.imageviewRootStatus.load(R.drawable.ic_close)
            this@RootCheckFragment.context?.let { it1 -> Toasty.error(it1, "Device not rooted. If you didn't grant root privilege to the app please reinstall and grant the needed permission.", Toast.LENGTH_SHORT, true).show() }

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