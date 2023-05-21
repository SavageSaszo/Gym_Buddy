package com.example.gymbuddy.Training.PushPullLegs

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.gymbuddy.R

class Push : AppCompatActivity() {
    private lateinit var seekBar: SeekBar
    private lateinit var textView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var countDownTimer: CountDownTimer
    private var selectedMinutes: Int = 0
    private lateinit var mediaPlayer: MediaPlayer
    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push)

        seekBar = findViewById(R.id.seekBar)
        textView = findViewById(R.id.textView)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        textView.text = getString(R.string.minutes, seekBar.progress)
        selectedMinutes = seekBar.progress

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView.text = getString(R.string.minutes, progress)
                selectedMinutes = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        startButton.setOnClickListener {
            val minutes = selectedMinutes
            val milliseconds = minutes * 60000L
            val remainingTimeSeconds = minutes * 60L

            countDownTimer = object : CountDownTimer(milliseconds, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val secondsRemaining = millisUntilFinished / 1000
                    val minutesRemaining = secondsRemaining / 60
                    val secondsDisplay = secondsRemaining % 60
                    val countdownText = "$minutesRemaining:${String.format("%02d", secondsDisplay)}"
                    textView.text = countdownText
                }

                override fun onFinish() {
                    textView.text = getString(R.string.time_up)
                    playNotificationSound()
                }
            }

            countDownTimer.start()
            startButton.visibility = View.GONE
            stopButton.visibility = View.VISIBLE
        }

        stopButton.setOnClickListener {
            countDownTimer.cancel()
            textView.text = getString(R.string.minutes, selectedMinutes)
            startButton.visibility = View.VISIBLE
            stopButton.visibility = View.GONE
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.happy_wheels_finish_sf)

        val imageView1 = findViewById<ImageView>(R.id.imageView1)
        val imageResource1 = R.drawable.wyciskanie_lawka_plaska // Zasób obrazu
        val imageDescription1 = "Wyciskanie na ławce płaskiej leżąc to popularne ćwiczenie angażujące głównie mięśnie klatki piersiowej, tricepsów i mięśnie przedramion. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Połóż się na ławce płaskiej, tak aby plecy, pośladki i głowa były dobrze podparte. Stopy postaw na podłodze dla stabilności.\n" +
                "\n" +
                "Chwyć sztangę nachwytem (dłonie skierowane na zewnątrz) na szerokość nieco większą niż szerokość barków. Sztanga powinna być trzymana na wysokości klatki piersiowej.\n" +
                "\n" +
                "Zdejmij sztangę z uchwytu, wyciągając ją w górę i unieś ją nad klatkę piersiową, utrzymując ręce w linii prostej nad barkami.\n" +
                "\n" +
                "Zegnij łokcie i powoli opuść sztangę do dolnej pozycji, kontrolując ruch i zachowując stabilną postawę.\n" +
                "\n" +
                "Przy napięciu mięśni klatki piersiowej, wyciśnij sztangę do góry, prostując ręce i powracając do początkowej pozycji.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wyciskania na ławce płaskiej leżąc, pamiętaj o prawidłowej technice wykonania, utrzymaniu stabilnej postawy i kontrolowanym ruchu."
        val imageTitle1 = "Wyciskanie sztangi na ławce płaskiej"

        imageView1.setOnClickListener {
            showImageDialog(imageResource1, imageDescription1, imageTitle1)
        }

        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageResource2 = R.drawable.dipy // Zasób obrazu
        val imageDescription2 = "Pompki na poręczach to skuteczne ćwiczenie, które angażuje przede wszystkim mięśnie klatki piersiowej, tricepsów i mięśnie przedramion. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Znajdź dwie poręcze o odpowiedniej wysokości, na których będziesz wykonywać pompki. Upewnij się, że poręcze są stabilne i bezpieczne.\n" +
                "\n" +
                "Stań między poręczami, trzymając się za nimi chwytem nachwytem (dłonie skierowane na zewnątrz) na szerokość nieco większą niż szerokość barków.\n" +
                "\n" +
                "Unieś się na rękach, wyprostowanych ramionach i z nogami zgiętymi w kolanach, aby stworzyć pozycję wyjściową do wykonywania pompek.\n" +
                "\n" +
                "Zginij łokcie i powoli opuść się w dół, schodząc jak najniżej, aż ramiona będą ugięte pod kątem około 90 stopni.\n" +
                "\n" +
                "Następnie wypchnij się mocno, prostując ramiona i wracając do pozycji wyjściowej.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania pompki na poręczach, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i napięciu mięśni."
        val imageTitle2 = " Pompki na poręczach"

        imageView2.setOnClickListener {
            showImageDialog(imageResource2, imageDescription2, imageTitle2)
        }

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageResource3 = R.drawable.wyciskanie_zolnierskie // Zasób obrazu
        val imageDescription3 = "Wyciskanie żołnierskie, znane również jako wyciskanie nad głowę, to skuteczne ćwiczenie angażujące głównie mięśnie ramion, barków i klatki piersiowej. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Rozpocznij, stojąc prosto, nogi ustawione na szerokość barków. Chwyć sztangę nachwytem (dłonie skierowane na zewnątrz) na szerokość nieco większą niż szerokość barków. Sztanga powinna być uniesiona do poziomu klatki piersiowej.\n" +
                "\n" +
                "Przytrzymaj sztangę na wysokości klatki piersiowej, trzymając ją mocno i stabilnie. Napnij mięśnie brzucha i pleców, utrzymując prostą postawę.\n" +
                "\n" +
                "Wypchnij sztangę do góry, prostując ramiona i unosząc ją nad głowę. W trakcie unoszenia, utrzymuj kontrolowany ruch i skup się na skurczu mięśni ramion i barków.\n" +
                "\n" +
                "Zatrzymaj się na chwilę w górnym punkcie, a następnie powoli opuść sztangę z powrotem do pozycji wyjściowej na poziomie klatki piersiowej.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania wyciskania żołnierskiego, pamiętaj o prawidłowej technice wykonania, kontrolowanym ruchu i utrzymaniu stabilnej postawy. Skup się na skurczu mięśni ramion, barków i klatki piersiowej podczas unoszenia sztangi."
        val imageTitle3 = "Wyciskanie sztangi stojąc"

        imageView3.setOnClickListener {
            showImageDialog(imageResource3, imageDescription3, imageTitle3)
        }

        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageResource4 = R.drawable.wyciskanie_hantli_skos // Zasób obrazu
        val imageDescription4 = "Wyciskanie hantli na ławce skośnej to ćwiczenie skupione głównie na mięśniach klatki piersiowej, przednich aktonach mięśnia naramiennego, tricepsach oraz mięśniach stabilizujących rdzeń. Poniżej znajduje się opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Ustaw ławkę skośną na odpowiednim nachyleniu, z górnym końcem wyżej od dolnego końca. Połóż się na ławce, plecami do niej, tak aby stopy były stabilnie osadzone na podłodze.\n" +
                "\n" +
                "Chwyć hantle nachwytem (dłonie skierowane na zewnątrz) na wysokości klatki piersiowej. Ręce powinny być ustawione na szerokość nieco większą niż szerokość barków.\n" +
                "\n" +
                "Wypchnij hantle do góry, prostując ramiona i unosząc je nad głowę. Skoncentruj się na skurczu mięśni klatki piersiowej.\n" +
                "\n" +
                "Zatrzymaj się na chwilę w górnym punkcie, a następnie kontroluj ruch, opuszczając hantle powoli do poziomu klatki piersiowej.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wyciskania hantli na ławce skośnej, pamiętaj o zachowaniu prawidłowej techniki wykonania, utrzymaniu stabilnej postawy i kontrolowanym ruchu."
        val imageTitle4 = "Wyciskanie hantli na ławce skośnej"

        imageView4.setOnClickListener {
            showImageDialog(imageResource4, imageDescription4, imageTitle4)
        }

        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageResource5 = R.drawable.wycisaknie_francuskie // Zasób obrazu
        val imageDescription5 = "Wyciskanie francuskie, znane również jako pompki francuskie lub francuskie wyciskanie sztangi, to ćwiczenie skupione głównie na mięśniach tricepsów. Poniżej znajduje się opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Połóż się na ławce płaskiej lub skośnej, trzymając sztangę nachwytem (dłonie skierowane na zewnątrz) na szerokość nieco węższą niż szerokość barków. Unieś sztangę nad głowę, trzymając ją prostymi ramionami.\n" +
                "\n" +
                "Zegnij łokcie i powoli opuść sztangę za głowę, skręcając przedramiona. Kontroluj ruch i unikaj nadmiernego rozchylenia łokci.\n" +
                "\n" +
                "Zatrzymaj się na chwilę w dolnej pozycji, poczując napięcie w tricepsach.\n" +
                "\n" +
                "Następnie prostuj łokcie i unosząc sztangę z powrotem do pozycji wyjściowej nad głowę.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wyciskania francuskiego, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i napięciu mięśni tricepsów."
        val imageTitle5 = "Wyciskanie francuskie"

        imageView5.setOnClickListener {
            showImageDialog(imageResource5, imageDescription5, imageTitle5)
        }


    }

    private fun showImageDialog(imageResource: Int, imageDescription: String, imageTitle: String) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_layout, null)
        val imageView = dialogView.findViewById<ImageView>(R.id.dialogImageView)
        imageView.setImageResource(imageResource) // Ustawienie zasobu obrazu

        val scrollView = dialogView.findViewById<ScrollView>(R.id.dialogScrollView)
        val textView = scrollView.findViewById<TextView>(R.id.dialogTextView)
        textView.text = imageDescription // Ustawienie opisu zdjęcia

        val closeButton = dialogView.findViewById<Button>(R.id.dialogCloseButton)
        closeButton.setOnClickListener {
            dialog?.dismiss()
        }

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle(imageTitle) // Ustawienie tytułu zdjęcia

        dialog = dialogBuilder.create()
        dialog?.show()
    }

    private fun playNotificationSound() {
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}