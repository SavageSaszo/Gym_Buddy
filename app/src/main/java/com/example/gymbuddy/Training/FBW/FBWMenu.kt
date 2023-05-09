package com.example.gymbuddy.Training.FBW

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.gymbuddy.R

class FBWMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fbwmenu)

        val AButton = findViewById<Button>(R.id.AButton)

        AButton.setOnClickListener {
            val intent = Intent(this, FBW_A::class.java)
            startActivity(intent)
        }

        val BButton = findViewById<Button>(R.id.BButton)

        BButton.setOnClickListener {
            val intent = Intent(this, FBW_B::class.java)
            startActivity(intent)
        }
    }
}