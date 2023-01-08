package com.theviciousgames.dpimodifier.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.maxkeppeler.sheets.input.InputSheet
import com.maxkeppeler.sheets.input.type.InputCheckBox
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentDashboardBinding
import com.theviciousgames.dpimodifier.getDpi
import dagger.hilt.android.AndroidEntryPoint
import eu.chainfire.libsuperuser.Shell

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    private val viewModel: DashboardFragmentViewModel by viewModels()
    private var dialog: InputSheet = InputSheet()

    ///adb shell appops set foo.bar.package WRITE_SETTINGS allow
    ///Switch on "Developer Options" / "Disable Permission Monitoring" (at the end of the section)
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
        binding.buttonSettings.setOnClickListener {
            showSettingsDialog()
        }

        binding.buttonSimple.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_newbieFragment)
        }

        binding.buttonAdvanced.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_advancedFragment)
        }
        binding.buttonReset.setOnClickListener {
            viewModel.resetDpiToDefault()
        }
    }
    private fun showSettingsDialog() {
        dialog = InputSheet().build(requireActivity()) {
            title("Settings")
            displayPositiveButton(true)
            displayCloseButton(false)
            displayNegativeButton(false)
            with(InputCheckBox("never_show_checkbox") {
                label("Hide confirmation when changing DPI?")
                text("Yes")
                if (!viewModel.getShowConfirmationSetting())
                {
                    defaultValue(true)
                }
            })
            onNegative {}
            onPositive { result ->
                val check = result.getBoolean("never_show_checkbox")
                viewModel.setShowConfirmationSetting(!check)
            }
        }
        dialog.show()
    }
    private fun updateUi()
    {
        binding.textviewDpiVal.text = getCurrentDpi().toString()

        if(Shell.SU.available())
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