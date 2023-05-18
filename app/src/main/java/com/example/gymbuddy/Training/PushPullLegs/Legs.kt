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

class Legs : AppCompatActivity() {
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
        setContentView(R.layout.activity_legs)

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
        val imageDescription1 = "Stań prosto, z szerokością barków, a sztangę umieść na górnej części pleców, trzymając ją stabilnie na ramionach. Trzymaj sztangę na wysokości karku, z dłońmi skierowanymi do przodu. Zachowaj wyprostowaną postawę, z łopatkami ściągniętymi do siebie i brzuchem napiętym.\n" +
                "\n" +
                "Rozpocznij ruch, schylając kolana i biodra, jakbyś siadał na krześle, utrzymując równowagę i kontrolując opad. Unikaj przechylenia tułowia do przodu i utrzymuj stabilność pleców. Głowa powinna być wyprostowana, a wzrok skierowany przed siebie.\n" +
                "\n" +
                "Kontynuuj ruch, aż uda będą równoległe do podłogi, lub do momentu, gdy osiągniesz pełne zgięcie kolan. Pamiętaj, aby utrzymać stabilność i kontrolować ruch w dolnym punkcie przysiadu.\n" +
                "\n" +
                "Następnie, napięciem mięśni nóg, wyprostuj kolana i biodra, unosząc się w górę, powracając do wyjściowej pozycji. Kontroluj ruch i unikaj zbyt gwałtownego wznoszenia się."
        val imageTitle1 = "Przysiad ze sztangą na plecach"

        imageView1.setOnClickListener {
            showImageDialog(imageResource1, imageDescription1, imageTitle1)
        }

        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageResource2 = R.drawable.wykroki_bulgarskie // Zasób obrazu
        val imageDescription2 = "Stanij przed ławką lub inną stabilną powierzchnią. Postaw jedną stopę na ławce tak, aby przednia część stopy była na niej oparta, a tył stopy był uniesiony. Drugą stopę postaw na podłodze, zachowując lekkie zgięcie w kolanie.\n" +
                "\n" +
                "Zachowaj prostą postawę, z ramionami rozluźnionymi wzdłuż ciała. Unieś lekko klatkę piersiową i napiętą brzuchem.\n" +
                "\n" +
                "Rozpocznij ruch, składając biodro i zginając kolano na nodze, która znajduje się na podłodze. Zachowaj równowagę i kontroluj opad. Unikaj przechylenia tułowia do przodu i utrzymuj stabilność pleców. Głowa powinna być wyprostowana, a wzrok skierowany przed siebie.\n" +
                "\n" +
                "Schodząc na dół, spuść się tak nisko, jak to możliwe, kontrolując ruch i utrzymując napięcie w mięśniach nóg.\n" +
                "\n" +
                "Następnie, napięciem mięśni nóg, wyprostuj kolano i biodro, unosząc się w górę, wracając do wyjściowej pozycji. Kontroluj ruch i unikaj zbyt gwałtownego wznoszenia się.\n" +
                "\n" +
                "Wykonuj przysiady bułgarskie w kontrolowany sposób, skupiając się na prawidłowej technice i pełnym zakresie ruchu."
        val imageTitle2 = "Przysiad bułgarski"

        imageView2.setOnClickListener {
            showImageDialog(imageResource2, imageDescription2, imageTitle2)
        }

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageResource3 = R.drawable.hip_thrust // Zasób obrazu
        val imageDescription3 = "Usiądź na podłodze, opierając plecy o ławkę, tak aby łopatki miały kontakt z jej górną częścią. Zegnij kolana i postaw stopy płasko na podłodze, w odległości około szerokości barków. Trzymaj ręce wzdłuż ciała lub skrzyżowane na klatce piersiowej.\n" +
                "\n" +
                "W tym położeniu, napięciem mięśni pośladków i brzucha, unoszę biodra w górę, jednocześnie wypychając łokcie w podłogę i napinając pośladki. W górnym punkcie, utrzymaj napięcie przez chwilę, zanim powoli opuścisz biodra na podłogę, kontrolując ruch.\n" +
                "\n" +
                "Podczas wykonywania hip thrust, staraj się utrzymać linię od kolan do ramion prosto. Unikaj przemieszczania się łokci w czasie ruchu. Skup się na napinaniu mięśni pośladków i kontrolowaniu ruchu bioder.\n" +
                "\n" +
                "Wykonuj hip thrust w kontrolowany sposób, skupiając się na prawidłowej technice i pełnym zakresie ruchu."
        val imageTitle3 = " Hip thrust"

