package com.example.gymbuddy.Training.PushPullLegs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.gymbuddy.R
import com.example.gymbuddy.Training.FBW.FBW_A

class PushPullLegsMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push_pull_legs_menu)

        val variableName = "Ten trening opiera się na podziale ćwiczeń na trzy kategorie: pchnięcie, ciągnięcie i trening nóg. W dniu treningu \"pchnięcia\" skupiasz się na ćwiczeniach, które angażują mięśnie zaangażowane w pchnięcia, takie jak klatka piersiowa, barki i triceps. W dniu treningu \"ciągnięcia\" skupiasz się na ćwiczeniach, które angażują mięśnie zaangażowane w ruchy ciągnięcia, takie jak plecy, bicepsy i mięśnie dwugłowe uda. W dniu treningu nóg koncentrujesz się na ćwiczeniach dla mięśni nóg, takich jak przysiady, wykroki i ćwiczenia na łydki. Cykl push-pull-legs może być powtarzany 2-3 razy w tygodniu."

        val myTextView = findViewById<TextView>(R.id.descriptionTextView)
        myTextView.text = variableName

        val push = findViewById<Button>(R.id.Push)

        push.setOnClickListener {
            val intent = Intent(this, Push::class.java)
            startActivity(intent)
        }

        val pull = findViewById<Button>(R.id.Pull)

        pull.setOnClickListener {
            val intent = Intent(this, Pull::class.java)
            startActivity(intent)
        }

        val legs = findViewById<Button>(R.id.Legs)

        legs.setOnClickListener {
            val intent = Intent(this, Legs::class.java)
            startActivity(intent)
        }
    }
}