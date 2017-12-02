package com.example.feng.weiyi

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.telephony.TelephonyManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        val deviceId = telephonyManager.deviceId
        if (deviceId.equals("861079032425698")) {
            button.visibility = View.VISIBLE
            textview.visibility = View.GONE
        } else {
            button.visibility = View.GONE
            textview.visibility = View.VISIBLE
            Handler().postDelayed(Runnable { finish() }, 10000)
        }

        button.setOnClickListener { v: View? ->
            val intent = Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }
    }
}
