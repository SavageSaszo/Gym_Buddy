package com.example.gymbuddy.CalorieDiary

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.gymbuddy.AppDatabase
import com.example.gymbuddy.MealDao
import com.example.gymbuddy.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CalorieDiary : AppCompatActivity() {

    private lateinit var meal_listview: ListView
    private lateinit var mealDao: MealDao

    private lateinit var dateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calorie_diary)

        dateTextView = findViewById(R.id.date_textview)

        dateTextView.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)
                    val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val formattedDate = dateFormat.format(selectedDate.time)

                    dateTextView.text = formattedDate

                    val userId = intent.getIntExtra("userId", -1)

                    val db = AppDatabase.getDatabase(this)
                    mealDao = db.mealDao()

                    mealDao.getTotalCaloriesForDay(userId, formattedDate).observe(this, Observer { totalCalories ->
                        val totalCaloriesTextView = findViewById<TextView>(R.id.calories_label)
                        if (totalCalories != null) {
                            totalCaloriesTextView.text = "Kcal: $totalCalories"
                        } else {
                            totalCaloriesTextView.text = "Kcal: 0"
                        }
                    })

                    mealDao.getTotalProteinForDay(userId, formattedDate).observe(this, Observer { totalProteins ->
                        val totalProteinsTextView = findViewById<TextView>(R.id.protein_label)
                        if (totalProteins != null) {
                            totalProteinsTextView.text = "B: $totalProteins"
                        } else {
                            totalProteinsTextView.text = "B: 0"
                        }
                    })

                    mealDao.getTotalCarbsForDay(userId, formattedDate).observe(this, Observer { totalCarbs ->
                        val totalCarbsTextView = findViewById<TextView>(R.id.carbs_label)
                        if (totalCarbs != null) {
                            totalCarbsTextView.text = "W: $totalCarbs"
                        } else {
                            totalCarbsTextView.text = "W: 0"
                        }
                    })

                    mealDao.getTotalFatForDay(userId, formattedDate).observe(this, Observer { totalFat ->
                        val totalFatTextView = findViewById<TextView>(R.id.fat_label)
                        if (totalFat != null) {
                            totalFatTextView.text = "T: $totalFat"
                        } else {
                            totalFatTextView.text = "T: 0"
                        }
                    })

                    mealDao.getMealsByUserIdAndDate(userId, formattedDate).observe(this, Observer { meals ->
                        val mealInfo = mutableListOf<String>()
                        meals.forEach { meal ->
                            val name = meal.name
                            val kcal = meal.kcal.toString()
                            val protein = meal.protein.toString()
                            val carbs = meal.carbs.toString()
                            val fat = meal.fat.toString()
                            val info = "$name\nKcal: $kcal, B: $protein, W: $carbs, T: $fat"
                            mealInfo.add(info)
                        }
                        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mealInfo)
                        meal_listview.adapter = adapter
                    })
                },
                year,
                month,
                dayOfMonth
            )

            datePickerDialog.show()
        }

        val userId = intent.getIntExtra("userId", -1)

        val DiaryButton = findViewById<Button>(R.id.add_button)

        DiaryButton.setOnClickListener {
            val intent = Intent(this, NewMeal::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        val db = AppDatabase.getDatabase(this)
        mealDao = db.mealDao()

        meal_listview = findViewById(R.id.meal_listview)

        val currentDate = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = formatter.format(currentDate)

        dateTextView.text = formattedDate

        mealDao.getTotalCaloriesForDay(userId, formattedDate).observe(this, Observer { totalCalories ->
            val totalCaloriesTextView = findViewById<TextView>(R.id.calories_label)
            if (totalCalories != null) {
                totalCaloriesTextView.text = "Kcal: $totalCalories"
            } else {
                totalCaloriesTextView.text = "Kcal: 0"
            }
        })

        mealDao.getTotalProteinForDay(userId, formattedDate).observe(this, Observer { totalProteins ->
            val totalProteinsTextView = findViewById<TextView>(R.id.protein_label)
            if (totalProteins != null) {
                totalProteinsTextView.text = "B: $totalProteins"
            } else {
                totalProteinsTextView.text = "B: 0"
            }
        })

        mealDao.getTotalCarbsForDay(userId, formattedDate).observe(this, Observer { totalCarbs ->
            val totalCarbsTextView = findViewById<TextView>(R.id.carbs_label)
            if (totalCarbs != null) {
                totalCarbsTextView.text = "W: $totalCarbs"
            } else {
                totalCarbsTextView.text = "W: 0"
            }
        })

        mealDao.getTotalFatForDay(userId, formattedDate).observe(this, Observer { totalFat ->
            val totalFatTextView = findViewById<TextView>(R.id.fat_label)
            if (totalFat != null) {
                totalFatTextView.text = "T: $totalFat"
            } else {
                totalFatTextView.text = "T: 0"
            }
        })

        mealDao.getMealsByUserIdAndDate(userId, formattedDate).observe(this, Observer { meals ->
            val mealInfo = mutableListOf<String>()
            meals.forEach { meal ->
                val name = meal.name
                val kcal = meal.kcal.toString()
                val protein = meal.protein.toString()
                val carbs = meal.carbs.toString()
                val fat = meal.fat.toString()
                val info = "$name\nKcal: $kcal, B: $protein, W: $carbs, T: $fat"
                mealInfo.add(info)
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mealInfo)
            meal_listview.adapter = adapter
        })
    }
}