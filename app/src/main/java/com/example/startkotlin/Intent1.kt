package com.example.startkotlin

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import kotlin.math.exp

class Intent1 : AppCompatActivity() {
    val INTENT_TWO: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intent1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val implicit_intent: TextView = findViewById(R.id.implicit_intent)
        implicit_intent.setOnClickListener {
            val intent: Intent = Intent(
                Intent.ACTION_PICK,
                )
            startActivity(intent)
        }
        val explicit_intent1: TextView = findViewById(R.id.explicit_intent1)
        explicit_intent1.setOnClickListener {
            val intent: Intent = Intent()
            val componentName: ComponentName = ComponentName(
                "com.example.startkotlin",
                "com.example.startkotlin.Intent2"
            )
            intent.component = componentName
            startActivity(intent)
        }

        val explicit_intent2: TextView = findViewById(R.id.explicit_intent2)
        explicit_intent2.setOnClickListener {
            val intent: Intent = Intent(this, Intent2::class.java)
            startActivity(intent)
        }

        val explicit_intent3: TextView = findViewById(R.id.explicit_intent3)
        explicit_intent3.setOnClickListener {
            val myIntent: Intent = Intent(this, Intent2::class.java)
            myIntent.putExtra("extra-data","data-one")
            startActivity(myIntent)
        }

        val explicit_intent4: TextView = findViewById(R.id.explicit_intent4)
        explicit_intent4.setOnClickListener {
            val myIntent: Intent = Intent(this, Intent2::class.java)
            startActivityForResult(myIntent,INTENT_TWO)
        }

        val activityResultLauncher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                // onActivityResult의 역할
                when (it.resultCode) {
                    RESULT_OK -> {
                        val res: String? = it.data?.extras?.getString("result")
                        res?.let {
                            Log.d("Intent1", res)
                        }
                    }
                }
            }

        (findViewById<TextView>(R.id.explicit_intent5)).apply {
            this.setOnClickListener {
                val myIntent: Intent = Intent(this@Intent1, Intent2::class.java)
                activityResultLauncher.launch(myIntent)
            }
        }

        (findViewById<TextView>(R.id.explicit_intent6)).apply {
            this.setOnClickListener {
                val myIntent: Intent = Intent(this@Intent1, Intent2::class.java)
                myIntent.action = Intent.ACTION_SEND
                myIntent.putExtra(Intent.EXTRA_STREAM,
                    Uri.parse("android.resource://" + packageName + "/drawable/img"))
                myIntent.setType("image/*")
                startActivity(myIntent)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            INTENT_TWO -> {
                when (resultCode) {
                    RESULT_OK -> {
                        val res: String? = data?.extras?.getString("result")
                        if (res != null) {
                            Log.d("Intent1", res)
                        }
                    }
                }
            }
        }
    }
}