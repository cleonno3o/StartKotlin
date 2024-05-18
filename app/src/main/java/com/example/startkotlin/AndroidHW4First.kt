package com.example.startkotlin

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AndroidHW4First : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_android_hw4_first)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val msgList = mutableListOf<Chat>()
        for (i in 1..20) {
            val flag = i % 2 == 0
            msgList.add(Chat("$i 번째 메시지 입니다.", flag))
        }
        recyclerView.adapter = MyRecyclerViewAdapter2(msgList, layoutInflater, this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}

class MyRecyclerViewAdapter2(
    val msgList: MutableList<Chat>,
    val inflater: LayoutInflater,
    val context: Context
) : RecyclerView.Adapter<MyRecyclerViewAdapter2.LeftViewHolder>() {
    inner class LeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImg: ImageView
        val msg: TextView

        init {
            profileImg = itemView.findViewById(R.id.profileImg)
            msg = itemView.findViewById(R.id.msg)
        }
    }
    inner class RightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImg: ImageView
        val msg: TextView

        init {
            profileImg = itemView.findViewById(R.id.profileImg)
            msg = itemView.findViewById(R.id.msg)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (msgList[position].isRight) 1 else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeftViewHolder {

        val myView =
            if (viewType == 1) inflater.inflate(R.layout.item_chat_right, parent, false)
            else inflater.inflate(R.layout.item_chat_left, parent, false)
        return LeftViewHolder(myView)
    }

    override fun onBindViewHolder(holder: LeftViewHolder, position: Int) {
        holder.profileImg.setImageDrawable(
            context.resources.getDrawable(R.drawable.img, context.theme)
        )
        holder.msg.text = msgList[position].msg
    }

    override fun getItemCount(): Int {
        return msgList.size
    }
}

class Chat(val msg:String, val isRight: Boolean)