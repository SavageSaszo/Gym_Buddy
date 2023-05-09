package com.example.gymbuddy.UserProfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.LiveData
import com.example.gymbuddy.AppDatabase
import com.example.gymbuddy.R
import com.example.gymbuddy.User
import com.example.gymbuddy.UserDao

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var userDao: UserDao
    private lateinit var currentUser: LiveData<User>
    private lateinit var nameTextView: TextView
    private lateinit var genderTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var heightTextView: TextView
    private lateinit var weightTextView: TextView
    private lateinit var goalTextView: TextView
    private lateinit var levelTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        nameTextView = findViewById(R.id.user_details_name)
        genderTextView = findViewById(R.id.user_details_gender)
        ageTextView = findViewById(R.id.user_details_age)
        heightTextView = findViewById(R.id.user_details_height)
        weightTextView = findViewById(R.id.user_details_weight)
        goalTextView = findViewById(R.id.user_details_goal)
        levelTextView = findViewById(R.id.user_details_level)

        // Pobierz userId wybranego użytkownika z dodatkowych informacji
        val userId = intent.getIntExtra("userId", -1)

        // Pobierz instancję bazy danych i DAO
        val db = AppDatabase.getDatabase(this)
        userDao = db.userDao()

        // Pobierz dane użytkownika z bazy danych
        currentUser = userDao.getUserById(userId)

        // Dodaj obserwatora do LiveData<User>
        currentUser.observe(this) { user ->
            user?.let {
                // Wyświetl dane użytkownika na ekranie
                displayUserDetails(user)
            }
        }
    }

    private fun displayUserDetails(user: User) {
        // Wyświetl dane użytkownika na ekranie
        // np. ustaw tekst w polach TextView

        nameTextView.text = user.name
        genderTextView.text = user.gender
        ageTextView.text = user.age.toString()
        heightTextView.text = user.height.toString()
        weightTextView.text = user.weight.toString()
        goalTextView.text = user.goal
        levelTextView.text = user.level
    }
}