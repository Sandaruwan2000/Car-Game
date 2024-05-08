package com.example.game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.ViewModel

class GameView(var c:Context ,var GameTask :GameTask ):View(c)
{
    private var myPaint:Paint? =null
    private var speed = 0
    private var time = 0
    private var score = 0
    private var myCarPosition = 0
    private val othercars = ArrayList<HashMap<String,Any>>()


    var viewWidth = 0
    var viewHeight = 0
    init{
        myPaint = Paint()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w
        viewHeight = h
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        viewWidth = this.measuredWidth
        viewHeight =this.measuredHeight

        // Add new cars periodically based on time and speed


        if(time % 700 <10 +speed){
            val map = HashMap<String,Any>()
            map["lane"] = (0..2).random()
            map["startTime"] = time
            othercars.add(map)
        }

        // Update time and calculate car dimensions

        time = time + 10 +speed
        val carWidth = viewWidth/5
        val carHeight = carWidth + 10
        myPaint!!.style= Paint.Style.FILL

        // Draw the player's car (blue car)

        val d = resources.getDrawable(R.drawable.bluecar,null)

        d.setBounds(
            myCarPosition * viewHeight / 3 + viewWidth / 15 + 25,
            viewHeight-2 - carHeight,
            myCarPosition * viewWidth / 3 + viewWidth / 15 + carWidth - 25,
            viewHeight - 2
        )

        d.draw(canvas!!)
        myPaint!!.color = Color.GREEN
        var highScore = 0

        // Draw other cars, check for collisions, and update score and speed

        for (i in othercars.indices){
            try{
                val carX = othercars[i]["lane"] as Int * viewWidth / 2 + viewWidth / 15
                var carY = time - othercars[i]["startTime"] as Int
                val d2 = resources.getDrawable(R.drawable.car,null)

                d2.setBounds(
                    carX + 25 , carY - carHeight , carX + carWidth - 25 , carY
                )
                d2.draw(canvas)
                if (othercars[i]["lane"] as Int == myCarPosition){
                    if (carY > viewHeight - 2 - carHeight
                        && carY < viewHeight - 2){

                        GameTask.closeGame(score)
                    }
                }
                if(carY > viewHeight + carHeight)
                {
                    //remove car abd update scores
                    othercars.removeAt(i)
                    score++
                    speed = 1 + Math.abs(score/ 8)
                    if(score > highScore){
                        highScore = score
                    }
                }
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }

        // Draw score and speed on the canvas

        myPaint!!.color = Color.WHITE
        myPaint!!.textSize = 40f
        canvas.drawText("Score : $score",80f,80f,myPaint!!)
        canvas.drawText("Speed : $speed",380f,80f,myPaint!!)
        invalidate()

    }

    // Handle touch events for controlling the player's car

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event!!.action){
            MotionEvent.ACTION_DOWN ->{
                val x1 = event.x
                if(x1 < viewWidth/2){
                    if(myCarPosition>0){
                        myCarPosition--
                    }

                }
                if(x1 > viewWidth / 2){
                    if(myCarPosition<2){
                        myCarPosition++
                    }
                }
                invalidate()
            }
            MotionEvent.ACTION_UP->{

            }
        }
        return true
    }
}