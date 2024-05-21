package com.example.startkotlin

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        MyNetworkAsyncTask().execute()
    }
}

class MyNetworkAsyncTask() : AsyncTask<Any?, Any?, Any?>() {
    val TAG = "NetworkActivity"
    override fun doInBackground(vararg params: Any?): Any? {
        //  Request 하기
        val urlString: String = "http://mellowcode.org/json/students/"
        // 문자열을 URL로 변환
        val url: URL = URL(urlString)
        // connection 형성
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        // request method 선택
        connection.requestMethod = "GET"
        // 헤더부분 데이터 작성
        connection.setRequestProperty("Content-Type", "application/json")

        // Response 받기
        var buf = ""
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            // 저장된 데이터를 한번에 반환
            val reader = BufferedReader(
                // connection 내부의 스트림 데이터를 UTF-8로 변환
                InputStreamReader(
                    connection.inputStream,
                    "UTF-8"
                )
            )
            buf = reader.readLine()
            Log.d(TAG, buf)
        }
        return null
    }
}
