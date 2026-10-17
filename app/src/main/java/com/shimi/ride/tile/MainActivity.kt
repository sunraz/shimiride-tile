package com.shimi.ride.tile

import android.app.Activity
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 96, 48, 48)
        }
        val status = TextView(this).apply { textSize = 18f }
        val btnBattery = Button(this).apply { text = "פטור מסוללה" }
        val btnPerm = Button(this).apply { text = "הרשאת מיקום" }
        val btnStop = Button(this).apply { text = "עצירת שידור" }

        fun refresh() {
            status.text = if (LocationForegroundService.hasLocationPermission(this@MainActivity)) {
                if (LocationForegroundService.isRunning) "שידור מיקום: פעיל" else "שידור מיקום: כבוי"
            } else "נדרשת הרשאת מיקום"
        }

        btnPerm.setOnClickListener {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
        btnBattery.setOnClickListener {
            val pm = getSystemService(POWER_SERVICE) as PowerManager
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                startActivity(Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                    Uri.parse("package:$packageName")))
            }
        }
        btnStop.setOnClickListener {
            LocationForegroundService.stop(this)
            refresh()
        }

        root.addView(status)
        root.addView(btnPerm)
        root.addView(btnBattery)
        root.addView(btnStop)
        setContentView(root)
        refresh()
    }

    override fun onResume() {
        super.onResume()
        // רענון סטטוס בחזרה
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        recreate()
    }
}
