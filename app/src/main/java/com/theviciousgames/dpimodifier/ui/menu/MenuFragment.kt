package com.theviciousgames.dpimodifier.ui.menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.suddenh4x.ratingdialog.AppRating
import com.theviciousgames.dpimodifier.R
import com.theviciousgames.dpimodifier.databinding.FragmentMenuBinding
import kotlin.system.exitProcess


class MenuFragment : Fragment(R.layout.fragment_menu) {

    private var _binding: FragmentMenuBinding? = null

    private val binding: FragmentMenuBinding
        get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMenuBinding.bind(view)
        buttonFunctions()
    }

    private fun buttonFunctions() {
        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.buttonQuit.setOnClickListener {
            closeApp()
        }
        binding.buttonRate.setOnClickListener {
            AppRating.Builder(requireActivity()).showNow()
        }
        binding.buttonShare.setOnClickListener {
            showSharingDialog()
        }
        binding.buttonMoreApps.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/dev?id=8049005269403185530")
                )
            )
        }
    }

    private fun showSharingDialog() {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "text/plain"
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "Download DPI Changer:\nhttps://play.google.com/store/apps/details?id=com.theviciousgames.dpimodifier"
        )
        startActivity(Intent.createChooser(intent, "Share with:"))
    }

    private fun closeApp() {
        finishAffinity(requireActivity())
        requireActivity().finish()
        exitProcess(0)
    }
}