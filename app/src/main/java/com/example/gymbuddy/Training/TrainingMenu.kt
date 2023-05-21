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
import com.example.gymbuddy.Training.Split.SplitMenu

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

        val split = findViewById<Button>(R.id.button3)

        split.setOnClickListener {
            val intent = Intent(this, SplitMenu::class.java)
            startActivity(intent)
        }

        val exercise1InfoButton = findViewById<ImageButton>(R.id.imageButton1)
        exercise1InfoButton.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("Trening FBW (Full Body Workout) to trening siłowy, który" +
                    " skupia się na ćwiczeniu całego ciała w jednym treningu. Trening FBW jest" +
                    " idealny dla początkujących i osób, które chcą osiągnąć ogólną kondycję i" +
                    " zwiększyć siłę mięśniową. Polega na wykonywaniu ćwiczeń obejmujących " +
                    "wszystkie główne grupy mięśniowe, takie jak klatka piersiowa, plecy, " +
                    "nogi, ramiona i brzuch.\n" +
                    "\n" +
                    "Trening FBW zazwyczaj wykonywany jest 2-3 razy w tygodniu, z dniem" +
                    " odpoczynku między sesjami treningowymi, aby dać mięśniom czas na" +
                    " regenerację. Na każdym treningu należy wykonać około 6-8 ćwiczeń," +
                    " wykonując po 2-3 serie każdego z nich. W celu osiągnięcia najlepszych" +
                    " wyników z treningu FBW, ważne jest, aby stosować ciężary i ilość" +
                    " powtórzeń odpowiednie dla Twojego poziomu zaawansowania i celów" +
                    " treningowych.")
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }

            val alert = dialogBuilder.create()
            alert.setTitle("Opis planu treningowego")
            alert.show()
        }

        val exercise2InfoButton = findViewById<ImageButton>(R.id.imageButton2)
        exercise2InfoButton.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("Trening Push Pull Legs (PPL) jest odpowiedni dla osób" +
                    " średniozaawansowanych i polega na podziale treningu na trzy kategorie:" +
                    " pchnięcie (push), ciągnięcie (pull) i nogi (legs). Wykonuje się go" +
                    " zazwyczaj około 3 razy w tygodniu.\n" +
                    "\n" +
                    "W treningu PPL skoncentrowane są różne grupy mięśniowe. Trening push" +
                    " obejmuje ćwiczenia związane z pchnięciem, takie jak wyciskanie sztangi" +
                    " na ławce. Trening pull skupia się na ćwiczeniach związanych z ciągnięciem," +
                    " na przykład podciąganie na drążku. Trening legs skierowany jest na" +
                    " ćwiczenia dla nóg, jak przysiady ze sztangą.\n" +
                    "\n" +
                    "Nazwa treningu \"Push Pull Legs\" (Pchnięcie Ciągnięcie Nogi) odnosi" +
                    " się do koncepcji grupowania ćwiczeń ze względu na ruchy, jakie" +
                    " wykonujemy podczas treningu. W treningu push wykonujemy ruchy" +
                    " pchnięcia, w treningu pull wykonujemy ruchy ciągnięcia, a w treningu" +
                    " legs skupiamy się na ćwiczeniach dla nóg.")
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }

            val alert = dialogBuilder.create()
            alert.setTitle("Opis planu treningowego")
            alert.show()
        }

        val exercise3InfoButton = findViewById<ImageButton>(R.id.imageButton3)
        exercise3InfoButton.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("Trening split dla zaawansowanych, składający się z czterech" +
                    " dni, obejmuje treningi back & biceps (plecy i bicepsy), belly & shoulders" +
                    " (brzuch i barki), chest & triceps (klatka piersiowa i tricepsy) oraz legs" +
                    " (nogi).\n" +
                    "\n" +
                    "W treningu back & biceps skupiasz się na wzmocnieniu pleców i bicepsów." +
                    " Możesz wykonywać ćwiczenia takie jak podciąganie na drążku nachwytem," +
                    " wiosłowanie sztangą oraz uginanie ramion ze sztangą.\n" +
                    "\n" +
                    "Trening belly & shoulders koncentruje się na wzmacnianiu brzucha i barków." +
                    " Wykonujesz różnorodne ćwiczenia, takie jak plank, wznosy ramion bokiem z" +
                    " hantlami oraz unoszenie nóg w zwisie.\n" +
                    "\n" +
                    "Trening chest & triceps skierowany jest na rozwój klatki piersiowej i" +
                    " tricepsów. Możesz wykonywać wyciskanie sztangi na ławce płaskiej," +
                    " pompki na poręczach oraz wyciskanie francuskie.\n" +
                    "\n" +
                    "Trening legs koncentruje się na treningu nóg. Włączasz przysiady ze" +
                    " sztangą, wykroki bułgarskie, hip thrust oraz wspięcia na palce stojąc.\n" +
                    "\n" +
                    "Podsumowując, czterodniowy trening split dla zaawansowanych składa się" +
                    " z treningów back & biceps, belly & shoulders, chest & triceps oraz legs." +
                    " Każdy dzień treningowy skupia się na innych grupach mięśniowych, umożliwiając" +
                    " intensywny trening i rozwój siły oraz masy mięśniowej.")
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }

            val alert = dialogBuilder.create()
            alert.setTitle("Opis planu treningowego")
            alert.show()
        }
    }
}