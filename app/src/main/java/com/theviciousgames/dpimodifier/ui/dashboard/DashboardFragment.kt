package com.theviciousgames.dpimodifier.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentDashboardBinding
import com.theviciousgames.dpimodifier.getDpi
import com.theviciousgames.dpimodifier.su.SuShell
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    private val viewModel: DashboardFragmentViewModel by viewModels()

    private val binding: FragmentDashboardBinding
        get() = _binding!!

    private fun checkRoot(): Boolean? {
        return viewModel.hasRootAccess()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDashboardBinding.bind(view)
        updateUi()
        buttonFunctions()

    }

    private fun buttonFunctions()
    {

        binding.buttonSimple.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_newbieFragment)
        }

        binding.buttonAdvanced.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_advancedFragment)
        }
    }

    private fun updateUi()
    {
        binding.textviewDpiVal.text = getCurrentDpi().toString()


        if(SuShell().hasRootAccess())
        {
            binding.textviewRootStatus.text="Your device is rooted."
            binding.imageviewRootStatus.load(R.drawable.ic_check)
        }
        else
        {
            binding.textviewRootStatus.text="Your device is not rooted."
            binding.imageviewRootStatus.load(R.drawable.ic_close)
        }
    }

    private fun getCurrentDpi(): Int {
        return getDpi(requireActivity())
    }
}