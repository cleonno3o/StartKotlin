package com.example.startkotlin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitActivity : AppCompatActivity() {
    private val TAG = "RetrofitActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val myRetrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            // Serialized 된 데이터를 JSON, 그리고 원하는 객체로 변환하는데 사용할 것 설정
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = myRetrofit.create(MyRetrofitService::class.java)
        retrofitService.getStudentList().enqueue(object : Callback<ArrayList<StudentFromServer>> {
            override fun onResponse(
                call: Call<ArrayList<StudentFromServer>>,
                response: Response<ArrayList<StudentFromServer>>
            ) {
                if (response.isSuccessful) {
                    val studentList = response.body()
                    studentList?.forEach {
                        Log.d(TAG, it.name)
                    }
                    findViewById<RecyclerView>(R.id.studentsRecyclerView).apply {
                        adapter = StudentListRecyclerViewAdapter(studentList!!, layoutInflater)
                        layoutManager = LinearLayoutManager(this@RetrofitActivity)
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<StudentFromServer>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        findViewById<TextView>(R.id.createStudent).setOnClickListener {
            val student = HashMap<String, Any>()
            student.put("name", "랄로")
            student.put("age", 30)
            student.put("intro", "2400! 2400!")
            retrofitService.createStudent(student).enqueue(object : Callback<StudentFromServer> {
                override fun onResponse(
                    call: Call<StudentFromServer>,
                    response: Response<StudentFromServer>
                ) {
                    if (response.isSuccessful) {
                        // body에 내용이 담겨있음
                        val student = response.body()
                        Log.d(TAG, "등록한 학생: ${student!!.name}")
                    }
                }

                override fun onFailure(call: Call<StudentFromServer>, t: Throwable) {
                    Log.d(TAG, "실패...")
                }
            })
        }

        findViewById<TextView>(R.id.easyCreateStudent).setOnClickListener {
            val newStudent = StudentFromServer("울산", 100, "The Rising City")
            retrofitService.easyCreateStudent(newStudent).enqueue(object : Callback<StudentFromServer> {
                override fun onResponse(
                    call: Call<StudentFromServer>,
                    response: Response<StudentFromServer>
                ) {
                    val student = response.body()
                    Log.d(TAG, "등록한 학생: ${student!!.name}")
                }

                override fun onFailure(call: Call<StudentFromServer>, t: Throwable) {
                    Log.d(TAG, "실패...")
                }
            })
        }
    }
}

class StudentListRecyclerViewAdapter(
    var studentList: ArrayList<StudentFromServer>,
    var inflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentName: TextView
        val studentAge: TextView
        val studentIntro: TextView

        init {
            studentName = itemView.findViewById(R.id.studentName)
            studentAge = itemView.findViewById(R.id.studentAge)
            studentIntro = itemView.findViewById(R.id.studentIntro)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mView = inflater.inflate(R.layout.item_student, parent, false)
        return MyViewHolder(mView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as MyViewHolder
        holder.studentName.text = studentList[position].name
        holder.studentAge.text = studentList[position].age.toString()
        holder.studentIntro.text = studentList[position].intro
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}