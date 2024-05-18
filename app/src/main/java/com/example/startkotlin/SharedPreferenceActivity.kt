package com.example.startkotlin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SharedPreferenceActivity : AppCompatActivity() {
    private val TAG = "SharedPreferenceActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        // Create
        findViewById<TextView>(R.id.create).setOnClickListener {
            val sharedPreferences = getSharedPreferences("tableName", Context.MODE_PRIVATE)
                // MODE_PRIVATE: 현재 앱에서만 사용할 것, 다른앱에 공유하지 않음
            // Editor를 이용하여 조작
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("Key1","Hello!")
            editor.putString("Key2","Bye!")
            // commit을 수행해야 적용
            editor.commit()
        }

        // Read
        findViewById<TextView>(R.id.read).setOnClickListener {
            val sharedPreferences = getSharedPreferences("tableName", Context.MODE_PRIVATE)
            // defValue인자는 Key값이 틀렸을때 반환 값
            val valueOne = sharedPreferences.getString("Key","WrongKey1")
            val valueTwo = sharedPreferences.getString("Key2","WrongKey2")
            Log.d(TAG, valueOne!!)
            Log.d(TAG, valueTwo!!)
        }

        // Update
        findViewById<TextView>(R.id.update).setOnClickListener {
            val sharedPreferences = getSharedPreferences("tableName", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            // 새로 생성할때와 같은방법을 사용해 덮어씌우는 방법으로 Update
            editor.putString("Key2","ChangedValue")
            editor.commit()
        }

        // Delete
        findViewById<TextView>(R.id.delete).setOnClickListener {
            val sharedPreferences = getSharedPreferences("tableName", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            // 해당 테이블을 모두 삭제
            editor.clear()
            editor.commit()
        }
    }
}