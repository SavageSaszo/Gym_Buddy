package com.example.gymbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.gymbuddy.CalorieDiary.CalorieDiary
import com.example.gymbuddy.KcalBmi.KcalBmi
import com.example.gymbuddy.Training.TrainingMenu
import com.example.gymbuddy.UserProfile.UserDetailsActivity

class UserMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_menu)

        val userId = intent.getIntExtra("userId", -1)

        val userDetailsButton = findViewById<Button>(R.id.profilButton)

        userDetailsButton.setOnClickListener {
            val intent = Intent(this, UserDetailsActivity::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        val DiaryButton = findViewById<Button>(R.id.kalorieButton)

        DiaryButton.setOnClickListener {
            val intent = Intent(this, CalorieDiary::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        val KcalBmiButton = findViewById<Button>(R.id.kcalButton)

        KcalBmiButton.setOnClickListener {
            val intent = Intent(this, KcalBmi::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        val TreningButton = findViewById<Button>(R.id.treningButton)

        TreningButton.setOnClickListener {
            val intent = Intent(this, TrainingMenu::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

    }
}