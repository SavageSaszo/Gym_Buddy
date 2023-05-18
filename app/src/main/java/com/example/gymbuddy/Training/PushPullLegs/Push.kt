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
        val imageDescription1 = "Wyciskanie sztangi na ławce płaskiej to ćwiczenie" +
                " siłowe, które koncentruje się na wzmacnianiu klatki piersiowej," +
                " ramion i tricepsów. Aby je wykonać prawidłowo, połóż się na " +
                "płaskiej ławce, tak aby plecy, pośladki i głowa miały pełny" +
                " kontakt z powierzchnią. Nogi powinny być ustawione stabilnie" +
                " na podłodze. Chwyć sztangę na szerokość nieco większą niż " +
                "szerokość barków, trzymając ją pewnie i stabilnie. Zdejmij" +
                " sztangę ze stojaków i opuść ją powoli i kontrolowanie do" +
                " linii klatki piersiowej, zginając łokcie. Pamiętaj, aby" +
                " utrzymać napięcie w mięśniach klatki piersiowej i tricepsach." +
                " Następnie, napnij mięśnie klatki piersiowej i wyprostuj ramiona," +
                " unosząc sztangę w górę. Kontroluj ruch i unikaj zbyt dużego" +
                " naprężenia pleców. Gdy sztanga znajdzie się na górze, chwilę" +
                " ją zatrzymaj, a następnie powoli opuść z powrotem do linii" +
                " klatki piersiowej, kontrolując ruch. Wykonuj wyciskanie sztangi" +
                " na ławce płaskiej w kontrolowany sposób, skupiając się na" +
                " prawidłowej technice i pełnym zakresie ruchu. Pamiętaj również" +
                " o odpowiednim oddychaniu, wdychając powietrze podczas opuszczania" +
                " sztangi i wydychając je podczas jej unoszenia."
        val imageTitle1 = "Wyciskanie sztangi na ławce płaskiej"

        imageView1.setOnClickListener {
            showImageDialog(imageResource1, imageDescription1, imageTitle1)
        }

        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageResource2 = R.drawable.dipy // Zasób obrazu
        val imageDescription2 = "Pompki na poręczach to ćwiczenie siłowe angażujące" +
                " mięśnie klatki piersiowej, ramion i tricepsów. Aby je wykonać" +
                " prawidłowo, znajdź stabilne poręcze o odpowiedniej wysokości i" +
                " odległości. Stojąc między poręczami, stopy na podłodze, dłonie" +
                " pewnie chwytając poręcze, zegnij łokcie, opuść ciało, zbliżając" +
                " klatkę piersiową do poziomu poręczy. Napnij mięśnie klatki" +
                " piersiowej i tricepsów, wyprostowując ramiona i unosząc ciało." +
                " Kontroluj ruch, zatrzymując się na szczycie, a następnie powoli" +
                " opuść ciało do pozycji wyjściowej. Wykonuj pompki na poręczach" +
                " kontrolowanie, skupiając się na pełnym zakresie ruchu i właściwej" +
                " technice. Regularne wykonywanie tego ćwiczenia wzmocni mięśnie" +
                " górnej części ciała."
        val imageTitle2 = " Pompki na poręczach"

        imageView2.setOnClickListener {
            showImageDialog(imageResource2, imageDescription2, imageTitle2)
        }

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageResource3 = R.drawable.wyciskanie_zolnierskie // Zasób obrazu
        val imageDescription3 = "Wyciskanie sztangi stojąc to ćwiczenie siłowe," +
                " które skupia się na wzmacnianiu klatki piersiowej, ramion" +
                " i tricepsów. Aby je wykonać prawidłowo, postępuj zgodnie z" +
                " poniższymi wskazówkami. Stań prosto, stopy rozstawione na" +
                " szerokość barków, trzymając sztangę na wysokości klatki" +
                " piersiowej. Chwyć sztangę szeroko na zewnątrz, dłonie" +
                " skierowane do przodu, palce oplatające sztangę. Upewnij" +
                " się, że masz pewny chwyt i utrzymuj napięcie w mięśniach" +
                " brzucha i pośladków, zapewniając stabilność ciała. Teraz" +
                " unieś sztangę, prostując ramiona. Kontroluj ruch i unikaj" +
                " nadmiernego wygięcia pleców. Gdy sztanga jest na górze," +
                " utrzymaj ją przez chwilę, a następnie powoli opuść z" +
                " powrotem do pozycji wyjściowej, zginając łokcie i zwracając" +
                " uwagę na napięcie w mięśniach klatki piersiowej i" +
                " tricepsach. Pamiętaj o utrzymaniu właściwej techniki" +
                " i pełnym zakresie ruchu."
        val imageTitle3 = "Wyciskanie sztangi stojąc"

        imageView3.setOnClickListener {
            showImageDialog(imageResource3, imageDescription3, imageTitle3)
        }

        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageResource4 = R.drawable.wyciskanie_hantli_skos // Zasób obrazu
        val imageDescription4 = "Wyciskanie hantli na ławce skośnej to" +
                " ćwiczenie skupione na wzmacnianiu klatki piersiowej," +
                " ramion i tricepsów. Usiądź na ławce skośnej, tak aby " +
                "górna część pleców była dobrze podparta. Utrzymuj stabilną" +
                " pozycję ciała. Weź hantle w obie ręce i unieś je na poziom" +
                " ramion, trzymając przedramiona równolegle do podłogi." +
                " Wydychając powietrze, napnij mięśnie klatki piersiowej" +
                " i wolno opuść hantle na boki, kontrolując ruch. Zatrzymaj" +
                " się, gdy twoje ramiona znajdą się na wysokości barków." +
                " Następnie, wdychając powietrze, wyprostuj ramiona, unosząc" +
                " hantle do góry w kierunku sufitu. Zatrzymaj się na chwilę" +
                " w pełnym wyproście ramion. Powtórz ruch przez określoną" +
                " liczbę powtórzeń, kontrolując tempo i zachowując prawidłową" +
                " technikę. Pamiętaj o utrzymaniu napięcia w mięśniach" +
                " klatki piersiowej i tricepsów podczas całego ruchu." +
                " Po zakończeniu serii ćwiczenia, delikatnie opuść hantle" +
                " i zdejmij je z ławki skośnej. Pamiętaj również o" +
                " odpowiednim oddychaniu, wdychając powietrze podczas" +
                " opuszczania hantli i wydychając je podczas ich unoszenia." +
                " Wykonuj wyciskanie hantli na ławce skośnej w kontrolowany" +
                " sposób, skupiając się na prawidłowej technice i pełnym" +
                " zakresie ruchu."
        val imageTitle4 = "Wyciskanie hantli na ławce skośnej"

        imageView4.setOnClickListener {
            showImageDialog(imageResource4, imageDescription4, imageTitle4)
        }

        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageResource5 = R.drawable.wycisaknie_francuskie // Zasób obrazu
        val imageDescription5 = "Wyciskanie francuskie to ćwiczenie skupione" +
                " na wzmacnianiu tricepsów. Aby je wykonać prawidłowo, postępuj" +
                " zgodnie z poniższymi wskazówkami: Usiądź na ławce, trzymając" +
                " sztangę nad głową. Twoje stopy powinny być płasko na ziemi," +
                " a plecy proste. Przytrzymaj sztangę oburącz na wysokości" +
                " klatki piersiowej, z dłońmi skierowanymi w stronę głowy." +
                " Wydychając powietrze, zgiń łokcie i opuść sztangę za głowę," +
                " kontrolując ruch. Zatrzymaj się, gdy przedramiona będą" +
                " niemal równoległe do podłogi. Następnie, wdychając powietrze," +
                " wyprostuj ramiona, unosząc sztangę w górę w kierunku sufitu." +
                " Zatrzymaj się na chwilę w pełnym wyproście ramion. Powtórz" +
                " ruch przez określoną liczbę powtórzeń, kontrolując tempo i" +
                " zachowując prawidłową technikę. Pamiętaj o utrzymaniu napięcia" +
                " w tricepsach podczas całego ruchu. Po zakończeniu serii" +
                " ćwiczenia, delikatnie opuść sztangę i umieść ją z powrotem" +
                " na stojak. Wykonuj wyciskanie francuskie w kontrolowany" +
                " sposób, skupiając się na prawidłowej technice i pełnym" +
                " zakresie ruchu."
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