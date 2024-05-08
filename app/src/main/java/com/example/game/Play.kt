package com.example.game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class Play : AppCompatActivity(),GameTask {

    lateinit var play : LinearLayout
    lateinit var start : Button
    lateinit var mGameView: GameView
    lateinit var score: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private var highScore = 0
    private var newScore = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        start = findViewById(R.id.start)
        play=findViewById(R.id.play)
        score = findViewById(R.id.score)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        highScore = sharedPreferences.getInt("highScore", 0)
        newScore = sharedPreferences.getInt("newScore", 0)

        mGameView = GameView(this,this)




        start.setOnClickListener{
            mGameView.setBackgroundResource(R.drawable.road11)
            play.addView(mGameView)
            start.visibility = View.GONE
            score.visibility = View.GONE

        }
    }

    override fun closeGame(mScore: Int) {
        score.text = "Score : $mScore"
        play.removeView(mGameView)
        start.visibility = View.VISIBLE
        score.visibility = View.VISIBLE

        if (mScore > highScore) {
            highScore = mScore
            saveHighScore(highScore)
        }

        newScore = mScore
        savenewScore(newScore)



        val intent = Intent(this, Last::class.java)
        startActivity(intent)
    }

    private fun saveHighScore(score: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt("highScore", score)
        editor.apply()
    }

    private fun savenewScore(score: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt("newScore", score)
        editor.apply()
    }


}