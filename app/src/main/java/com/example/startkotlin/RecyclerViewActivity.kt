package com.example.startkotlin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val carList = mutableListOf<Car>()
        for (i in 1..100) {
            carList.add(Car("${i} 번째 자동차", "${i} 번째 엔진"))
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = MyRecyclerViewAdapter(
            carList, LayoutInflater.from(this), this)
        // 배치 방법을 조정
        recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false
        )
    }
}
// outer class
class MyRecyclerViewAdapter(
    var carList: MutableList<Car>,
    var inflater: LayoutInflater,
    val context: Context
) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {
    // ListView에서는 View Holder를 Adapter외부에 만들었지만
    // RecyclerView는 암묵적으로 inner class로 만들어 사용
    // ListView에서 처럼 Holder를 그냥 만들어 쓰는 것이 아니라
    // 성능이 개선된 View Holder를 상속받아 구현
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // itemView의 자식 View를 구성
        val carImage: ImageView
        val nthCar: TextView
        val nthEngine: TextView
        init {
            carImage = itemView.findViewById(R.id.carImage)
            nthCar = itemView.findViewById(R.id.nthCar)
            nthEngine = itemView.findViewById(R.id.nthEngine)
            itemView.setOnClickListener {
                val position = adapterPosition
                val car = carList[position]
                Log.d("Recycler", car.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // itemView를 반환
        val myView = inflater.inflate(R.layout.car_item, parent, false)
        return MyViewHolder(myView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // itemView에 데이터를 연결
        holder.carImage.setImageDrawable(
            context.resources.getDrawable(R.drawable.img, null))
        holder.nthCar.text = carList[position].nthCar
        holder.nthEngine.text = carList[position].nthEngine
    }

    override fun getItemCount(): Int {
        return carList.size
    }
}