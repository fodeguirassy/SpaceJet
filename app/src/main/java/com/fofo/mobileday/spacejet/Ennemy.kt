package com.fofo.mobileday.spacejet

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import java.util.*

/**
 * Created by fofofofodev on 21/11/2017.
 */
class Ennemy(ctx: Context, screenX: Int, screenY: Int) {

    var bitmap : Bitmap

    var x : Float
    var y : Float

    var maxX:  Int
    var maxY: Int

    var minY: Int
    var minX: Int

    var speed: Int
    var randomGenerator: Random

    var detectCollision: Rect

    init {
        randomGenerator = Random()

        bitmap = BitmapFactory.decodeResource(ctx.resources, R.drawable.enemy)

        maxX = screenX
        maxY = screenY

        minX = 0
        minY = 0

        x = screenX.toFloat()
        y = (randomGenerator.nextInt(maxY) - bitmap.height).toFloat()

        speed = randomGenerator.nextInt(6) + 10

        detectCollision = Rect(x.toInt(),y.toInt(), bitmap.width, bitmap.height)

    }

    fun update(playerSpeed: Int){
        x -= playerSpeed
        x -= speed

        if(x < minX - bitmap.height){

            speed = randomGenerator.nextInt(10) + 10

            x = maxX.toFloat()
            y = (randomGenerator.nextInt(maxY) - bitmap.height).toFloat()

        }

        detectCollision.left = x.toInt()
        detectCollision.top = y.toInt()
        detectCollision.right = x.toInt() + bitmap.width
        detectCollision.bottom = y.toInt() + bitmap.height

    }

}