package com.example.startkotlin

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class StudentFromServer(
    val id: Int, val name: String, val age: Int, val intro: String
) {
    constructor(name: String, age: Int, intro: String) : this(0, name, age, intro)
}
// 다양한 객체를 주고받게 되는데 그때마다 어떤 타입을 얻게되는지 정의
interface MyRetrofitService {
    @GET("json/students/")  // 해당 주소에 요청
    fun getStudentList() : Call<ArrayList<StudentFromServer>>

    @POST("json/students/")
    fun createStudent(
        // Body에 전달
        @Body params: HashMap<String, Any>
    ) : Call<StudentFromServer>

    @POST("json/students/")
    fun easyCreateStudent(
        @Body param: StudentFromServer
    ) : Call<StudentFromServer>
}