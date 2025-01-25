package com.example.ma_ex_28_k;

import android.content.*
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var textViewStatus: TextView
    private lateinit var buttonSend: Button
    private lateinit var button_2: Button
    private lateinit var customReceiver: MyBroadcastReceiver
    private lateinit var connectivityReceiver: BroadcastReceiver
    private val timeBroadcastReceiver = TimeBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewStatus = findViewById(R.id.text_view_status)
        buttonSend = findViewById(R.id.button_send)
        button_2 = findViewById(R.id.button_2)

        customReceiver = MyBroadcastReceiver { message ->
            Log.d("MY broadcast", message)
        }

        connectivityReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val network = connectivityManager.activeNetwork
                val capabilities = connectivityManager.getNetworkCapabilities(network)
                val isConnected = capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
                textViewStatus.text = if (isConnected) "Подключен к Интернету" else "Нет подключения к Интернету"
            }
        }

        buttonSend.setOnClickListener {
            val intent = Intent("com.example.ma_ex_28_k.CUSTOM_ACTION")
            intent.putExtra("message", "Срочно покорми кота!")
            sendBroadcast(intent)
            val text_message : String = intent.getStringExtra("message").toString()
            Toast.makeText(this, text_message,
                Toast.LENGTH_LONG).show()
        }

        button_2.setOnClickListener {
            registerReceiver(
                timeBroadcastReceiver, IntentFilter(
                    "android.intent.action.TIME_TICK"
                )
            )
            Toast.makeText(
                getApplicationContext(), "Приёмник включен",
                Toast.LENGTH_SHORT
            ).show();
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onResume() {
        super.onResume()
        val customFilter = IntentFilter("com.example.ma_ex_28_k.CUSTOM_ACTION")
        registerReceiver(customReceiver, customFilter, RECEIVER_EXPORTED)

        val connectivityFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(connectivityReceiver, connectivityFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(customReceiver)
        unregisterReceiver(connectivityReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(timeBroadcastReceiver)
        Toast.makeText(
            getApplicationContext(),
            "Приёмник выключён", Toast.LENGTH_SHORT
        )
            .show();
    }
}
