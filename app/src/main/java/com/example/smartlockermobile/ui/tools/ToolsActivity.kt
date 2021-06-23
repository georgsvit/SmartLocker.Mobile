package com.example.smartlockermobile.ui.tools

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.smartlockermobile.MainActivity
import com.example.smartlockermobile.R
import com.example.smartlockermobile.data.SessionManager
import com.example.smartlockermobile.ui.notifications.NotificationsActivity

class ToolsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tools)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = getString(R.string.tools_title)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.tools_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logout) {
            SessionManager(application).removeAllData()
            this.finish()
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        if(item.itemId == R.id.notification) {
            startActivity(Intent(applicationContext, NotificationsActivity::class.java))
        }
        return super.onOptionsItemSelected(item)

    }
}