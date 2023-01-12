package com.theviciousgames.dpimodifier.ui.newbie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maxkeppeler.sheets.input.InputSheet
import com.maxkeppeler.sheets.input.type.InputCheckBox
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentNewbieBinding
import com.theviciousgames.dpimodifier.utils.Operation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewbieFragment : Fragment(R.layout.fragment_newbie) {

    private var _binding: FragmentNewbieBinding? = null
    private val viewModel: NewbieFragmentViewModel by viewModels()
    private var dialog: InputSheet = InputSheet()
    private val binding: FragmentNewbieBinding
        get() = _binding!!

    private fun buttonFunctions() {
        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.buttonAdd.setOnClickListener {
            increaseDpiButtonPressed()
        }
        binding.buttonSubstract.setOnClickListener {
            decreaseDpiButtonPressed()
        }
        binding.buttonSettings.setOnClickListener {
            showSettingsDialog()
        }
    }

    private fun decreaseDpiButtonPressed() {
        showDpiDialog(Operation.DECREASE)
    }

    private fun getCurrentDpi(): Int {
        return viewModel.getDpi(requireActivity())
    }

    private fun increaseDpiButtonPressed() {
      showDpiDialog(Operation.INCREASE)
    }

    private fun setDisplayDensity(newDpiValue: Int,operation: Operation) {
        viewModel.setDisplayDensity(newDpiValue,operation)
    }
    private fun getDisplayDensity():Int
    {
        return viewModel.getDpi(requireActivity())
    }

    private fun updateDpi(operation: Operation) {
        setDisplayDensity(getDisplayDensity(),operation)
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

    private fun showDpiDialog(operation: Operation) {
        if (viewModel.getShowConfirmationSetting()) {
            dialog = InputSheet().build(requireActivity()) {
                title("Are you sure?")
                displayPositiveButton(true)
                displayCloseButton(false)
                displayNegativeButton(false)

                with(InputCheckBox("never_show_checkbox") {
                    label("Don't show this again?")
                    text("Confirm")
                })

                onNegative {}
                onPositive { result ->
                    val check = result.getBoolean("never_show_checkbox")
                    if (check) {
                        viewModel.setShowConfirmationSetting(false)
                    }
                    updateDpi(operation)
                }
            }
            dialog.show()
        } else {
            updateDpi(operation)
        }
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
