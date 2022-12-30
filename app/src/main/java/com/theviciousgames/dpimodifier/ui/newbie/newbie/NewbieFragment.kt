package com.theviciousgames.dpimodifier.ui.newbie.newbie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentNewbieBinding
import com.theviciousgames.dpimodifier.getDpi
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewbieFragment : Fragment(R.layout.fragment_newbie) {

    private val viewModel: NewbieFragmentViewModel by viewModels()

    private var _binding: FragmentNewbieBinding? = null
    private val binding: FragmentNewbieBinding
        get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewbieBinding.bind(view)

        Log.d("debug",getCurrentDpi().toString())
    }

    private fun updateDpiTo(dpi:Int)
    {
        viewModel.updateDpiTo(301)
    }

    private fun getCurrentDpi():Int
    {
        return getDpi(requireActivity())
    }
}