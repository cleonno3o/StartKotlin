package com.example.startkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ApplicationActivity1 : AppCompatActivity() {
    val TAG = "ApplicationActivity1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_application1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<TextView>(R.id.changeTo2).setOnClickListener {
            startActivity(Intent(this, ApplicationActivity2::class.java))
        }

        findViewById<TextView>(R.id.testMethod).setOnClickListener {
            (applicationContext as MasterApplication).methodOfApplication()
            val userId = (applicationContext as MasterApplication).userId
            Log.d(TAG, "" + userId)
        }
    }
}