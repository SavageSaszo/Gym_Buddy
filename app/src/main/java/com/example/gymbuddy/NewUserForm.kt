package com.example.gymbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewUserForm : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var ageEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var goalSpinner: Spinner
    private lateinit var levelSpinner: Spinner
    private lateinit var submitButton: Button

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user_form)

        // Inicjalizacja komponentów interfejsu użytkownika
        nameEditText = findViewById(R.id.name_edittext)
        genderRadioGroup = findViewById(R.id.gender_radiogroup)
        ageEditText = findViewById(R.id.age_edittext)
        heightEditText = findViewById(R.id.height_edittext)
        weightEditText = findViewById(R.id.weight_edittext)
        goalSpinner = findViewById(R.id.goal_spinner)
        levelSpinner = findViewById(R.id.level_spinner)
        submitButton = findViewById(R.id.add_user_submit_button)

        // Inicjalizacja bazy danych
        userDao = AppDatabase.getDatabase(this).userDao()

        // Obsługa kliknięcia przycisku dodawania użytkownika
        submitButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val gender = when (genderRadioGroup.checkedRadioButtonId) {
                R.id.male_radiobutton -> "M"
                R.id.female_radiobutton -> "K"
                else -> ""
            }
            val age = ageEditText.text.toString().toIntOrNull() ?: 0
            val height = heightEditText.text.toString().toIntOrNull() ?: 0
            val weight = weightEditText.text.toString().toIntOrNull() ?: 0
            val goal = goalSpinner.selectedItem.toString()
            val level = levelSpinner.selectedItem.toString()

            val user = User(name = name, gender = gender, age = age, height = height, weight = weight, goal = goal, level = level)
            GlobalScope.launch(Dispatchers.IO) {
                userDao.insert(user)
                withContext(Dispatchers.Main) {
                    finish()
                }
            }
        }
    }
}