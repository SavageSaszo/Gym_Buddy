package com.example.gymbuddy.Training

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import com.example.gymbuddy.R
import com.example.gymbuddy.Training.FBW.FBWMenu
import com.example.gymbuddy.Training.PushPullLegs.PushPullLegsMenu

class TrainingMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_menu)

        val FBW = findViewById<Button>(R.id.button1)

        FBW.setOnClickListener {
            val intent = Intent(this, FBWMenu::class.java)
            startActivity(intent)
        }

        val PushPullLegs = findViewById<Button>(R.id.button2)

        PushPullLegs.setOnClickListener {
            val intent = Intent(this, PushPullLegsMenu::class.java)
            startActivity(intent)
        }

        val exercise1InfoButton = findViewById<ImageButton>(R.id.imageButton1)
        exercise1InfoButton.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("Trening FBW (Full Body Workout) to trening siłowy, który skupia się na ćwiczeniu całego ciała w jednym treningu. Trening FBW jest idealny dla początkujących i osób, które chcą osiągnąć ogólną kondycję i zwiększyć siłę mięśniową. Polega na wykonywaniu ćwiczeń obejmujących wszystkie główne grupy mięśniowe, takie jak klatka piersiowa, plecy, nogi, ramiona i brzuch.\n" +
                    "\n" +
                    "Trening FBW zazwyczaj wykonywany jest 2-3 razy w tygodniu, z dniem odpoczynku między sesjami treningowymi, aby dać mięśniom czas na regenerację. Na każdym treningu należy wykonać około 6-8 ćwiczeń, wykonując po 2-3 serie każdego z nich. W celu osiągnięcia najlepszych wyników z treningu FBW, ważne jest, aby stosować ciężary i ilość powtórzeń odpowiednie dla Twojego poziomu zaawansowania i celów treningowych.")
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }

            val alert = dialogBuilder.create()
            alert.setTitle("Opis planu treningowego")
            alert.show()
        }
    }
}