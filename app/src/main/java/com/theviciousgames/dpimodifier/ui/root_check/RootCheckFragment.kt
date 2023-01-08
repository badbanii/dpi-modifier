package com.theviciousgames.dpimodifier.ui.root_check

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.maxkeppeler.sheets.core.SheetStyle
import com.maxkeppeler.sheets.info.InfoSheet
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
    private var dialog: InfoSheet = InfoSheet()

    private val binding: FragmentRootCheckBinding
        get() = _binding!!

    private fun buttonFunctions() {
        binding.buttonSecurePermissionTutorial.setOnClickListener {
            showTutorialDialog()
        }
        binding.buttonInfo.setOnClickListener {
            showInfoDialog()
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

            if(viewModel.isWriteSecureSettingsPermissionGranted(requireContext()))
            {
                deviceStatus=DeviceStatus.GRANTED_SECURE_PERMISSION
                //findNavController().navigate(R.id.action_rootCheckFragment_to_dashboardFragment)
            }
            else
            {
                binding.textviewRootStatus.text = "Your device is not rooted and WRITE_SECURE_SETTINGS is not granted."
                this@RootCheckFragment.context?.let{ it1 -> Toasty.info(it1, "Device not rooted. You have to grant <WRITE SECURE SETTINGS> PERMISSION.", Toast.LENGTH_SHORT, false).show() }
            }
        }
    }

    private fun showTutorialDialog()
    {
          dialog=InfoSheet().build(requireActivity()) {
                title("Grant WRITE_SECURE_PERMISSION")
                displayNegativeButton(false)
                content("WRITE_SECURE_PERMISSION is a special permission that allows the app to modify the system's settings data.\n" +
                        "As you know, root is needed for changing the DPI of your device but you can also do it this way.\n\nFollow the steps:\n\n1.Enable developer mode: go to android settings -> about phone and look for the build number option. Tap it multiple times until developer mode is enabled.\n2.Enable USB debugging: go to android settings -> developer options and look for USB debugging\n3.Setup ADB on your PC.\n4.Connect device with computer: connect your device with your computer and look at your phone. A prompt may show up asking you to allow debugging by your computer, accept.\n5.In terminal, write:\nadb shell pm grant com.theviciousgames.dpimodifier android.permission.WRITE_SECURE_SETTINGS")
                onPositive("Ok") {
                }
            }
        dialog.show()
    }

    private fun showInfoDialog()
    {
        dialog=InfoSheet().build(requireActivity()) {
            title("Info")
            displayNegativeButton(false)
            style(SheetStyle.DIALOG)
            content("In order to change your device's DPI, it has to be either rooted or WRITE_SECURE_PERMISSION granted.\nNote: On some MIUI devices you need to enable the option 'Disable permission Monitoring' under 'Developer options' to be able to grant this permission, you can read more online about it.")
            onPositive("Ok") {
            }
        }
        dialog.show()
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