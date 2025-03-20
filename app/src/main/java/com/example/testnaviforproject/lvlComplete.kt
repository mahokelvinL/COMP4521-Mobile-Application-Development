package com.example.testnaviforproject

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class lvlComplete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.lvlcomplete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val lifeCountTextView = findViewById<TextView>(R.id.txLifeLeft)
        val timeLeftTextView = findViewById<TextView>(R.id.txTimeLeft)

        val lifeCount = intent.getIntExtra("lifeCount", 0)
        val timeLeft = intent.getStringExtra("timeLeft")

        lifeCountTextView.text = "Life Count: $lifeCount"
        timeLeftTextView.text = "$timeLeft"
    }
}