package com.fofo.mobileday.spacejet

import java.util.*


class Star(val screenX: Int, val screenY: Int) {

    var x: Int
    var y: Int
    var speed: Int

    var maxX: Int
    var maxY: Int
    var minX: Int
    var minY: Int

    init {

        maxX = screenX
        maxY = screenY
        minX = 0
        minY = 0

        val randomGenerator = Random()
        speed = randomGenerator.nextInt(10)

        //generating a random coordinate
        //but keeping the coordinate inside the screen size
        x = randomGenerator.nextInt(maxX)
        y = randomGenerator.nextInt(maxY)

    }

    fun update(playerSpeed: Int) {

        //animating the star horizontally left side
        //by decreasing x coordinate with player speed
        x -= playerSpeed
        x -= speed

        //if the star reached the left edge of the screen
        if (x < 0) {
            //again starting the star from right edge
            //this will give a infinite scrolling background effect
            x = maxX
            val generator = Random()
            y = generator.nextInt(maxY)
            speed = generator.nextInt(15)
        }
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