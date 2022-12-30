package com.theviciousgames.dpimodifier.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentDashboardBinding
import com.theviciousgames.dpimodifier.getDpi

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
   // private val viewModel: FragmentDashboardBinding by viewModels()

    private val binding: FragmentDashboardBinding
        get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDashboardBinding.bind(view)

        binding.textviewDpiVal.text= getDpi(requireActivity()).toString()

        binding.buttonSimple.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_newbieFragment)
        }

    }
}