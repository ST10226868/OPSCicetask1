package com.example.flappybirdgame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import kotlin.random.Random

class Tower(context: Context) {
    private var towerBitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.tower)
    private val paint: Paint = Paint()
    var x: Float = 0f
    var y: Float = 0f
    private val speed: Float = 10f
    private var screenWidth: Int = 0
    private var screenHeight: Int = 0

    init {
        towerBitmap = Bitmap.createScaledBitmap(towerBitmap, towerBitmap.width / 2, towerBitmap.height, false)
    }

    fun update(screenWidth: Int, screenHeight: Int) {
        this.screenWidth = screenWidth
        this.screenHeight = screenHeight

        x -= speed
        if (x < -towerBitmap.width) {
            x = screenWidth.toFloat()
            y = Random.nextInt(screenHeight / 4, screenHeight / 2).toFloat()
        }
    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(towerBitmap, x, y, paint)
    }

    fun getRect(): android.graphics.Rect {
        return android.graphics.Rect(x.toInt(), y.toInt(), x.toInt() + towerBitmap.width, y.toInt() + towerBitmap.height)
    }
}
