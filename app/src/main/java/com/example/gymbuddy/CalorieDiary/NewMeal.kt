package com.example.gymbuddy.CalorieDiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.gymbuddy.AppDatabase
import com.example.gymbuddy.Meal
import com.example.gymbuddy.MealDao
import com.example.gymbuddy.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class NewMeal : AppCompatActivity() {

    private lateinit var mealDao: MealDao
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_meal)

        val userId = intent.getIntExtra("userId", -1)

        // Pobranie instancji bazy danych
        val db = AppDatabase.getDatabase(applicationContext)
        mealDao = db.mealDao()

        // Ustawienie akcji dla przycisku "Dodaj posiłek"
        val addButton = findViewById<Button>(R.id.add_user_submit_button)
        addButton.setOnClickListener { addMeal(userId) }
    }

    private fun addMeal(userId: Int) {
        // Pobranie wartości z pól formularza
        val name = findViewById<EditText>(R.id.meal_name).text.toString()
        val kcal = findViewById<EditText>(R.id.kcal).text.toString().toInt()
        val protein = findViewById<EditText>(R.id.protein).text.toString().toInt()
        val carbs = findViewById<EditText>(R.id.carbs).text.toString().toInt()
        val fat = findViewById<EditText>(R.id.fat).text.toString().toInt()
        val today = Date()
        val date = dateFormat.format(today)

        // Utworzenie obiektu Meal na podstawie wartości z formularza i argumentu userId
        val meal = Meal(
            userId = userId,
            name = name,
            kcal = kcal,
            protein = protein,
            carbs = carbs,
            fat = fat,
            date = date
        )

        // Utworzenie korutyny
        CoroutineScope(Dispatchers.IO).launch {
            // Wstawienie nowego rekordu do tabeli meals w bazie danych
            mealDao.insert(meal)

            // Przejście do wątku UI, aby wyświetlić komunikat Toast
            withContext(Dispatchers.Main) {
                // Wyświetlenie komunikatu Toast
                Toast.makeText(applicationContext, "Posiłek zapisany", Toast.LENGTH_SHORT).show()

                // Zakończenie aktywności
                finish()
            }
        }
    }
}