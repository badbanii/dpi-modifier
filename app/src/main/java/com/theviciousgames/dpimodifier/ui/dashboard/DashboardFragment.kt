package com.theviciousgames.dpimodifier.ui.dashboard

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.maxkeppeler.sheets.input.InputSheet
import com.maxkeppeler.sheets.input.type.InputCheckBox
import com.maxkeppeler.sheets.input.type.InputEditText
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentDashboardBinding
import com.theviciousgames.dpimodifier.model.Preset
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    private val viewModel: DashboardFragmentViewModel by viewModels()
    private var dialog: InputSheet = InputSheet()
    private var presetAdapter: PresetAdapter? = null
    private var presetList = mutableListOf<Preset>()

    ///adb shell appops set foo.bar.package WRITE_SETTINGS allow
    ///Switch on "Developer Options" / "Disable Permission Monitoring" (at the end of the section)

    private val binding: FragmentDashboardBinding
        get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDashboardBinding.bind(view)
        updateUi()
        buttonFunctions()
        setupRecyclerView()
        addListToRecyclerView()
        handleAddPresetButton()
    }

    private fun addListToRecyclerView() {
        presetList = getPresetList().toMutableList()
        presetAdapter?.differ?.submitList(presetList)
    }

    private fun buttonFunctions() {
        binding.buttonSettings.setOnClickListener {
            showSettingsDialog()
        }
        binding.buttonMenu.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_menuFragment)
        }
        binding.buttonSimple.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_newbieFragment)
        }
        binding.buttonAdvanced.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_advancedFragment)
        }
        binding.buttonReset.setOnClickListener {
            viewModel.resetDisplayDensity()
        }
        binding.buttonAdd.setOnClickListener {
            showPresetsDialog()
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


    private fun showPresetsDialog() {

        dialog = InputSheet().build(requireActivity()) {
            title("Settings")
            displayPositiveButton(true)
            displayCloseButton(false)
            displayNegativeButton(false)

            with(InputEditText("preset_name") {
                label("Preset's name")
                required(true)
            })
            with(InputEditText("preset_dpi") {
                label("Preset's DPI")
                inputType(InputType.TYPE_CLASS_NUMBER)
                required(true)
            })
            onNegative {}
            onPositive { result ->
                val name = result.getString("preset_name")
                val dpi = result.getString("preset_dpi")

                name?.let { dpi?.let { it1 -> Preset(it, it1.toInt()) } }
                    ?.let { presetList.add(it) }
                setPresetList(presetList)
                setupRecyclerView()
                addListToRecyclerView()
                handleAddPresetButton()
            }
        }
        dialog.show()
    }


    private fun updateUi() {
        binding.textviewDpiVal.text = getDisplayDensity().toString()

        if (viewModel.getRootAccess()) {
            binding.textviewRootStatus.text = "Your device is rooted."
            binding.imageviewRootStatus.load(R.drawable.ic_check)
        } else {
            binding.textviewRootStatus.text = "Your device is not rooted."
            binding.imageviewRootStatus.load(R.drawable.ic_close)
        }
    }

    private fun getDisplayDensity(): Int {
        return viewModel.getDisplayDensity(requireActivity())
    }

    private fun getPresetList(): List<Preset> {
        return viewModel.getPresetList()
    }

    private fun setDisplayDensity(value: Int) {
        viewModel.setDisplayDensity(value)
    }

    private fun setPresetList(list: List<Preset>) {
        viewModel.setPresetList(list)
    }

    private fun setupRecyclerView() {
        presetAdapter = PresetAdapter(PresetAdapter.OnClickListenerDelete {
            presetList.remove(it)
            setPresetList(presetList)
            setupRecyclerView()
            addListToRecyclerView()
            handleAddPresetButton()
        }, PresetAdapter.OnClickListenerSet {
            setDisplayDensity(it.value)
        })

        binding.recyclerview.apply {
            adapter = presetAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun handleAddPresetButton() {
        if (presetList.size > 1) {
            binding.buttonAdd.visibility = View.GONE
        } else {
            binding.buttonAdd.visibility = View.VISIBLE
        }
    }
}