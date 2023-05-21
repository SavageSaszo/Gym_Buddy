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
        val imageDescription1 = "Martwy ciąg to jedno z podstawowych ćwiczeń siłowych, które angażuje głównie mięśnie pleców, nogi i pośladki. Poniżej znajduje się opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Stań prosto, stopy ustawione na szerokość barków. Sztanga powinna znajdować się na podłodze przed tobą.\n" +
                "\n" +
                "Zegnij kolana i biodra, schylając się w pasie, aby sięgnąć po sztangę. Chwyć sztangę nachwytem, z szerokością chwytu nieco większą niż szerokość barków.\n" +
                "\n" +
                "Trzymając plecy proste, wyciągnij klatkę piersiową do przodu, unikając zaokrąglania pleców.\n" +
                "\n" +
                "Zachowując stabilność i napięcie mięśni brzucha, zacznij podnosić sztangę, unosząc ją w górę. Napinaj mięśnie pleców, nóg i pośladków, aby generować siłę wznoszenia.\n" +
                "\n" +
                "Kontynuuj podnoszenie sztangi, aż staniesz w pełnym wyprostowaniu ciała. W tym momencie plecy powinny być proste, a barki wciągnięte.\n" +
                "\n" +
                "Zatrzymaj się na chwilę w górnym punkcie, a następnie powoli opuść sztangę, kontrolując ruch i zachowując prawidłową postawę.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Martwy ciąg jest skutecznym ćwiczeniem na rozwinięcie siły i masy mięśniowej, szczególnie mięśni pleców, nóg, pośladków i przedramion."
        val imageTitle1 = "Martwy ciąg"

        imageView1.setOnClickListener {
            showImageDialog(imageResource1, imageDescription1, imageTitle1)
        }

        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageResource2 = R.drawable.podciaganie_nachwytem // Zasób obrazu
        val imageDescription2 = "Podciąganie na drążku nachwytem to skuteczne ćwiczenie, które głównie angażuje mięśnie pleców, ramion oraz mięśnie naramienne. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Stanij pod drążkiem, uniesionym na wysokość, na której będziesz w stanie wykonać podciągnięcia. Chwyć drążek nachwytem (dłonie skierowane na zewnątrz) na szerokość nieco większą niż szerokość barków.\n" +
                "\n" +
                "Zegnij nogi w kolanach lub podnieś stopy na tzw. \"podwieszkę\", aby uniknąć dotykania ziemi podczas wykonywania podciągnięć.\n" +
                "\n" +
                "Zegnij łokcie, wyciągnij klatkę piersiową do przodu i unosząc się, ciągnij ciało w górę, doprowadzając brodę do wysokości drążka.\n" +
                "\n" +
                "Skup się na skurczu mięśni pleców i ramion. Utrzymuj kontrolowany ruch i unikaj używania impulsu.\n" +
                "\n" +
                "Zatrzymaj się na chwilę w górnym punkcie, gdy jesteś w pełnym podciągnięciu, a następnie powoli opuść się, rozciągając mięśnie pleców, aż ramiona będą wyprostowane.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania podciągania na drążku nachwytem, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i napięciu mięśni."
        val imageTitle2 = "Podciąganie na drążku"

        imageView2.setOnClickListener {
            showImageDialog(imageResource2, imageDescription2, imageTitle2)
        }

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageResource3 = R.drawable.wioslowanie_jednoracz // Zasób obrazu
        val imageDescription3 = "Wiosłowanie hantlem w opadzie to skuteczne ćwiczenie, które angażuje głównie mięśnie pleców, ramion, mięśnie naramienne oraz mięśnie środkowej partii pleców. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Ustaw się w pozycji wyjściowej, stojąc z lekkim wygięciem w pasie, kolana lekko ugięte, a górna część ciała skierowana w przód. W jednej ręce trzymaj hantel, a drugą rękę wsparcie o stabilny przedmiot (np. ławka).\n" +
                "\n" +
                "Nachwyć hantel (dłoń skierowana do ciebie) i pozwól, aby ręka z hantlem opadła swobodnie wzdłuż ciała, z łokciem lekko zgiętym.\n" +
                "\n" +
                "Zegnij łokieć, unosząc hantel w górę w kierunku boku ciała, aż do momentu, gdy łokieć będzie na wysokości tułowia. Skup się na skurczu mięśni pleców i ramion.\n" +
                "\n" +
                "Zatrzymaj się na chwilę w górnym punkcie, poczując napięcie w mięśniach pleców i ramion.\n" +
                "\n" +
                "Powoli opuść hantel z powrotem do pozycji wyjściowej, kontrolując ruch i rozciągając mięśnie pleców.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń, a następnie wykonaj to samo na drugą stronę, trzymając hantel w drugiej ręce.\n" +
                "\n" +
                "Podczas wykonywania wiosłowania hantlem w opadzie, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i skupieniu na skurczu mięśni pleców i ramion. "
        val imageTitle3 = "Wiosłowanie hantlem w opadzie"

        imageView3.setOnClickListener {
            showImageDialog(imageResource3, imageDescription3, imageTitle3)
        }

        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageResource4 = R.drawable.wioslowanie_sztanga // Zasób obrazu
        val imageDescription4 = "Wiosłowanie sztangą to skuteczne ćwiczenie, które angażuje głównie mięśnie pleców, ramion, mięśnie naramienne oraz mięśnie środkowej partii pleców. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Stanij w lekkim rozkroku, trzymając sztangę przed sobą na szerokość nieco większą niż szerokość barków. Uchwyć sztangę nachwytem (dłonie skierowane na zewnątrz), trzymając ją na wysokości kolan z lekko zgiętymi kolanami.\n" +
                "\n" +
                "Nachyl tułów w przód, utrzymując prostą pozycję pleców. Klatka piersiowa powinna być wysunięta do przodu, a łopatki lekko złączone.\n" +
                "\n" +
                "Zacznij wiosłować, zginając łokcie i unosząc sztangę w stronę klatki piersiowej. Skup się na skurczu mięśni pleców i ramion.\n" +
                "\n" +
                "Unosząc łokcie, wyciągnij łopatki do tyłu, wyciągając klatkę piersiową do przodu. Ruch powinien pochodzić głównie z mięśni pleców, a nie z ramion.\n" +
                "\n" +
                "Zatrzymaj się na chwilę w górnym punkcie, poczując napięcie w mięśniach pleców.\n" +
                "\n" +
                "Powoli opuść sztangę z powrotem do pozycji wyjściowej, kontrolując ruch i rozciągając mięśnie pleców.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania wiosłowania sztangą, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i skupieniu na skurczu mięśni pleców i ramion. Utrzymuj stabilną postawę, utrzymuj napięcie mięśni brzucha i pleców oraz kontroluj oddech. "
        val imageTitle4 = "Wiosłowanie sztangą"

        imageView4.setOnClickListener {
            showImageDialog(imageResource4, imageDescription4, imageTitle4)
        }

        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageResource5 = R.drawable.bark_tylni // Zasób obrazu
        val imageDescription5 = "Zamocuj gumy treningowe na odpowiedniej wysokości lub użyj specjalnego uchwytu do gum, który można przymocować do drążka lub innego stabilnego punktu.\n" +
                "\n" +
                "Stanij w pozycji wyjściowej, trzymając gumy treningowe w obu dłoniach. Postaw się na tyle do tyłu, aby utrzymać napięcie w gumach.\n" +
                "\n" +
                "Rozstaw nogi na szerokość barków, lekko zgięte w kolanach. Utrzymuj prostą postawę, wyciągnij klatkę piersiową do przodu i zegnij lekko w pasie.\n" +
                "\n" +
                "Zegnij łokcie i przyciągnij gumy w stronę twarzy, unosząc ramiona na boki. Skoncentruj się na skurczu mięśni ruchowych barku.\n" +
                "\n" +
                "W górnej pozycji, kiedy gumy dotkną policzków, zatrzymaj się na chwilę, czując napięcie w mięśniach.\n" +
                "\n" +
                "Powoli kontroluj opuszczanie gum z powrotem do pozycji wyjściowej, rozciągając mięśnie ruchowe barku.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania face pull z użyciem gum, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i skupieniu na skurczu mięśni ruchowych barku. Utrzymuj stabilną postawę, utrzymuj napięcie mięśni brzucha i pleców oraz kontroluj oddech. "
        val imageTitle5 = "Face pull"

        imageView5.setOnClickListener {
            showImageDialog(imageResource5, imageDescription5, imageTitle5)
        }

        val imageView6 = findViewById<ImageView>(R.id.imageView6)
        val imageResource6 = R.drawable.biceps // Zasób obrazu
        val imageDescription6 = "Uginanie przedramion ze sztangą to ćwiczenie, które skupia się głównie na mięśniach bicepsów, ale również angażuje mięśnie przedramion. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Stanij prosto, trzymając sztangę nachwytem (dłonie skierowane do ciebie) na szerokość nieco większą niż szerokość barków. Sztanga powinna być trzymana na wprost, tuż przy udach.\n" +
                "\n" +
                "Zegnij łokcie i unieś sztangę, unosząc ją do góry w kierunku ramion. Pamiętaj, aby ruch był kontrolowany i skupiony na mięśniach bicepsów.\n" +
                "\n" +
                "W górnej pozycji, kiedy sztanga znajdzie się blisko ramion, zatrzymaj się na chwilę, czując napięcie w mięśniach bicepsów.\n" +
                "\n" +
                "Powoli kontroluj opuszczanie sztangi z powrotem do pozycji wyjściowej, rozciągając mięśnie bicepsów i zachowując kontrolowany ruch.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania uginania przedramion ze sztangą, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i skupieniu na skurczu mięśni bicepsów. "
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