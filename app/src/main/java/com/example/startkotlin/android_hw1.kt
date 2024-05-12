package com.example.startkotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class android_hw1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val TAG: String = "HW1"
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_android_hw1)
        val ans: TextView = findViewById(R.id.display)
        val num0: Button = findViewById(R.id.num0)
        val num1: Button = findViewById(R.id.num1)
        val num2: Button = findViewById(R.id.num2)
        val num3: Button = findViewById(R.id.num3)
        val num4: Button = findViewById(R.id.num4)
        val num5: Button = findViewById(R.id.num5)
        val num6: Button = findViewById(R.id.num6)
        val num7: Button = findViewById(R.id.num7)
        val num8: Button = findViewById(R.id.num8)
        val num9: Button = findViewById(R.id.num9)
        val clear: Button = findViewById(R.id.clear)
        val plus: Button = findViewById(R.id.plus)
        val calculate: Button = findViewById(R.id.calculate)

        var res: Int = 0
        var temp: String = "0"
        val num = mutableListOf<Button>(num0,num1,num2,num3,num4,num5,num6,num7,num8,num9)
        for (i in num.indices) {
            num[i].setOnClickListener {
                if (temp == "0") temp = i.toString()
                else temp += i.toString()
                ans.text = temp
                Log.d(TAG,"num$i pressed")
                Log.d(TAG, ans.text.toString())
            }
        }
        clear.setOnClickListener {
            temp = "0"
            ans.text = temp
            res = 0
        }
        plus.setOnClickListener {
            res += temp.toInt()
            temp = "0"
            ans.text = res.toString()
        }
        calculate.setOnClickListener {
            res += temp.toInt()
            temp = "0"
            ans.text = res.toString()
        }
    }
}