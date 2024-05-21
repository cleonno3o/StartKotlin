package com.example.startkotlin

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PermissionActivity : AppCompatActivity() {
    private val TAG = "PermissionActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        findViewById<TextView>(R.id.requestPermission).setOnClickListener {
            val cameraPermission = checkSelfPermission(
                android.Manifest.permission.CAMERA
            )
            // 권한이 없으면
            if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    // 여러개의 권한을 배열로 한번에 요청가능, 각각의 인덱스에 결과값이 담김
                    arrayOf(android.Manifest.permission.CAMERA),
                    // 요청을 구분할 requestCode 등록 (임의 설정)
                    1000
                )
            } else {
                Toast.makeText(this, "이미 권한이 있습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // 위에서 요청한 것에 대한 응답이면
        if (requestCode == 1000) {
            // 0에 카메라에 대한 권한 결과가 담겨 있음
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "승인 했습니다", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, "거절 했습니다", Toast.LENGTH_SHORT).show()
        }
    }
}