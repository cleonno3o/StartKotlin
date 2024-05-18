package com.example.startkotlin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val carList = mutableListOf<Car>()
        for (i in 1..100) {
            carList.add(Car("${i}번째 자동차","${i}번째 엔진"))
        }
        // Adapter 준비: 부착할 아이템들 생성
        val adapter = MyListViewAdapter(
            carList,
            LayoutInflater.from(this),
            this
        )

        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val car: Car = adapter.carList[position]
            val nthCar = car.nthCar
            val nthEngine = car.nthEngine

            Toast.makeText(
                this, "$nthCar $nthEngine",
                Toast.LENGTH_SHORT
            ).show()
        }
        // 데이터 추가하기
        findViewById<TextView>(R.id.addCar).setOnClickListener {
            adapter.carList.add(
                Car("새로운 차","새로운 엔진")
            )
            adapter.notifyDataSetChanged()
        }
    }
}

class MyListViewAdapter(
    val carList: MutableList<Car>,
    val layoutInflater: LayoutInflater,
    val context: Context
) : BaseAdapter() {
    override fun getCount(): Int {
        // 전체 데이터의 개수를 반환
        return carList.size
    }

    override fun getItem(position: Int): Any {
        // 해당 순서의 데이터를 반환
        return carList[position]
    }

    override fun getItemId(position: Int): Long {
        // 잘 쓰지는 않음
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val myView: View
        val myHolder: MyViewHolder
        if (convertView == null) {
            myView = layoutInflater.inflate(R.layout.car_item, null)
            myHolder = MyViewHolder()

            myHolder.carImage = myView.findViewById<ImageView>(R.id.carImage)
            myHolder.nthCar = myView.findViewById<TextView>(R.id.nthCar)
            myHolder.nthEngine = myView.findViewById<TextView>(R.id.nthEngine)

            myView.tag = myHolder
        } else {
            // 재활용 가능
            myHolder = convertView.tag as MyViewHolder
            myView = convertView
        }
        myHolder.carImage?.setImageDrawable(
            context.resources.getDrawable(R.drawable.img, null)
        )
        val currCar = carList[position]
        myHolder.nthCar?.text = currCar.nthCar
        myHolder.nthEngine?.text = currCar.nthEngine

        return myView
    }
}

class MyViewHolder {
    // car_item.xml 과 구성이 동일
    var carImage: ImageView? = null
    var nthCar: TextView? = null
    var nthEngine: TextView? = null
}