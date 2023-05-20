package com.example.gymbuddy.Training.Split

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

class ChestAndTriceps : AppCompatActivity() {
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
        setContentView(R.layout.activity_chest_and_triceps)

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
                "Podczas wyciskania na ławce płaskiej leżąc, pamiętaj o prawidłowej technice wykonania, utrzymaniu stabilnej postawy i kontrolowanym ruchu. Skup się na skurczu mięśni klatki piersiowej i tricepsów podczas unoszenia sztangi, unikając nadmiernego wygięcia pleców. "
        val imageTitle1 = "Wyciskanie sztangi na łąwce płaskiej"

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
                "Podczas wykonywania pompki na poręczach, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i napięciu mięśni. Skup się na skurczu mięśni klatki piersiowej i tricepsów podczas unoszenia ciała. Dodatkowo, utrzymuj stabilność i równowagę ciała oraz kontroluj oddech."
        val imageTitle2 = "Pompki na poręczach"

        imageView2.setOnClickListener {
            showImageDialog(imageResource2, imageDescription2, imageTitle2)
        }

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageResource3 = R.drawable.wyciskanie_hantli_skos // Zasób obrazu
        val imageDescription3 = "Wyciskanie hantli na ławce skośnej to ćwiczenie skupione głównie na mięśniach klatki piersiowej, przednich aktonach mięśnia naramiennego, tricepsach oraz mięśniach stabilizujących rdzeń. Poniżej znajduje się opis techniki wykonania tego ćwiczenia:\n" +
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
                "Podczas wyciskania hantli na ławce skośnej, pamiętaj o zachowaniu prawidłowej techniki wykonania, utrzymaniu stabilnej postawy i kontrolowanym ruchu. Skup się na skurczu mięśni klatki piersiowej i tricepsów podczas unoszenia hantli."
        val imageTitle3 = "Wycisaknie sztangielek na ławce dodatniej"

        imageView3.setOnClickListener {
            showImageDialog(imageResource3, imageDescription3, imageTitle3)
        }

        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageResource4 = R.drawable.rozpietki_dodatnie // Zasób obrazu
        val imageDescription4 = "Rozpiętki ze sztangielkami na ławce dodatniej są ćwiczeniem skupiającym się na wzmacnianiu mięśni klatki piersiowej, zwłaszcza mięśni piersiowych większych. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Połóż się na ławce dodatniej, tak aby głowa, plecy i pośladki były dobrze podparte. Stopy powinny być stabilnie osadzone na podłodze.\n" +
                "\n" +
                "Weź w dłonie sztangielki i unieś je nad klatkę piersiową, trzymając dłonie skierowane na zewnątrz i lekko zgięte łokcie. Unikaj skrzyżowania nadgarstków.\n" +
                "\n" +
                "Powoli opuść sztangielki na boki, rozciągając mięśnie klatki piersiowej. Wykonuj ten ruch kontrolowanie, skupiając się na ekscentrycznym skurczu mięśni.\n" +
                "\n" +
                "Zatrzymaj się na chwilę, gdy sztangielki są na wysokości klatki piersiowej.\n" +
                "\n" +
                "Powoli podnieś sztangielki, skupiając się na koncentrycznym skurczu mięśni klatki piersiowej.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania rozpiętek ze sztangielkami na ławce dodatniej, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Utrzymuj kontrolowany ruch, utrzymując napięcie w mięśniach klatki piersiowej. Unikaj dotykania sztangielek do siebie na górze ruchu. Skup się na precyzyjnym rozciąganiu mięśni klatki piersiowej i kontrolowanym unoszeniu sztangielek. "
        val imageTitle4 = "Rozpiętki ze sztangielkami na ławce dodatniej"

        imageView4.setOnClickListener {
            showImageDialog(imageResource4, imageDescription4, imageTitle4)
        }

        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageResource5 = R.drawable.triceps_na_wyciagu // Zasób obrazu
        val imageDescription5 = "Prostowanie ramion na linii z wyciągu górnego to ćwiczenie skupiające się na wzmacnianiu mięśni tricepsów, czyli tylnych mięśni ramienia. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Stań przodem do wyciągu górnego, przyjmując stabilną postawę, ze stopami szeroko rozstawionymi na szerokość barków.\n" +
                "\n" +
                "Chwyć drążek wyciągu górnego szeroko, trzymając go rękami w lekkim nachyleniu do przodu. Upewnij się, że chwyt jest pewny i stabilny.\n" +
                "\n" +
                "Zegnij łokcie, tak aby przedramiona były skierowane w dół i równolegle do podłoża. Ramiona powinny być blisko tułowia.\n" +
                "\n" +
                "Zacznij prostować ramiona, wykorzystując głównie siłę mięśni tricepsów. Wyciągnij drążek do pełnego wyprostu ramion, kontrolując ruch.\n" +
                "\n" +
                "Zatrzymaj się na chwilę, aby poczuć napięcie w mięśniach tricepsów.\n" +
                "\n" +
                "Powoli zegnij łokcie, aby powrócić do pozycji wyjściowej.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania prostowania ramion na linii z wyciągu górnego, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Utrzymuj kontrolowany ruch, utrzymując napięcie w mięśniach tricepsów. Unikaj przechylenia ciała do przodu lub do tyłu oraz używania momentum."
        val imageTitle5 = "Prostowanie ramion na linie z wyciągu górnego"

        imageView5.setOnClickListener {
            showImageDialog(imageResource5, imageDescription5, imageTitle5)
        }

        val imageView6 = findViewById<ImageView>(R.id.imageView6)
        val imageResource6 = R.drawable.martwy_ciag // Zasób obrazu
        val imageDescription6 = "Pompki na poręczach to skuteczne ćwiczenie, które angażuje przede wszystkim mięśnie klatki piersiowej, tricepsów i mięśnie przedramion. Oto opis techniki wykonania tego ćwiczenia:\n" +
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
                "Podczas wykonywania pompki na poręczach, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i napięciu mięśni. Skup się na skurczu mięśni klatki piersiowej i tricepsów podczas unoszenia ciała. Dodatkowo, utrzymuj stabilność i równowagę ciała oraz kontroluj oddech."
        val imageTitle6 = " Pompki na poręczach"

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