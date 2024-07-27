package com.example.flappybirdgame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val gameSurface: GameSurface = findViewById(R.id.game_surface)
        gameSurface.setOnTouchListener { _, _ ->
            gameSurface.bird.flap()
            true
        }
    }
}
