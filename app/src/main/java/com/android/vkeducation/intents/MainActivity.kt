package com.android.vkeducation.intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.vkeducation.R
import androidx.core.net.toUri

import androidx.activity.ComponentActivity
class MainActivity : ComponentActivity(){

    companion object {
        const val EXTRA_TEXT = "Vozmi Telephooon Detka"
    }

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        editText = findViewById(R.id.editText)
        val btnOpenSecond = findViewById<Button>(R.id.btnOpenSecond)
        val btnDial = findViewById<Button>(R.id.btnDial)
        val btnShare = findViewById<Button>(R.id.btnShare)

        btnOpenSecond.setOnClickListener {
            val text = editText.text.toString().trim()

            if (text.isEmpty()) {
                showToast("Введите текст для передачи")
                return@setOnClickListener
            }

            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra(EXTRA_TEXT, text)
            }
            startActivity(intent)
        }

        btnDial.setOnClickListener {
            val phone = editText.text.toString().trim()

            if (!isValidPhone(phone)) {
                showToast("Введите корректный номер телефона")
                return@setOnClickListener
            }

            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                data = "tel:$phone".toUri()
            }

            if (dialIntent.resolveActivity(packageManager) != null) {
                startActivity(dialIntent)
            } else {
                showToast("Приложение для звонков не найдено")
            }
        }

        btnShare.setOnClickListener {
            val text = editText.text.toString().trim()

            if (text.isEmpty()) {
                showToast("Введите текст для отправки")
                return@setOnClickListener
            }

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, text)
            }

            startActivity(Intent.createChooser(shareIntent, "Поделиться через..."))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }




    private fun isValidPhone(phone: String): Boolean {
        if (phone.isEmpty()) return false
        val digitsOnly = phone.filter { it.isDigit() }
        return digitsOnly.length >= 7 && phone.matches(Regex("[+\\d\\s()\\-]+"))
    }
}