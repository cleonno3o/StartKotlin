package com.example.startkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        (findViewById<TextView>(R.id.one)).apply {
            this.setOnClickListener {
                startActivity(Intent(this@Activity1, Activity1::class.java))
            }
        }
        (findViewById<TextView>(R.id.two)).apply {
            this.setOnClickListener {
                val myIntent = Intent(this@Activity1, Activity2::class.java)
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(myIntent)
            }
        }
        (findViewById<TextView>(R.id.three)).apply {
            this.setOnClickListener {
                startActivity(Intent(this@Activity1, Activity3::class.java))
            }
        }
    }
}