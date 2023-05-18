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

class Pull : AppCompatActivity() {
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
        setContentView(R.layout.activity_pull)

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
        val imageResource1 = R.drawable.martwy_ciag // Zasób obrazu
        val imageDescription1 = "Martwy ciąg to skuteczne ćwiczenie siłowe, które" +
                " angażuje głównie mięśnie pleców, pośladków, ud, a także mięśnie" +
                " nóg i przedramion. Postaw się prosto nad sztangą, stojąc na" +
                " szerokość barków. Stopy powinny być skierowane lekko na zewnątrz," +
                " a sztanga powinna być na wysokości połowy stóp. Schyl się w" +
                " biodrach, trzymając plecy proste i zginając kolana, aż będziesz" +
                " mógł sięgnąć sztangą. Chwyć sztangę nieco szerzej niż szerokość" +
                " barków, trzymając ją pewnie i stabilnie. Wypnij klatkę piersiową" +
                " do przodu i napnij mięśnie pleców. Wdechając powietrze, unieś" +
                " sztangę, prostując nogi i unosząc tułów, jednocześnie utrzymując" +
                " napięcie w mięśniach pleców i pośladków. W górnej pozycji" +
                " sztanga powinna znajdować się w pobliżu bioder, a ciało powinno" +
                " tworzyć prostą linie od głowy do pięt. Wydechając powietrze," +
                " opuść sztangę kontrolowanie, schylając się w biodrach i zginając" +
                " kolana. Powtórz ruch przez określoną liczbę powtórzeń, dbając" +
                " o prawidłową technikę i zachowując pełny zakres ruchu." +
                " Pamiętaj o utrzymaniu stabilnej pozycji ciała, unikaj szarpania" +
                " lub nadmiernego obciążenia kręgosłupa. Po zakończeniu serii" +
                " ćwiczenia, delikatnie opuść sztangę na ziemię. Wykonuj martwy" +
                " ciąg w kontrolowany sposób, skupiając się na prawidłowej" +
                " technice i pełnym zakresie ruchu. "
        val imageTitle1 = "Martwy ciąg"

        imageView1.setOnClickListener {
            showImageDialog(imageResource1, imageDescription1, imageTitle1)
        }

        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageResource2 = R.drawable.podciaganie_nachwytem // Zasób obrazu
        val imageDescription2 = "Podciąganie nachwytem wykonuj na drążku, trzymając go nachwytem (dłonie skierowane na zewnątrz). Stań pod drążkiem, trzymając go na szerokość barków. Nogi mogą być wyprostowane lub zgięte w kolanach, w zależności od preferencji i poziomu zaawansowania.\n" +
                "\n" +
                "Rozpocznij ruch, unosząc ciało w górę, napinając mięśnie pleców i ramion. Skup się na wykorzystaniu siły pleców do podciągania ciała do góry. W trakcie podciągania, staraj się dotknąć drążka klatką piersiową, utrzymując łokcie skierowane na zewnątrz.\n" +
                "\n" +
                "Kontynuuj ruch, aż podnosisz się jak najwyżej. Następnie opuść ciało kontrolowanie, wracając do wyjściowej pozycji. Pamiętaj o utrzymaniu napięcia w mięśniach pleców i ramion przez cały ruch.\n" +
                "\n" +
                "Wykonuj podciąganie nachwytem w kontrolowany sposób, skupiając się na prawidłowej technice i pełnym zakresie ruchu. Staraj się unikać nadmiernego huśtania ciałem czy wykorzystywania siły momentu bezwładności."
        val imageTitle2 = "Podciąganie na drążku"

        imageView2.setOnClickListener {
            showImageDialog(imageResource2, imageDescription2, imageTitle2)
        }

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageResource3 = R.drawable.wioslowanie_sztangielkami // Zasób obrazu
        val imageDescription3 = "Weź hantel w jedną rękę i stój w lekkim rozkroku z lekko zgiętymi kolanami. Wyprostuj plecy i pochyl tułów do przodu, tak aby był równoległy do podłogi. Wyciągnij wolną rękę w kierunku podłogi, aby utrzymać równowagę.\n" +
                "\n" +
                "Wyciągnij hantel w ręce trzymanej na wprost, z łokciem skierowanym na zewnątrz. Zacznij wiosłować, przyciągając łokieć do tyłu, a hantel w górę w kierunku boku tułowia.\n" +
                "\n" +
                "Napnij mięśnie pleców, ramion i mięśnie naramienne, kontrolując ruch. Pamiętaj, aby utrzymać stabilność tułowia i unikać nadmiernego wygięcia pleców.\n" +
                "\n" +
                "Kiedy hantel znajdzie się blisko ciała, zatrzymaj ruch na chwilę, a następnie powoli opuść hantel do wyjściowej pozycji, kontrolując opad. Powtórz ruch dla określonej liczby powtórzeń."
        val imageTitle3 = "Wiosłowanie hantlem w opadzie"

