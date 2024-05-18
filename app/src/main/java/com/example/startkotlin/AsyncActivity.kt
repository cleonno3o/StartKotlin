package com.example.startkotlin

import android.os.AsyncTask
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AsyncActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_async)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        val backgroundAsyncTask = BackgroundAsyncTask(
//            findViewById(R.id.progressBar),
//            findViewById(R.id.progressBarText)
//        )
//        findViewById<TextView>(R.id.start).setOnClickListener {
//            backgroundAsyncTask.execute()
//        }
//        findViewById<TextView>(R.id.stop).setOnClickListener {
//            backgroundAsyncTask.cancel(true)
//        }
    }
}

//class BackgroundAsyncTask(
//    val progressBar: ProgressBar,
//    val progressText: TextView
//) : AsyncTask<Int, Int, Int>() {
    /* 순서대로 params, progress, result
    *  params -> doInBackground에서 사용할 타입
    *  progress -> onProgressUpdate에서 사용할 타입
    *  result -> onPostExecute에서 사용할 타입
    * */

//    var percent: Int = 0
//    override fun doInBackground(vararg params: Int?): Int {
//        while (!isCancelled()) {
//            percent++
//            if (percent > 100) break
//            else {
//                publishProgress(percent)
//            }
//            Thread.sleep(50)
//        }
//        return percent
//    }
//
//    override fun onPreExecute() {
//        percent = 0
//        progressBar.setProgress(percent)
//    }
//
//    override fun onPostExecute(result: Int?) {
//        progressText.text = "작업이 완료되었습니다"
//    }
//
//    override fun onProgressUpdate(vararg values: Int?) {
//        progressBar.setProgress(values[0] ?: 0)
//        progressText.text = "퍼센트: ${values[0]}"
//    }
//
//    override fun onCancelled() {
//        percent = 0
//        progressBar.setProgress(percent)
//        progressText.text = "작업이 취소되었습니다"
//    }
//}