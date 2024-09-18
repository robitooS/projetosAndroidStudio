package com.example.aplicacaosharedpreferences

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nome = findViewById<EditText>(R.id.nomeUser)
        val telefone = findViewById<EditText>(R.id.telUser)
        val button = findViewById<Button>(R.id.buttonNext)

        val txtNome = findViewById<TextView>(R.id.txtNome)
        val txtTel = findViewById<TextView>(R.id.txtTel)

        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("NOME", "")
        val savedPhone = sharedPreferences.getString("TELEFONE", "")

        txtNome.text = "$savedName"
        txtTel.text = "$savedPhone"

        button.setOnClickListener {
            val nomeView = nome.text.toString()
            val telView = telefone.text.toString()

            val editor = sharedPreferences.edit()
            editor.putString("NOME", nomeView)
            editor.putString("TELEFONE", telView)
            editor.apply()

            txtNome.text = "$nomeView"
            txtTel.text = "$telView"
        }
    }
}