package com.example.game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Challenge : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var usernameText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge)



        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        usernameText = findViewById(R.id.usernameText)

        var i = 20 // Set i to 20 initially
        var level = 1
        var up=0
        val savedUsername = sharedPreferences.getString("username", "")
        val newScore = sharedPreferences.getInt("newScore", 0)

        val task = if (newScore >= i) {
            i += 20

            level += 1
            "Task Completed"
        } else {
            // Handle the case when the task is incomplete
            "Task Incomplete"

        }

        val message = "$savedUsername Your Level ${level-1}  $task" // Message to display

        usernameText.text = message

        // Save the updated i and level to SharedPreferences
        with(sharedPreferences.edit()) {
            putInt("i", i)
            putInt("level", level)
            putString("completedMessage", message)
            apply()
        }


        val back = findViewById<Button>(R.id.back)
        back.setOnClickListener(){

            val intent = Intent(this, Last::class.java)
            startActivity(intent)
        }

    }
}