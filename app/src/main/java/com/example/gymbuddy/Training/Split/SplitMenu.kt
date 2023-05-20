package com.example.gymbuddy.Training.Split

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.gymbuddy.R

class SplitMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_split_menu)

        val bb = findViewById<Button>(R.id.backandbiceps)

        bb.setOnClickListener {
            val intent = Intent(this, BackAndBiceps::class.java)
            startActivity(intent)
        }

        val bs = findViewById<Button>(R.id.bellyandshoulders)

        bs.setOnClickListener {
            val intent = Intent(this, BellyAndShoulders::class.java)
            startActivity(intent)
        }

        val ct = findViewById<Button>(R.id.chestandtriceps)

        ct.setOnClickListener {
            val intent = Intent(this, ChestAndTriceps::class.java)
            startActivity(intent)
        }

        val legs = findViewById<Button>(R.id.legs)

        legs.setOnClickListener {
            val intent = Intent(this, Legs2::class.java)
            startActivity(intent)
        }

    }
}