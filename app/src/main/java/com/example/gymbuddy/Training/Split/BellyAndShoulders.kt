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

class BellyAndShoulders : AppCompatActivity() {
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
        setContentView(R.layout.activity_belly_and_shoulders)

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
        val imageResource1 = R.drawable.wyciskanie_zolnierskie // Zasób obrazu
        val imageDescription1 = "Wyciskanie żołnierskie to ćwiczenie skupiające się głównie na mięśniach barków, ramion i mięśniach korpusu. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Stój prosto, trzymając sztangę na wysokości klucza ramienno-obojczykowego (przed klatką piersiową) z nachwytem (dłonie skierowane do przodu). Możesz również wykorzystać hantle, trzymając je przy bokach ciała na wysokości barków.\n" +
                "\n" +
                "Zacznij wyciskanie, unosząc sztangę lub hantle w górę, prostując ręce. W trakcie unoszenia, zachowaj kontrolowany ruch i utrzymaj napięcie w mięśniach korpusu.\n" +
                "\n" +
                "Unikaj nadmiernego wygięcia pleców, unikając również wyrzutu bioder do przodu. Utrzymaj stabilną postawę i utrzymuj napięcie w mięśniach brzucha i pleców.\n" +
                "\n" +
                "Unieś sztangę lub hantle tak wysoko, aby ramiona były wyprostowane i sztanga znajdowała się nad głową. Możesz lekko zetknąć końce sztangi lub hantli na górze.\n" +
                "\n" +
                "Powoli kontroluj opuszczanie sztangi lub hantli, opuszczając je z powrotem do pozycji wyjściowej na wysokości klucza ramienno-obojczykowego. Pamiętaj, aby utrzymać kontrolowany ruch i nie opuszczać się zbyt gwałtownie.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania wyciskania żołnierskiego, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Utrzymuj kontrolowany ruch, utrzymuj napięcie w mięśniach korpusu i kontroluj oddech. Dostosuj ciężar sztangi lub hantli do swojej siły i możliwości."
        val imageTitle1 = "Wyciskanie żołnierskie"

        imageView1.setOnClickListener {
            showImageDialog(imageResource1, imageDescription1, imageTitle1)
        }

        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageResource2 = R.drawable.bark_tylni // Zasób obrazu
        val imageDescription2 = "Unoszenie sztangielek w opadzie jest ćwiczeniem skupiającym się na wzmacnianiu tylniej części barków, zwłaszcza mięśni nagłówka trójgłowego ramienia. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Weź w dłonie sztangielki, jedną w każdej dłoni, i stój prosto, ze stopami lekko rozstawionymi na szerokość barków.\n" +
                "\n" +
                "Skrzyżuj sztangielki przed sobą, tak aby dłonie były skierowane ku górze. Utrzymuj lekkie zgięcie w łokciach i lekko zgięte kolana.\n" +
                "\n" +
                "Pochyl się w przód w pasie biodrowym, trzymając plecy proste i utrzymując napięcie w mięśniach brzucha.\n" +
                "\n" +
                "Zacznij unoszenie sztangielek na boki, unosząc je na wysokość barków. Wykonuj ten ruch kontrolowanie, skupiając się na koncentrycznym skurczu mięśni nagłówka trójgłowego ramienia.\n" +
                "\n" +
                "Zatrzymaj się na chwilę na górze, aby wyczuć maksymalne napięcie w mięśniach barku.\n" +
                "\n" +
                "Powoli opuść sztangielki, kontrolując ruch, aby powrócić do pozycji wyjściowej.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania unoszenia sztangielek w opadzie, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Utrzymuj kontrolowany ruch, utrzymując napięcie w mięśniach barku. "
        val imageTitle2 = "Unoszenie ramion w opadzie"

        imageView2.setOnClickListener {
            showImageDialog(imageResource2, imageDescription2, imageTitle2)
        }

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageResource3 = R.drawable.pull_face // Zasób obrazu
        val imageDescription3 = "Zamocuj gumy treningowe na odpowiedniej wysokości lub użyj specjalnego uchwytu do gum, który można przymocować do drążka lub innego stabilnego punktu.\n" +
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
        val imageTitle3 = "Face pulls"

        imageView3.setOnClickListener {
            showImageDialog(imageResource3, imageDescription3, imageTitle3)
        }

        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageResource4 = R.drawable.wznosy_hantlami_pionowo // Zasób obrazu
        val imageDescription4 = "Wznosy ciężarków na boki to ćwiczenie skupiające się na wzmacnianiu mięśni bocznej części barku, zwłaszcza mięśni pośrednich i małżowiny. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Weź w dłonie ciężarki (np. hantle), jedną w każdej dłoni, i stój prosto, ze stopami lekko rozstawionymi na szerokość barków.\n" +
                "\n" +
                "Trzymaj ciężarki wzdłuż ciała, z dłońmi skierowanymi do przodu. Utrzymuj lekkie zgięcie w łokciach i lekko zgięte kolana.\n" +
                "\n" +
                "Pochyl się nieco w przód w pasie biodrowym, trzymając plecy proste i utrzymując napięcie w mięśniach brzucha.\n" +
                "\n" +
                "Zacznij unosić ciężarki na boki, wykonując ruch łokci do góry, aż do poziomu barków. Wykonuj ten ruch kontrolowanie, skupiając się na koncentrycznym skurczu mięśni barku.\n" +
                "\n" +
                "Zatrzymaj się na chwilę na górze, aby wyczuć maksymalne napięcie w mięśniach barku.\n" +
                "\n" +
                "Powoli opuść ciężarki, kontrolując ruch, aby powrócić do pozycji wyjściowej.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania wznosów ciężarków na boki, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Utrzymuj kontrolowany ruch, utrzymując napięcie w mięśniach barku. Unikaj przechylenia tułowia do przodu lub do tyłu."
        val imageTitle4 = "Wznosy ciężarków na boki"

