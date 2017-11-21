package com.fofo.mobileday.spacejet

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.util.*

/**
 * Created by fofofofodev on 21/11/2017.
 */
class Boom(ctx: Context) {

    var x: Float
    var y: Float

    var bitmap: Bitmap
    var mutableBitmap : Bitmap

    init {

        bitmap = BitmapFactory.decodeResource(ctx.resources, R.drawable.boom)
        mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        x= -250.toFloat()
        y= (-250).toFloat()

    }
    fun getStarWidth(): Float {
        //Making the star width random so that
        //it will give a real look
        val minX = 1.0f
        val maxX = 4.0f
        val rand = Random()
        return rand.nextFloat() * (maxX - minX) + minX
    }

}