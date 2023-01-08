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
import com.theviciousgames.dpimodifier.utils.DeviceStatus
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import eu.chainfire.libsuperuser.Shell.SU

@AndroidEntryPoint
class RootCheckFragment : Fragment(R.layout.fragment_root_check) {

    private var _binding: FragmentRootCheckBinding? = null
    private val viewModel: RootCheckFragmentViewModel by viewModels()
    private lateinit var deviceStatus: DeviceStatus

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
            deviceStatus=DeviceStatus.ROOTED
            binding.textviewRootStatus.text = "Your device is rooted."
            binding.imageviewRootStatus.load(R.drawable.ic_check)
            findNavController().navigate(R.id.action_rootCheckFragment_to_dashboardFragment)
        } else {
            deviceStatus=DeviceStatus.NOT_ROOTED
            binding.textviewRootStatus.text = "Your device is not rooted."
            binding.imageviewRootStatus.load(R.drawable.ic_close)
            this@RootCheckFragment.context?.let{
                    it1 ->
                Toasty.info(it1, "Device not rooted. You have to grant <WRITE SECURE SETTINGS> PERMISSION.",
                    Toast.LENGTH_SHORT, true).show() }

            if(viewModel.isWriteSecureSettingsPermissionGranted(requireContext()))
            {
                deviceStatus=DeviceStatus.GRANTED_SECURE_PERMISSION
             //findNavController().navigate(R.id.action_rootCheckFragment_to_dashboardFragment)
            }
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