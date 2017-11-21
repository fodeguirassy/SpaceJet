package com.fofo.mobileday.spacejet

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.lang.Thread

class GameView(val ctx: Context,
               val screenX: Int,
               val screenY: Int) : SurfaceView(ctx), Runnable {

    var playing = false
    var gameThread: Thread? = null

    var player: Player
    var paint: Paint
    var canvas: Canvas? = null
    var surfaceHolder: SurfaceHolder

    var stars : MutableList<Star> = mutableListOf()
    var ennemies: MutableList<Ennemy> = mutableListOf()

    var boom: Boom

    init {
        player = Player(ctx, screenX, screenY)
        paint = Paint()
        surfaceHolder = holder

        stars.apply {
             for (i in 1..100){
                add(Star(screenX, screenY))
             }
        }

        ennemies.apply {
            for (i in 1..3){
                add(Ennemy(ctx, screenX,screenY))
            }
        }

        boom = Boom(ctx)
    }

    override fun run() {

        while (playing) {
            update()
            draw()
            control()
        }
    }

    fun update() {
       // player.update()
        stars.forEach {
            it.update(player.speed)
        }

        ennemies.forEach {
            it.update(player.speed)
            if(Rect.intersects(player.detectCollision, it.detectCollision)){
                boom.x = it.x
                boom.y = it.y
                it.x = -400.toFloat()
                //boom.reset()
            }
        }
       // boom.mutableBitmap.eraseColor(Color.TRANSPARENT)
    }

    private fun draw() {
        if (surfaceHolder.surface.isValid) {
            //locking the canvas
            canvas = surfaceHolder.lockCanvas()
            canvas!!.drawColor(Color.BLACK)

            paint.color = Color.WHITE


            //drawing player
            canvas!!.drawBitmap(
                    player.bitmap,
                    player.x,
                    player.y,
                    paint
            )

            stars.forEach {
                paint.strokeWidth = it.getStarWidth()
                canvas!!.drawPoint(it.x.toFloat(), it.y.toFloat(), paint)
            }

            ennemies.forEach {
                canvas!!.drawBitmap(
                        it.bitmap,
                        it.x,
                        it.y,
                        paint
                )
            }

            canvas!!.drawBitmap(
                    boom.mutableBitmap,
                    boom.x.toFloat(),
                    boom.y.toFloat(),
                    paint
            )

            player.update()
            surfaceHolder.unlockCanvasAndPost(canvas)

            //boom.mutableBitmap.eraseColor(Color.TRANSPARENT)
        }
    }

    private fun control() {
        gameThread.let {
            try {
               // java.lang.Thread.sleep(17)
            }catch (e: InterruptedException){

            }
        }
    }

    fun pause() {
        playing = false
        gameThread?.let {
            try {
                it.join()
            } catch (e: InterruptedException) {
                print("Error pause() " + e.toString())
            }
        }
    }

    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread!!.start()
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
       // Log.i("onTouch", event?.action.toString())
        event?.let {
            when (it.action) {
                MotionEvent.ACTION_UP -> {
                    player.stopBoosting()
                    invalidate()
                }

                MotionEvent.ACTION_DOWN -> {
                    player.startBoosting()
                    invalidate()
                }
                else -> {
                    return true
                }
            }
        }
        return true
    }

}