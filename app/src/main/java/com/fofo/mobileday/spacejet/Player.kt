package com.fofo.mobileday.spacejet

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class Player(var ctx: Context,
             val screenX: Int,
             val screenY: Int) {

    var bitmap: Bitmap
    var x: Float
    var y: Float
    var speed: Int


    //part2
    var boosting: Boolean
    var gravity = -10;

    val MIN_SPEED = 1
    val MAX_SPEED = 20

    var minY: Float
    var maxY: Float

    var detectCollision: Rect

    init {
        bitmap = BitmapFactory.decodeResource(ctx.resources,
                R.drawable.player)
        x = 0F
        y = 0F
        speed = 1

        //part2
        boosting = false

        //top edge's y point is 0 so min y will always be zero
        maxY = (screenY - bitmap.height).toFloat()
        minY = 0F

        detectCollision = Rect(x.toInt(),y.toInt(),bitmap.width,bitmap.height)
    }


    fun update() {
        x += 1F
        //Part 2
        if (boosting) {
            speed += 2
           // x += 2
           // y += 10F
        } else {
            //x-= 5
             //y-=10
            speed -= 5
        }

        if (speed > MAX_SPEED) speed = MAX_SPEED

        if (speed < MIN_SPEED) speed = MIN_SPEED

        //moving the ship down
        y -= speed + gravity

        //control ship movements inside screen
        if (y < minY) y = minY
        if (y > maxY ) y = maxY


        detectCollision.left = x.toInt()
        detectCollision.top = y.toInt()
        detectCollision.right = x.toInt() + bitmap.width
        detectCollision.bottom = y.toInt() + bitmap.height
    }

    fun startBoosting() {
        boosting = true
    }

    fun stopBoosting() {
        boosting = false
    }
}