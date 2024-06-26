package com.example.startkotlin

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test)
        Log.d("MAIN","onCreate!")
    }

    override fun onStart() {
        Log.d("MAIN","onStart!")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MAIN","onResume!")
        super.onResume()
    }

    override fun onPause() {
        Log.d("MAIN","onPause!")
        super.onPause()
    }

    override fun onStop() {
        Log.d("MAIN","onStop!")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MAIN","onDestroy!")
        super.onDestroy()
    }

    override fun onRestart() {
        Log.d("MAIN","onRestart!")
        super.onRestart()
    }
}