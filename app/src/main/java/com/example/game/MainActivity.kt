package com.example.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val delayMillis: Long = 5000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Handler().postDelayed({

            startActivity(Intent(this@MainActivity,Signup::class.java))

            finish()
        }, delayMillis)



    }
}