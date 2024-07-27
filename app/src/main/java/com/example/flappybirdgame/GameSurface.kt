package com.example.flappybirdgame

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameSurface(context: Context, attrs: AttributeSet) : SurfaceView(context, attrs), SurfaceHolder.Callback {
    private lateinit var thread: GameThread
    val bird = Bird(context)
    private val towers = mutableListOf<Tower>()
    private var screenWidth: Int = 0
    private var screenHeight: Int = 0

    init {
        holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        screenWidth = width
        screenHeight = height

        for (i in 0 until 3) {
            towers.add(Tower(context))
        }

        thread = GameThread(holder, bird, towers, screenWidth, screenHeight)
        thread.setRunning(true)
        thread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        thread.setRunning(false)
        while (retry) {
            try {
                thread.join()
                retry = false
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}

class GameThread(
    private val surfaceHolder: SurfaceHolder,
    private val bird: Bird,
    private val towers: List<Tower>,
    private val screenWidth: Int,
    private val screenHeight: Int
) : Thread() {
    private var running: Boolean = false

    fun setRunning(isRunning: Boolean) {
        running = isRunning
    }

    override fun run() {
        while (running) {
            val canvas: Canvas? = surfaceHolder.lockCanvas()
            if (canvas != null) {
                synchronized(surfaceHolder) {
                    canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR)
                    bird.draw(canvas)
                    for (tower in towers) {
                        tower.update(screenWidth, screenHeight)
                        tower.draw(canvas)
                        if (android.graphics.Rect.intersects(bird.getRect(), tower.getRect())) {
                            running = false
                        }
                    }
                }
                surfaceHolder.unlockCanvasAndPost(canvas)
            }
        }
    }
}
