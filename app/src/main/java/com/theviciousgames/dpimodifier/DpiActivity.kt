package com.theviciousgames.dpimodifier

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DpiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        keepScreenAwake()
    }

  private fun keepScreenAwake()
   {window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)}
}