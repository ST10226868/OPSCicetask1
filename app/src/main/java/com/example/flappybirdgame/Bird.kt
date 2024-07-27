package com.example.flappybirdgame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View

class Bird(context: Context) : View(context) {
    private var birdBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bird_main)
    private var birdX: Float = 100f
    private var birdY: Float = 100f
    private var velocity: Float = 0f
    private val gravity: Float = 2f
    private val paint: Paint = Paint()

    init {
        birdBitmap = Bitmap.createScaledBitmap(birdBitmap, birdBitmap.width / 2, birdBitmap.height / 2, false)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        update()
        canvas.drawBitmap(birdBitmap, birdX, birdY, paint)
        invalidate()
    }

    private fun update() {
        birdY += velocity
        velocity += gravity
    }

    fun flap() {
        velocity = -30f
    }

    fun getRect(): android.graphics.Rect {
        return android.graphics.Rect(birdX.toInt(), birdY.toInt(), birdX.toInt() + birdBitmap.width, birdY.toInt() + birdBitmap.height)
    }
}
