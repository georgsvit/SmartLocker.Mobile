package com.example.smartlockermobile.ui.tools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smartlockermobile.R

class ToolsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tools)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = getString(R.string.tools_title)
        }
    }
}