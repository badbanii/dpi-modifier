package com.theviciousgames.dpimodifier.ui.advanced

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentAdvancedBinding


class AdvancedFragment : Fragment(R.layout.fragment_advanced) {

    private val viewModel: AdvancedFragmentViewModel by viewModels()
    private var _binding: FragmentAdvancedBinding? = null

    private val binding: FragmentAdvancedBinding
        get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAdvancedBinding.bind(view)

        val dm = DisplayMetrics()
        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(dm)
        val dpiClassification = dm.densityDpi
        val xDpi = dm.xdpi
        val yDpi = dm.ydpi
        Log.d("debug",dm.density.toString())
}}