        imageView3.setOnClickListener {
            showImageDialog(imageResource3, imageDescription3, imageTitle3)
        }

        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageResource4 = R.drawable.dzien_dobry // Zasób obrazu
        val imageDescription4 = "Dzień dobry to ćwiczenie skupiające się na rozwinięciu mięśni pośladków, ud oraz dolnej części pleców. Aby je wykonać, rozpocznij od stanu wyprostowanego, trzymając sztangę na plecach, opartą na górnej części pleców.\n" +
                "\n" +
                "Następnie, wykonaj głęboki wdech, napnij brzuch i skoncentruj się na napieraniu mięśniami na ściany jamy brzusznej. W czasie ruchu, wypchnij biodra do tyłu, jednocześnie utrzymując naturalne ustawienie miednicy. Delikatnie zegnij kolana, aby umożliwić płynny ruch.\n" +
                "\n" +
                "Podczas wykonywania ruchu, skłon się do przodu, trzymając sztangę na plecach. Pamiętaj o kontrolowanym tempie wykonywania i utrzymaniu prawidłowej postawy. Unikaj nadmiernego wygięcia w dolnej części pleców.\n" +
                "\n" +
                "Ćwiczenie dzień dobry ma na celu wzmocnienie mięśni pośladków, ud oraz dolnej części pleców."
        val imageTitle4 = " Dzień dobry ze sztangą"

        imageView4.setOnClickListener {
            showImageDialog(imageResource4, imageDescription4, imageTitle4)
        }

        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageResource5 = R.drawable.lydka_stojac // Zasób obrazu
        val imageDescription5 = "Wspięcia na palce to ćwiczenie skupiające się na wzmacnianiu mięśni łydek oraz stabilizatorów stopy. Aby je wykonać, stan na prostych nogach i stóp o szerokości około barków.\n" +
                "\n" +
                "Zaczynając od pozycji stojącej, unieś stopę, unosząc pięty i opierając się na palcach. Skoncentruj się na napięciu mięśni łydek i stabilizacji stopy. Unosząc się jak najwyżej, zatrzymaj się na chwilę, a następnie powoli opuść pięty, wracając do pozycji wyjściowej.\n" +
                "\n" +
                "Podczas wykonywania wspięć na palce, staraj się utrzymać równowagę i kontrolować ruch. Skup się na napięciu mięśni łydek i unikaj opadania stopy. Możesz również dostosować stopień trudności, wykonując ćwiczenie z obciążeniem, na przykład trzymając hantle w dłoniach."
        val imageTitle5 = "Wspięcia na palce"

        imageView5.setOnClickListener {
            showImageDialog(imageResource5, imageDescription5, imageTitle5)
        }

        val imageView6 = findViewById<ImageView>(R.id.imageView6)
        val imageResource6 = R.drawable.unoszenie_nog // Zasób obrazu
        val imageDescription6 = "Połóż się na maszynie do unoszenia nóg z brzuchem opartym na podparciu. Umieść ręce na wyznaczonych uchwytach lub na bokach ciała dla stabilizacji.\n" +
                "\n" +
                "Następnie, napnij mięśnie brzucha i unosząc napięte nogi, podnoś je w górę w kierunku sufitu. Skoncentruj się na napięciu mięśni dwugłowych uda. Unoszenie nóg powinno odbywać się za pomocą mięśni brzucha i uda, a nie poprzez impulsywny ruch całego ciała.\n" +
                "\n" +
                "Kontynuuj unoszenie nóg, aż osiągniesz pełne napięcie mięśni dwugłowych uda. Następnie, powoli opuść nogi z powrotem na pozycję wyjściową, kontrolując ruch.\n" +
                "\n" +
                "Pamiętaj, aby wykonywać to ćwiczenie w sposób kontrolowany, skupiając się na napięciu mięśni dwugłowych uda."
        val imageTitle6 = "Unoszenie nóg leżąc"

        imageView6.setOnClickListener {
            showImageDialog(imageResource6, imageDescription6, imageTitle6)
        }

        val imageView7 = findViewById<ImageView>(R.id.imageView7)
        val imageResource7 = R.drawable.plank // Zasób obrazu
        val imageDescription7 = "Połóż się na podłodze, opierając się na przedramionach i palcach stóp.\n" +
                "\n" +
                "Upewnij się, że ciało jest wyprostowane, a linia od głowy do stóp jest prosta. Napnij mięśnie brzucha, aby utrzymać stabilność w korpusie. Unikaj opadania bioder lub uniesienia pośladków.\n" +
                "\n" +
                "Trzymaj pozycję plank przez określony czas, zazwyczaj od 30 sekund do 1 minuty lub dłużej, w zależności od twojego poziomu zaawansowania. Pamiętaj, aby utrzymywać równomierne oddychanie podczas trzymania pozycji.\n" +
                "\n" +
                "Ważne jest, aby zachować prawidłową formę i unikać nadmiernego skrzywienia pleców lub unoszenia pośladków. Skup się na utrzymaniu napięcia w mięśniach korpusu przez cały czas trwania ćwiczenia.\n" +
                "\n" +
                "Plank jest skutecznym ćwiczeniem wzmocnienia mięśni korpusu i stabilizacji."
        val imageTitle7 = "Plank/Deska"

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