package com.example.gymbuddy.KcalBmi


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer;
import com.example.gymbuddy.AppDatabase
import com.example.gymbuddy.R
import com.example.gymbuddy.UserDao


class KcalBmi : AppCompatActivity() {

    private lateinit var userDao: UserDao
    private lateinit var bmiTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kcal_bmi)

        val userId = intent.getIntExtra("userId", -1)

        userDao = AppDatabase.getDatabase(applicationContext).userDao()

        val userDetailsTextView = findViewById<TextView>(R.id.user_details_caloric_needs)
        val proteinTextView = findViewById<TextView>(R.id.protein_value)
        val carbsTextView = findViewById<TextView>(R.id.carbs_value)
        val fatsTextView = findViewById<TextView>(R.id.fat_value)
        bmiTextView = findViewById(R.id.bmi_value)

        val userLiveData = userDao.getUserById(userId)
        userLiveData.observe(this, Observer { user ->

            // Calculate BMR using Mifflin-St Jeor formula
            val bmr = calculateBmr(user.gender, user.age, user.height, user.weight)

            // Calculate TDEE
            val tdee = calculateTdee(bmr, user.level, user.goal)

            val withoutprotein = tdee-(user.weight*1.1)

            // Calculate macronutrient needs
            val protein = calculateProtein(user.weight)
            val carbs = calculateCarbs(withoutprotein)
            val fats = calculateFats(withoutprotein)

            // Calculate BMI
            val bmi = calculateBmi(user.height, user.weight)

            // Display results
            userDetailsTextView.text = "${tdee.toInt()} kcal"
            proteinTextView.text = "${protein.toInt()} g"
            carbsTextView.text = "${carbs.toInt()} g"
            fatsTextView.text = "${fats.toInt()} g"
            bmiTextView.text = "${"%.1f".format(bmi)} kg/m²"

            /*userDao.updateUserNutritionData(
                userName,
                tdee.toInt(),
                protein.toInt(),
                carbs.toInt(),
                fats.toInt()
            )*/
        })
    }

    fun calculateBmr(gender: String, age: Int, height: Int, weight: Int): Double {
        val s = if (gender == "male") 5 else -161
        return 10 * weight + 6.25 * height - 5 * age + s
    }

    fun calculateTdee(bmr: Double, level: String, goal: String): Double {
        val activityLevel = when (level) {
            "Brak aktywności fizycznej" -> 1.2
            "Niska aktywność fizyczna" -> 1.375
            "Umiarkowana aktywność fizyczna" -> 1.55
            "Wysoka aktywność fizyczna" -> 1.725
            else -> 1.9
        }

        val goalMultiplier = when (goal) {
            "Utrata wagi" -> 0.8
            "Wzrost masy" -> 1.2
            else -> 1.0
        }

        return bmr * activityLevel * goalMultiplier
    }
    fun calculateProtein(weight: Int): Double {
        return weight.toDouble()  * 1.1 // 1.1 g of protein per pound of body weight
    }

    fun calculateCarbs(withoutprotein: Double): Double {
        return withoutprotein * 0.50 / 4 // 50% of calories from carbs, 1 g of carbs = 4 kcal
    }

    fun calculateFats(withoutprotein: Double): Double {
        return withoutprotein * 0.30 / 9 // 30% of calories from fat, 1 g of fat = 9 kcal
    }

    fun calculateBmi(height: Int, weight: Int): Double {
        val heightInMeters = height.toDouble() / 100
        return weight.toDouble() / (heightInMeters * heightInMeters)
    }
}