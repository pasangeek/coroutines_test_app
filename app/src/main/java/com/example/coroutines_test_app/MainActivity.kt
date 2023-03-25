package com.example.coroutines_test_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

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
//        fetchAndShowUser()
        fetchAndShowUser2()
    }

    /** launch */
    private fun fetchAndShowUser(){
        lifecycleScope.launch(Dispatchers.Main){
            Log.i("LNBTI", "Starting.. ${Thread.currentThread().name}")
            /*  val user = fetchUser()
              showUser(user)*/
            val user = async { fetchUser() }
            showUser(user.await())

        }
    }

    suspend fun fetchUser() : User{
        return withContext(Dispatchers.IO){
            // make network call
            // return user
            Log.i("LNBTI", "User fetching..")
            delay(2000)
            Log.i("LNBTI", "User fetching.. completed ${Thread.currentThread().name}")
            return@withContext User(1, "Sam" )
        }
    }

    fun showUser(user: User){
        // show user data on UI
        Log.i("LNBTI", "User data showing on UI")
        textUser.text = "id: ${user.id} name: ${user.name}"
        Log.i("LNBTI", "User data showing on UI.. completed")
    }

    //----------------
    /** async */
    private fun fetchAndShowUser2(){
        lifecycleScope.launch(Dispatchers.Main){
            val timeTaken = measureTimeMillis {
//                val user1 = longRunningTaskOne() // 2 seconds
//                val user2 = longRunningTaskTwo() // 4 seconds
//                showUserResults(user1, user2)

                val user1 = async { longRunningTaskOne() }// 2 seconds
                val user2 = async { longRunningTaskTwo() } // 4 seconds
                showUserResults(user1.await(), user2.await())
            }
            Log.i("LNBTI", "Time taken: $timeTaken")
        }
    }

    suspend fun longRunningTaskOne() : User{
        return withContext(Dispatchers.IO){
            // make a network call
            // return a User
            Log.i("LNBTI", "User fetching task 1..")
            delay(2000)
            Log.i("LNBTI", "User fetching task 1.. completed ${Thread.currentThread().name}")

            return@withContext User(1, "Ann")
        }

    }

    suspend fun longRunningTaskTwo() : User{
        return withContext(Dispatchers.IO){
            // make a network call
            // return a User
            Log.i("LNBTI", "User fetching task 2..")
            delay(4000)
            Log.i("LNBTI", "User fetching task 2.. completed ${Thread.currentThread().name}")

            return@withContext User(2, "John")
        }
    }

    fun showUserResults(vararg users: User){
        Log.i("LNBTI", "Users showing on UI..")
        var userData = ""
        for (user in users){
            userData += "id: ${user.id} name: ${user.name}\n"
        }
        textUser.text = userData
        Log.i("LNBTI", "Users showing on UI.. completed")

    }


}