package com.theviciousgames.dpimodifier.ui.advanced

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.maxkeppeler.sheets.input.InputSheet
import com.maxkeppeler.sheets.input.type.InputCheckBox
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentAdvancedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AdvancedFragment : Fragment(R.layout.fragment_advanced) {

    private var _binding: FragmentAdvancedBinding? = null
    private var dialog: InputSheet = InputSheet()
    private val viewModel: AdvancedFragmentViewModel by viewModels()

    private val binding: FragmentAdvancedBinding
        get() = _binding!!

    private fun buttonFunctions() {
        with(binding)
        {
            buttonBack.setOnClickListener {
                findNavController().navigateUp()
            }
           buttonConfirm.setOnClickListener {
                showDpiDialog(binding.edittextDpiValue.text.toString().toInt())
            }

            buttonSettings.setOnClickListener {
                showSettingsDialog()
            }
            buttonTest.setOnClickListener {
                showTestDialog(binding.edittextDpiValue.text.toString().toInt())
            }
            buttonReset.setOnClickListener {
                resetDisplayDensity()
            }
        }
    }

    private fun resetDisplayDensity()
    {
        viewModel.resetDisplayDensity()
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
                if (!viewModel.getShowConfirmationSetting()) {
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

    private fun setDisplayDensity(newDpiValue: Int) {
       viewModel.setDisplayDensity(newDpiValue)
    }

    private fun showDpiDialog(newDpiValue: Int) {
        if (viewModel.getShowConfirmationSetting()) {
            dialog = InputSheet().build(requireActivity()) {
                title("Are you sure?\nDPI will be $newDpiValue")
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
                    setDisplayDensity(newDpiValue)
                }
            }
            dialog.show()
        } else {
            setDisplayDensity(newDpiValue)
        }
    }

    private fun showTestDialog(newDpiValue: Int) {
        if (viewModel.getShowConfirmationSetting()) {
            dialog = InputSheet().build(requireActivity()) {
                title("New configuration: $newDpiValue DPI\nWill reset in 15 seconds.")
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
                    viewModel.saveCurrentConfiguration(viewModel.getDpi(requireActivity()).toString().toInt())
                    viewModel.runDelayedReset()
                    setDisplayDensity(binding.edittextDpiValue.text.toString().toInt())
                }
            }
            dialog.show()
        } else {
            viewModel.saveCurrentConfiguration(viewModel.getDpi(requireActivity()).toString().toInt())
            viewModel.runDelayedReset()
            setDisplayDensity(binding.edittextDpiValue.text.toString().toInt())
        }
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
    private fun updateUi() {
        binding.textviewDpiVal.text = viewModel.getDpi(requireActivity()).toString()
        binding.edittextDpiValue.text = viewModel.getDpi(requireActivity()).toString().toEditable()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAdvancedBinding.bind(view)
        updateUi()
        buttonFunctions()
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.resetDpi.collect {
                    when (it) {
                        true -> {
                            Log.d(
                                "DEBUG",
                                "SplashFragment -> updatePasswordResponse state is <LOADING>: $it"
                            )
                            setDisplayDensity(viewModel.oldDpi)
                            viewModel.resetDpiTriggerFlow()
                        }

                        false -> {
                            Log.d(
                                "DEBUG",
                                "SplashFragment -> updatePasswordResponse state is <FAILURE>}"
                            )
                        }
                    }
                }
            }
        }
    }
}