package com.example.gymbuddy.Training.FBW

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.gymbuddy.R
import com.example.gymbuddy.Training.TrainingMenu

class FBW_A : AppCompatActivity() {
    private lateinit var seekBar: SeekBar
    private lateinit var textView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var countDownTimer: CountDownTimer
    private var selectedMinutes: Int = 0
    private lateinit var mediaPlayer: MediaPlayer

    private var dialog: AlertDialog? = null // Dodana deklaracja zmiennej dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fbw_a)

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
        val imageResource1 = R.drawable.przysiad_tyl // Zasób obrazu
        val imageDescription1 = "Stój prosto, stopy lekko szerzej niż szerokość barków. Umieść sztangę na podporach sztangi na wysokości klatki piersiowej.\n" +
                "\n" +
                "Chwyć sztangę nieco szerszym niż szerokość barków uchwytem. Umieść ją na górnej części mięśni karku i barkach.\n" +
                "\n" +
                "Wdychając głęboko, unieś sztangę, prostując nogi i biodra. Trzymaj klatkę piersiową wyprostowaną i skieruj wzrok przed siebie.\n" +
                "\n" +
                "Opadaj, zginając kolana i biodra, jakbyś siadał na wyimaginowanym krześle. Pamiętaj, aby utrzymać kolana nad linią stóp.\n" +
                "\n" +
                "Kontroluj ruch, aż uda będą równoległe do podłoża lub nieco niżej.\n" +
                "\n" +
                "Napięciem mięśni ud i pośladków, wróć do pozycji wyjściowej, unosząc sztangę."
        val imageTitle1 = "Przysiady ze sztangą na plecach"

        imageView1.setOnClickListener {
            showImageDialog(imageResource1, imageDescription1, imageTitle1)
        }

        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageResource2 = R.drawable.wyciskanie_stojac // Zasób obrazu
        val imageDescription2 = "Stój prosto, stopy nieco szerzej niż szerokość barków. Umieść sztangę na poziomej wysokości klatki piersiowej.\n" +
                "\n" +
                "Chwyć sztangę nieco szerszym niż szerokość barków uchwytem. Palce powinny być skierowane do przodu, a kciuki owinięte wokół sztangi.\n" +
                "\n" +
                "Wdychając głęboko, unieś sztangę z pomocą nóg i bioder. Trzymaj klatkę piersiową wyprostowaną, ramiona blisko ciała i spojrzenie skierowane przed siebie.\n" +
                "\n" +
                "Opadaj, zginając lekko kolana i biodra, aby stworzyć lekkie napięcie w mięśniach nogi.\n" +
                "\n" +
                "Kontroluj ruch, unosząc sztangę prosto do góry, wzdłuż linii środka ciała. Wyprostuj kolana i biodra jednocześnie, wykorzystując siłę mięśni ramion i barków.\n" +
                "\n" +
                "Gdy sztanga jest w górnej pozycji, wydychaj powoli i utrzymaj chwilę napięcie w mięśniach ramion.\n" +
                "\n" +
                "Powoli opuść sztangę, zginając lekko łokcie i skręcając biodra i kolana, aby powrócić do pozycji wyjściowej."
        val imageTitle2 = "Wyciskanie sztangi stojąc"

        imageView2.setOnClickListener {
            showImageDialog(imageResource2, imageDescription2, imageTitle2)
        }

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageResource3 = R.drawable.hip_thrust // Zasób obrazu
        val imageDescription3 = "Usiądź na ziemi, plecami do ławki, nogi zgięte w kolanach, stopy płasko na podłożu w szerokości barków. Umieść sztangę na biodrach, trzymając ją oburącz za sztangielki.\n" +
                "\n" +
                "Wdychając głęboko, napięciem mięśni pośladków i brzucha, unosząc biodra w górę. W tym momencie waga powinna być wsparta na łopatkach i stopach.\n" +
                "\n" +
                "Kontynuuj unoszenie bioder, aż ciało znajdzie się w linii prostej od kolan do barków. Skup się na aktywacji mięśni pośladków.\n" +
                "\n" +
                "Na górnym punkcie napięcia, wytrzymaj przez chwilę i poczuj skurcz mięśni pośladkowych.\n" +
                "\n" +
                "Powoli opuść biodra, kontrolując ruch, aż wrócisz do pozycji wyjściowej."
        val imageTitle3 = "Hip Thrust"