        imageView4.setOnClickListener {
            showImageDialog(imageResource4, imageDescription4, imageTitle4)
        }

        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageResource5 = R.drawable.unoszenie_kolan // Zasób obrazu
        val imageDescription5 = "Unoszenie kolan do klatki w zwisie to ćwiczenie skupiające się na wzmacnianiu mięśni brzucha, szczególnie mięśni prostych brzucha. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Zaczep się na drążku do podciągania, trzymając drążek szerokością ramion i opuść się w pozycję zwisu. Upewnij się, że ramiona są wyprostowane, a plecy proste.\n" +
                "\n" +
                "Zgiń kolana i unieś je w kierunku klatki piersiowej, starając się dotknąć ich łokciami. Napnij mięśnie brzucha i unikaj używania impulsu lub huśtania się ciałem.\n" +
                "\n" +
                "Zatrzymaj się na chwilę, aby poczuć napięcie w mięśniach brzucha.\n" +
                "\n" +
                "Powoli opuść nogi, kontrolując ruch, aby powrócić do pozycji wyjściowej.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania unoszenia kolan do klatki w zwisie, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Utrzymuj kontrolowany ruch, utrzymując napięcie w mięśniach brzucha. Skup się na precyzyjnym unoszeniu kolan w kierunku klatki piersiowej, koncentrując się na działaniu mięśni brzucha."
        val imageTitle5 = "Unoszenie kolan do kaltki w zwisie"

        imageView5.setOnClickListener {
            showImageDialog(imageResource5, imageDescription5, imageTitle5)
        }

        val imageView6 = findViewById<ImageView>(R.id.imageView6)
        val imageResource6 = R.drawable.plank // Zasób obrazu
        val imageDescription6 = "Plank, znany również jako deska, to skuteczne ćwiczenie wzmacniające mięśnie brzucha, mięśnie korpusu oraz mięśnie stabilizujące. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Połóż się na podłodze na brzuchu. Zegnij łokcie i oprzyj się na przedramionach. Przedramiona powinny być ustawione równolegle do siebie, a łokcie powinny znajdować się bezpośrednio pod barkami.\n" +
                "\n" +
                "Wyciągnij nogi do tyłu i unieś ciało, podpierając się na palcach stóp. Palce stóp powinny być skierowane w dół, a ciało powinno tworzyć prostą linię od głowy do pięt.\n" +
                "\n" +
                "Napnij mięśnie brzucha i pośladków. Utrzymuj napięcie w mięśniach korpusu, unikając zapadania się w dolnej części pleców lub unoszenia bioder.\n" +
                "\n" +
                "Trzymaj tę pozycję przez określony czas, zazwyczaj od 20 do 60 sekund, lub według swojej wytrzymałości. Pamiętaj o równomiernym oddychaniu podczas trzymania pozycji.\n" +
                "\n" +
                "Powoli opuść się na podłogę, odpocznij przez chwilę i powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania planka, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Unikaj zapadania się w dolnej części pleców, unoszenia bioder lub wypychania pośladków do góry. Utrzymuj równomierną i kontrolowaną pozycję, utrzymując napięcie w mięśniach brzucha i korpusu. "
        val imageTitle6 = "Plank"

        imageView6.setOnClickListener {
            showImageDialog(imageResource6, imageDescription6, imageTitle6)
        }

        val imageView7 = findViewById<ImageView>(R.id.imageView7)
        val imageResource7 = R.drawable.lydka_stojac // Zasób obrazu
        val imageDescription7 = "Wspięcie na palce stojąc to ćwiczenie skupiające się głównie na mięśniach łydek. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Stań prosto, trzymając się równowagi. Stopy powinny być ustawione na szerokość barków lub nieco szersze.\n" +
                "\n" +
                "Włóż pięty na krawędź podłoża, tak aby palce stóp znajdowały się na ziemi. Możesz także skorzystać z podwyższenia, takiego jak klocki treningowe lub platforma ćwiczeniowa.\n" +
                "\n" +
                "Zaczynając od pozycji z piętami na podłożu, zegnij kolana i zacznij się wznosić na palcach stóp. Pamiętaj, aby unosić zarówno pięty, jak i palce stóp, aby uzyskać pełne skurcze mięśni łydek.\n" +
                "\n" +
                "W górnej pozycji, kiedy jesteś na wznak, zatrzymaj się na chwilę, czując napięcie w mięśniach łydek.\n" +
                "\n" +
                "Powoli kontroluj opuszczanie pięt z powrotem na podłoże, rozciągając mięśnie łydek i zachowując kontrolowany ruch.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania wspięcia na palce stojąc, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i skupieniu na mięśniach łydek. Utrzymuj stabilną postawę, utrzymuj napięcie mięśni korpusu i kontroluj oddech."
        val imageTitle7 = "Wspięcia na palce jednonóż"

        imageView7.setOnClickListener {
            showImageDialog(imageResource7, imageDescription7, imageTitle7)
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