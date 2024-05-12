package com.example.startkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        (findViewById<TextView>(R.id.one)).apply {
            this.setOnClickListener {
                startActivity(Intent(this@Activity3, Activity1::class.java))
            }
        }
        (findViewById<TextView>(R.id.two)).apply {
            this.setOnClickListener {
                startActivity(Intent(this@Activity3, Activity2::class.java))
            }
        }
        (findViewById<TextView>(R.id.three)).apply {
            this.setOnClickListener {
                startActivity(Intent(this@Activity3, Activity3::class.java))
            }
        }
    }
}