package com.example.game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Last : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var usernameTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last)

        usernameTextView = findViewById(R.id.usernameTextView)

        val highScoreTextView: TextView = findViewById(R.id.highScoreTextView)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Retrieve the high score
        val highScore = sharedPreferences.getInt("highScore", 0)
        val newScore = sharedPreferences.getInt("newScore", 0)

        // Display the high score in the TextView
        highScoreTextView.text = "High Score: $highScore"


        val savedUsername = sharedPreferences.getString("username", "")
        usernameTextView.text = "$savedUsername Your Score $newScore"

        val restart = findViewById<Button>(R.id.restart)
        restart.setOnClickListener(){

            val intent = Intent(this, Play::class.java)
            startActivity(intent)
        }


        var url ="https://www.google.com/"

        val intend =findViewById<Button>(R.id.intend)

        intend.setOnClickListener{
            val intend = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intend)

        }

        val challenge = findViewById<Button>(R.id.challeage)
        challenge.setOnClickListener(){

            val intent = Intent(this, Challenge::class.java)
            startActivity(intent)
        }


    }
}