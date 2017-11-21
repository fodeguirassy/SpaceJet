package com.fofo.mobileday.spacejet

import android.content.pm.ActivityInfo
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        //setContentView(R.layout.activity_main)

        //Getting the screen resolution into point object
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        gameView = GameView(this, size.x, size.y)
        setContentView(gameView)
    }

    override fun onPause() {
        super.onPause()
        gameView.pause()
    }

    override fun onResume() {
        super.onResume()
        gameView.resume()
    }
}
