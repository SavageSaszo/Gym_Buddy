package com.example.gymbuddy.Training.FBW

import android.content.Intent
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
        val imageDescription1 = "Przysiady ze sztangą na plecach to skuteczne ćwiczenie, które angażuje głównie mięśnie ud, pośladków, mięśnie czworogłowe uda oraz mięśnie korpusu. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Stanij prosto, trzymając sztangę na plecach na wysokości górnej części mięśni grzbietowych. Sztanga powinna być stabilnie umieszczona na karku, trzymając ją za chwytak nachwytem (dłonie skierowane na zewnątrz) na odpowiedniej szerokości.\n" +
                "\n" +
                "Rozstaw nogi na szerokość nieco większą niż szerokość barków. Unieś klatkę piersiową i zegnij lekko w pasie. To jest pozycja wyjściowa.\n" +
                "\n" +
                "Zginając w kolanach i biodrach, zacznij opuszczać ciało w dół, jakbyś chciał usiąść na niewidzialnym krześle. Pamiętaj, aby utrzymać stabilną postawę, patrząc przed siebie, a nie w dół.\n" +
                "\n" +
                "Kontynuuj opuszczanie się, dopóki uda nie będą równoległe do podłogi lub nie osiągniesz dogodnego zakresu ruchu. W tym momencie poczuj napięcie w mięśniach ud i pośladków.\n" +
                "\n" +
                "Od tego punktu zacznij się prostować, unosząc się do góry, powracając do pozycji wyjściowej. Pamiętaj o utrzymaniu równomiernego i kontrolowanego ruchu.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania przysiadów ze sztangą na plecach, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i skupieniu na mięśniach ud, pośladków i czworogłowych uda. Utrzymuj stabilną postawę, utrzymuj napięcie mięśni brzucha i pleców oraz kontroluj oddech."
        val imageTitle1 = "Przysiady ze sztangą na plecach"

        imageView1.setOnClickListener {
            showImageDialog(imageResource1, imageDescription1, imageTitle1)
        }

        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageResource2 = R.drawable.wyciskanie_zolnierskie // Zasób obrazu
        val imageDescription2 = "Wyciskanie żołnierskie to ćwiczenie skupiające się głównie na mięśniach barków, ramion i mięśniach korpusu. Oto opis techniki wykonania tego ćwiczenia:\n" +
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
        val imageTitle2 = "Wyciskanie sztangi stojąc"

        imageView2.setOnClickListener {
            showImageDialog(imageResource2, imageDescription2, imageTitle2)
        }

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageResource3 = R.drawable.hip_thrust // Zasób obrazu
        val imageDescription3 = "Hip thrust to ćwiczenie, które skupia się głównie na mięśniach pośladkowych, ale również angażuje mięśnie czworogłowe uda oraz mięśnie korpusu. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Usiądź na ziemi z plecami opartymi o ławkę lub inną stabilną podporę. Upewnij się, że ławka jest ustawiona na wysokości klatki piersiowej.\n" +
                "\n" +
                "Złóż nogi w kolanach, stawiając stopy na podłożu w odległości około szerokości bioder. Kiedy uniesiesz biodra, kolana powinny być zgięte pod kątem prostym.\n" +
                "\n" +
                "Trzymając sztangę na biodrach, tuż przed kośćmi biodrowymi, złap ją obiema rękoma z nachwytem (dłonie skierowane na zewnątrz) na odpowiedniej szerokości.\n" +
                "\n" +
                "Unieś biodra, naciskając na pięty, aż stworzysz prostą linię od kolan do barków. Napnij pośladki i utrzymuj napięcie w mięśniach pośladkowych przez cały ruch.\n" +
                "\n" +
                "W górnej pozycji, kiedy osiągniesz pełne wyprostowanie bioder, zatrzymaj się na chwilę, czując napięcie w mięśniach pośladkowych.\n" +
                "\n" +
                "Powoli kontroluj opuszczanie bioder, opuszczając się z powrotem na ziemię, ale nie dotykając tyłkiem podłoża. W kolejnej powtórce kontynuuj ruch unoszenia bioder.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania hip thrust, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i skupieniu na mięśniach pośladkowych. Utrzymuj stabilną postawę, utrzymuj napięcie mięśni brzucha i pleców oraz kontroluj oddech."
        val imageTitle3 = "Hip Thrust"

        imageView3.setOnClickListener {
            showImageDialog(imageResource3, imageDescription3, imageTitle3)
        }

        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageResource4 = R.drawable.wykroki_hantle // Zasób obrazu
        val imageDescription4 = "Wykroki z hantlami są skutecznym ćwiczeniem wzmacniającym mięśnie nóg, pośladków oraz mięśnie korpusu. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Weź w dłonie hantle i trzymaj je wzdłuż ciała, z dłońmi skierowanymi do przodu. Postaw się w pozycji wyprostowanej, z niewielkim rozstawieniem stóp.\n" +
                "\n" +
                "Wykonaj krok do przodu jedną nogą. Zginaj obie kolana, aby utworzyć dwie prostokątne pozycje w stawach kolanowych, z jednym kolanem zgiętym pod kątem 90 stopni, a drugim kolanem zbliżającym się do podłoża.\n" +
                "\n" +
                "Unikaj wysuwania kolana przed linię palców stopy. Upewnij się, że kolano jest wyprostowane nad kostką, aby uniknąć nadmiernego obciążenia stawu kolanowego.\n" +
                "\n" +
                "Zwróć uwagę na utrzymanie prostej postawy i stabilności tułowia. Utrzymuj napięcie w mięśniach korpusu, utrzymując napięte mięśnie brzucha i pleców.\n" +
                "\n" +
                "Wznikaj z pozycji wykonując napęd za pomocą nóg, wracając do pozycji wyjściowej. Wykonaj kolejny krok do przodu, tym razem naprzemiennie z drugą nogą.\n" +
                "\n" +
                "Powtórz ćwiczenie, wykonując żądaną liczbę powtórzeń dla obu nóg.\n" +
                "\n" +
                "Podczas wykonywania wykroków z hantlami, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Kontroluj ruch, utrzymuj stabilność tułowia i utrzymuj napięcie w mięśniach korpusu."
        val imageTitle4 = "Wykroki z hantlami"

        imageView4.setOnClickListener {
            showImageDialog(imageResource4, imageDescription4, imageTitle4)
        }

        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageResource5 = R.drawable.podciaganie_nachwytem // Zasób obrazu
        val imageDescription5 = "Podciąganie na drążku nachwytem to skuteczne ćwiczenie wzmacniające mięśnie pleców, ramion i mięśnie korpusu. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Stanij pod drążkiem, trzymając go na szerokość barków. Chwyć drążek nachwytem, czyli dłonie powinny być skierowane w stronę twarzy, a kciuki powinny być skierowane na zewnątrz.\n" +
                "\n" +
                "Zegnij łokcie i wyciągnij nogi, aby unieść się w powietrze. Możesz zacząć z wyprostowanymi ramionami lub z lekkim zgięciem w łokciach, w zależności od swojej preferencji.\n" +
                "\n" +
                "Zacznij podciąganie, wyciągając łopatki w dół i do tyłu. Skoncentruj się na aktywacji mięśni pleców i ramion, a nie na sile ramion.\n" +
                "\n" +
                "Podciągnij się, unieś ciało, aż broda jest wyżej od poziomu drążka. Staraj się dotknąć drążka klatką piersiową.\n" +
                "\n" +
                "Powoli opuść się, rozciągając mięśnie, ale nie rozluźniając całkowicie. Kontroluj ruch, unikaj opadania zbyt gwałtownie.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania podciągania na drążku nachwytem, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Utrzymuj kontrolowany ruch, skupiając się na mięśniach pleców i ramion."
        val imageTitle5 = "Podciąganie na drążku"

        imageView5.setOnClickListener {
            showImageDialog(imageResource5, imageDescription5, imageTitle5)
        }

        val imageView6 = findViewById<ImageView>(R.id.imageView6)
        val imageResource6 = R.drawable.pull_face // Zasób obrazu
        val imageDescription6 = "Pull-Off Press na gumach to dynamiczne ćwiczenie, które angażuje mięśnie górnej części ciała, w szczególności mięśnie ramion, klatki piersiowej i pleców. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Zacznij, stojąc w lekkim rozkroku, trzymając gumę na wysokości klatki piersiowej. Upewnij się, że gumy są odpowiednio napięte, aby zapewnić opór podczas ruchu.\n" +
                "\n" +
                "Chwyć gumy obiema rękoma, trzymając je na zewnątrz barków. Twoje dłonie powinny być skierowane do przodu, a łokcie lekko zgięte.\n" +
                "\n" +
                "Zacznij ruch, rozciągając gumy w bok i jednocześnie wypychając je przed siebie. Pamiętaj o utrzymaniu kontroli nad ruchem i utrzymaniu stabilnej postawy.\n" +
                "\n" +
                "Wykonaj pełne wyprostowanie ramion, wypychając gumy jak najdalej przed siebie. Staraj się utrzymać napięcie w mięśniach ramion i klatki piersiowej.\n" +
                "\n" +
                "Powróć do pozycji wyjściowej, kontrolując powolne zwijanie gum w stronę klatki piersiowej. Pamiętaj o utrzymaniu kontroli nad ruchem i utrzymaniu napięcia w mięśniach.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania Pull-Off Press na gumach, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Utrzymuj kontrolowany ruch, utrzymując napięcie w mięśniach górnej części ciała. Skup się na wykorzystaniu mięśni ramion, klatki piersiowej i pleców do napędzania ruchu."
        val imageTitle6 = "Pall of press"

        imageView6.setOnClickListener {
            showImageDialog(imageResource6, imageDescription6, imageTitle6)
        }

        val imageView7 = findViewById<ImageView>(R.id.imageView7)
        val imageResource7 = R.drawable.spacer_farmera // Zasób obrazu
        val imageDescription7 = "Spacer farmera to ćwiczenie, które angażuje mięśnie nóg, pośladków, mięśnie korpusu oraz mięśnie przedramion. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Weź w dłonie ciężary, na przykład hantle lub kettlebell'e, i trzymaj je wzdłuż ciała, z dłońmi skierowanymi do przodu. Upewnij się, że ciężary są odpowiednio dobrze wyważone.\n" +
                "\n" +
                "Stań prosto, utrzymując napięcie w mięśniach korpusu. W przypadku korzystania z ciężarów jednoręcznych, trzymaj jedną hantlę w każdej ręce. W przypadku korzystania z ciężaru dwuręcznego, trzymaj go oburącz.\n" +
                "\n" +
                "Zacznij krokić do przodu, utrzymując prostej postawy. Zrób duży krok naprzód jedną nogą, a następnie duży krok naprzód drugą nogą.\n" +
                "\n" +
                "Kontynuuj spacer, utrzymując równomierne tempo. Staraj się utrzymać stabilność korpusu, napięcie w mięśniach brzucha i pleców.\n" +
                "\n" +
                "Podczas spaceru farmera, utrzymuj napięcie w mięśniach nóg i pośladków. Unikaj unoszenia ramion, trzymając ciężary blisko ciała.\n" +
                "\n" +
                "Spacer farmera może być wykonywany na określoną odległość, na przykład od jednego miejsca do drugiego, lub przez określony czas.\n" +
                "\n" +
                "Podczas wykonywania spaceru farmera, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Utrzymuj kontrolowany ruch, utrzymując napięcie w mięśniach nóg, pośladków i mięśniach korpusu."
        val imageTitle7 = "Spacer farmera"

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