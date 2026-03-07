package com.android.vkeducation.intents

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.vkeducation.R
import androidx.activity.ComponentActivity
class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val tvReceivedText = findViewById<TextView>(R.id.tvReceivedText)
        val btnBack = findViewById<Button>(R.id.btnBack)


        val receivedText = intent.getStringExtra(MainActivity.EXTRA_TEXT)
            ?: "Текст не получен"

        tvReceivedText.text = receivedText

        btnBack.setOnClickListener { finish() }
    }
}