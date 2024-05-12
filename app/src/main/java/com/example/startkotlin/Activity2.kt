package com.example.startkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_2)
        (findViewById<TextView>(R.id.one)).apply {
            this.setOnClickListener {
                startActivity(Intent(this@Activity2, Activity1::class.java))
            }
        }
        (findViewById<TextView>(R.id.two)).apply {
            this.setOnClickListener {
                startActivity(Intent(this@Activity2, Activity2::class.java))
            }
        }
        (findViewById<TextView>(R.id.three)).apply {
            this.setOnClickListener {
                startActivity(Intent(this@Activity2, Activity3::class.java))
            }
        }
    }
}