        imageView3.setOnClickListener {
            showImageDialog(imageResource3, imageDescription3, imageTitle3)
        }

        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageResource4 = R.drawable.wykroki_hantle // Zasób obrazu
        val imageDescription4 = "Stój prosto, trzymając hantle obok ciała. Zrób duży krok naprzód, zginając jedno kolano, opadając biodrem. Opuść drugą nogę, utrzymując równowagę. Wznieś się, naciskając na piętę przedniej nogi. Powtórz z drugą nogą. Utrzymuj prostą postawę."
        val imageTitle4 = "Wykroki z hantlami"

        imageView4.setOnClickListener {
            showImageDialog(imageResource4, imageDescription4, imageTitle4)
        }

        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageResource5 = R.drawable.podciaganie // Zasób obrazu
        val imageDescription5 = "Stan na podłożu, stopy na szerokość barków. Wyprostuj ramiona i sięgnij nachwytem za drążek, trzymając go nieco szerzej niż szerokość barków.\n" +
                "\n" +
                "Rozpocznij ruch, zginając łokcie i unosząc ciało w górę, skierowując łopatki w stronę siebie. Napnij mięśnie pleców, aby wykonać ruch.\n" +
                "\n" +
                "Podciągnij się aż do momentu, gdy broda znajdzie się nieco powyżej wysokości drążka. Zachowaj kontrolę nad ruchem i unikaj nadmiernego machania ciałem.\n" +
                "\n" +
                "Powoli opuść się, kontrolując ruch, aż ręce będą ponownie wyprostowane i ciało opadnie do pozycji wyjściowej.\n" +
                "\n" +
                "Kontynuuj ćwiczenie przez określoną liczbę powtórzeń."
        val imageTitle5 = "Podciąganie na drążku"

        imageView5.setOnClickListener {
            showImageDialog(imageResource5, imageDescription5, imageTitle5)
        }

        val imageView6 = findViewById<ImageView>(R.id.imageView6)
        val imageResource6 = R.drawable.podciaganie // Zasób obrazu
        val imageDescription6 = ""
        val imageTitle6 = "Pall of press"

        imageView6.setOnClickListener {
            showImageDialog(imageResource6, imageDescription6, imageTitle6)
        }

        val imageView7 = findViewById<ImageView>(R.id.imageView7)
        val imageResource7 = R.drawable.spacer_farmera // Zasób obrazu
        val imageDescription7 = "Weź w obie ręce hantle o odpowiednim obciążeniu. Stojąc prosto, trzymaj hantle wzdłuż ciała, z ramionami zrelaksowanymi i plecami prosto.\n" +
                "\n" +
                "Skoncentruj się na utrzymaniu prawidłowej postawy. Wyprostuj się, napnij mięśnie brzucha i pleców.\n" +
                "\n" +
                "Rozpocznij spacer, krocząc stabilnie naprzód. Krok powinien być pewny i kontrolowany.\n" +
                "\n" +
                "Trzymaj hantle blisko ciała, unikając zboczenia na boki lub kołysania. Zachowaj równowagę i kontroluj ruchy.\n" +
                "\n" +
                "Kontynuuj spacer przez określony dystans lub czas."
        val imageTitle7 = "Spacer farmera"

        imageView7.setOnClickListener {
            showImageDialog(imageResource7, imageDescription7, imageTitle7)
        }


    }

    private fun showImageDialog(imageResource: Int, imageDescription: String, imageTitle: String) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_layout, null)
        val imageView = dialogView.findViewById<ImageView>(R.id.dialogImageView)
        imageView.setImageResource(imageResource) // Ustawienie zasobu obrazu

        val textView = dialogView.findViewById<TextView>(R.id.dialogTextView)
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