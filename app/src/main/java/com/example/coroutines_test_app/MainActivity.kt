package com.example.coroutines_test_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var textUser:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        textUser = findViewById(R.id.textView)


        button.setOnClickListener {

test()
        }
    }

    private fun test(){
//launch call
        lifecycleScope.launch(Dispatchers.Main){

            Log.i("lnbti","Starting${Thread.currentThread().name}")
            val user = fetchUser()
            showUser(user)
        }


    }
    suspend fun fetchUser():User{
//make network call

        //return user
        return withContext(Dispatchers.IO){

            Log.i("lnbti","user fetching ....")
            delay(1000)
            Log.i("lnbti","user fetching.. completed")
            return@withContext User(1,"Sam")
        }

    }
    fun showUser(user:User){

        Log.i("lnbti","user data showing ui")
        textUser.text = "id : ${user.id}name : ${user.name}"
        Log.i("lnbti","user datashowing on ui .... complted")
    }


}