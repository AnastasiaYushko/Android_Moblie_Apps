package com.example.ma_ex_28_k;

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyBroadcastReceiver(private val onReceiveAction: (String) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.example.ma_ex_28_k.CUSTOM_ACTION") {
            val message = intent.getStringExtra("message") ?: "No Message"
            onReceiveAction(message)
        }
    }
}