        imageView3.setOnClickListener {
            showImageDialog(imageResource3, imageDescription3, imageTitle3)
        }

        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageResource4 = R.drawable.wioslowanie_sztanga // Zasób obrazu
        val imageDescription4 = "Stanij przy sztandze, trzymając sztangę na szerokość barków, chwyt nachwytem (dłonie skierowane na zewnątrz). Stopy ustaw szerokość barków, lekko zgięte w kolanach. Pochyl tułów do przodu, zachowując prostą i wyprostowaną postawę. Kolana powinny być lekko zgięte, a plecy równoległe do podłogi.\n" +
                "\n" +
                "Zaczynając od rozciągniętej pozycji ramion, wyciągnij łokcie do tyłu, przyciągając sztangę w górę w kierunku klatki piersiowej. Napnij mięśnie pleców, ramion, mięśnie naramienne oraz mięśnie przywodzące, kontrolując ruch. Pamiętaj o utrzymaniu stabilności tułowia i unikaniu nadmiernego wygięcia pleców.\n" +
                "\n" +
                "Kiedy sztanga znajdzie się blisko ciała, zatrzymaj ruch na chwilę, a następnie powoli opuść sztangę w kontrolowany sposób do wyjściowej pozycji, rozciągając mięśnie pleców. Powtórz ruch dla określonej liczby powtórzeń.\n" +
                "\n" +
                "Wykonuj wiosłowanie sztangą w kontrolowany sposób, skupiając się na prawidłowej technice i pełnym zakresie ruchu. Pamiętaj o równomiernym oddychaniu i unikaj nadmiernego unoszenia ramion."
        val imageTitle4 = "Wiosłowanie sztangą"

        imageView4.setOnClickListener {
            showImageDialog(imageResource4, imageDescription4, imageTitle4)
        }

        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageResource5 = R.drawable.pull_face // Zasób obrazu
        val imageDescription5 = "Stanij wzdłuż maszyny lub uchwytu, trzymając go na wysokości klatki piersiowej, nachwytem (dłonie skierowane na zewnątrz). Stopy ustaw szerokość barków, lekko zgięte w kolanach. Pochyl tułów do przodu, zachowując prostą i wyprostowaną postawę. Kolana powinny być lekko zgięte, a plecy równoległe do podłogi.\n" +
                "\n" +
                "Zaczynając od rozciągniętej pozycji ramion, przyciągnij uchwyt w stronę twarzy, unosząc łokcie na wysokość policzków. Skoncentruj się na skurczeniu mięśni między łopatkami i wyciąganiu łokci do tyłu.\n" +
                "\n" +
                "Następnie kontrolowanie opuść uchwyt do wyjściowej pozycji, rozciągając mięśnie pleców. Powtórz ruch dla określonej liczby powtórzeń.\n" +
                "\n" +
                "Wykonuj face pull w kontrolowany sposób, skupiając się na prawidłowej technice i pełnym zakresie ruchu."
        val imageTitle5 = "Face pull"

        imageView5.setOnClickListener {
            showImageDialog(imageResource5, imageDescription5, imageTitle5)
        }

        val imageView6 = findViewById<ImageView>(R.id.imageView6)
        val imageResource6 = R.drawable.biceps // Zasób obrazu
        val imageDescription6 = "Stanij prosto, trzymając sztangę podchwytem na szerokość barków. Stopy ustaw szerokość barków, lekko zgięte w kolanach. Ramiona powinny być wyciągnięte wzdłuż ciała, blisko torsu.\n" +
                "\n" +
                "Zaczynając od wyprostowanej pozycji, zginij łokcie i unieś sztangę w kierunku ramion, skupiając się na skurczeniu mięśni przedramion. Upewnij się, że tylko przedramiona wykonują ruch, a pozostała część ciała pozostaje nieruchoma.\n" +
                "\n" +
                "Następnie kontrolowanie opuść sztangę do wyjściowej pozycji, rozciągając mięśnie przedramion. Powtórz ruch dla określonej liczby powtórzeń.\n" +
                "\n" +
                "Wykonuj uginanie przedramion ze sztangą w kontrolowany sposób, skupiając się na prawidłowej technice i pełnym zakresie ruchu."
        val imageTitle6 = "Ugianie przedramion ze sztangą"

        imageView6.setOnClickListener {
            showImageDialog(imageResource6, imageDescription6, imageTitle6)
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