package com.example.startkotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.zip.Inflater

class AndroidHW3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_android_hw3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var info = mutableListOf<Person>()
        for (i in 1..20) {
            val midNumber = (0..9).shuffled().slice(0..3).joinToString("")
            val lastNumber = (0..9).shuffled().slice(0..3).joinToString("")
            info.add(Person("사람 이름","010-$midNumber-$lastNumber"))
        }
        val container = findViewById<LinearLayoutCompat>(R.id.list)
        val myInflater = LayoutInflater.from(this)
        info.forEach {
            val myView = myInflater.inflate(R.layout.item_hw3, null)
            val hwImg = myView.findViewById<ImageView>(R.id.hw3Img)
            val hwName = myView.findViewById<TextView>(R.id.hw3Name)
            val hwNumber = myView.findViewById<TextView>(R.id.hw3Number)

            hwImg.setImageDrawable(resources.getDrawable(R.drawable.img, null))
            hwName.text = it.name
            hwNumber.text = it.phoneNumber

            myView.setOnClickListener {
                startActivity(
                    Intent(this@AndroidHW3, AndroidHW3Sub::class.java).apply {
                        putExtra("name", hwName.text)
                        putExtra("number", hwNumber.text)
                    }
                )
            }
            container.addView(myView)
        }
    }
}

class Person(val name: String, val phoneNumber: String)