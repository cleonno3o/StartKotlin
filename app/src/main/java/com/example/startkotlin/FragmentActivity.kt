package com.example.startkotlin

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // FragmentManager를 통해 관리, 기존의 FragmentManager는 deprecated되어 support사용
        val fragmentManager = supportFragmentManager
        // Fragment 클래스 객체를 생성
        val fragment1 = Fragment1()
        // 누르면 Fragment를 attach할 TextView의 Listener정의
        (findViewById<TextView>(R.id.attach)).setOnClickListener {
            // Transaction 시작
            val transaction = fragmentManager.beginTransaction()
            val bundle = Bundle()
            bundle.putString("key", "hello")
            fragment1.arguments = bundle
            // this의 layout에 정의된 View중에서 Fragment의 RootView로 사용할 것과 연결할 Fragment 지정
            transaction.replace(R.id.rootview, fragment1, "fragment1_tag")
            // commit하여 transaction 완성, commit하지 않으면 시작도 하지 않음
            transaction.commit()
        }
        (findViewById<TextView>(R.id.detach)).setOnClickListener {
            val transaction = fragmentManager.beginTransaction()
            transaction.remove(fragment1)
            transaction.commit()
        }

        (findViewById<TextView>(R.id.access_fragment)).setOnClickListener {
            val fragment1 = fragmentManager.findFragmentByTag("fragment1_tag") as Fragment1
            fragment1.printTestLog()
        }
    }

    fun printTestLog() {
        Log.d("FragmentActivity","This is Test Log fn")
    }
}