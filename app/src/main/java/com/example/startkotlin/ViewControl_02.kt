package com.example.startkotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

val TAG: String = "ViewControl"
class ViewControl_02 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_control_02)
        Log.d("MAIN","onCreate!")

        val textView1: TextView = findViewById(R.id.text1)
        val btn1: Button = findViewById(R.id.btn1)
        Log.d(TAG, textView1.text.toString())

        btn1.setOnClickListener {
            Log.d(TAG, "버튼 클릭!")
        }

        val clickListener1 = object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d(TAG, "버튼 클릭!!")
            }
        }
        btn1.setOnClickListener(clickListener1)

        btn1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d(TAG, "버튼 클릭!!")
            }
        })
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