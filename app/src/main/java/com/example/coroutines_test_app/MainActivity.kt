package com.example.coroutines_test_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var textUser:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        textUser = findViewById(R.id.textView)


        button.setOnClickListener {


        }
    }

    private fun test(){



    }
    suspend fun fetchUser():User{

      Log.i("lnbti","user fetching ....")
       delay(1000)
    }


}