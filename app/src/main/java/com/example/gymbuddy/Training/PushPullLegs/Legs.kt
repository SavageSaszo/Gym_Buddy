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
                "Podczas wykonywania przysiadów ze sztangą na plecach, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i skupieniu na mięśniach ud, pośladków i czworogłowych uda. "
        val imageTitle1 = "Przysiad ze sztangą na plecach"

        imageView1.setOnClickListener {
            showImageDialog(imageResource1, imageDescription1, imageTitle1)
        }

        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageResource2 = R.drawable.wykroki_bulgarskie // Zasób obrazu
        val imageDescription2 = "Wykroki bułgarskie to ćwiczenie, które angażuje głównie mięśnie ud, pośladków oraz mięśnie przywodziciele i przywodziciele bioder. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Przygotuj dwie ławki o podobnej wysokości i postaw je równolegle obok siebie. Możesz również skorzystać z platformy treningowej lub innego stabilnego podłoża.\n" +
                "\n" +
                "Stanij przed jedną z ławek, zwrócony plecami do drugiej ławki. Podnieś jedną stopę i umieść ją na tylnej ławce, tak że stopy są skierowane w dół.\n" +
                "\n" +
                "Wyprostuj plecy, utrzymując równowagę, i rozpocznij ruch zginania nóg w kolanach. Zginij nogę przodującą w kolanie i opuść się w dół, aż kolano tylniej nogi prawie dotknie podłoża.\n" +
                "\n" +
                "Pamiętaj, aby utrzymać stabilną postawę i nie zginaj się w pasie. Twój korpus powinien być wyprostowany, a klatka piersiowa uniesiona.\n" +
                "\n" +
                "Naciskaj na piętę przodującej nogi, aby wrócić do pozycji wyjściowej, unosząc się do góry. Unikaj wykorzystywania siły z tylniej nogi, skupiając się na mięśniach przodującej nogi.\n" +
                "\n" +
                "Powtórz ćwiczenie dla jednej nogi przez zaleconą liczbę powtórzeń, a następnie wykonaj je dla drugiej nogi.\n" +
                "\n" +
                "Podczas wykonywania wykroków bułgarskich, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i skupieniu na mięśniach ud, pośladków oraz mięśniach przywodziciele i przywodziciele bioder."
        val imageTitle2 = "Przysiad bułgarski"

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
                "Podczas wykonywania hip thrust, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i skupieniu na mięśniach pośladkowych. Utrzymuj stabilną postawę, utrzymuj napięcie mięśni brzucha i pleców oraz kontroluj oddech. "
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
        val imageDescription5 = "Wspięcie na palce stojąc to ćwiczenie skupiające się głównie na mięśniach łydek. Oto opis techniki wykonania tego ćwiczenia:\n" +
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
        val imageDescription7 = "Plank, znany również jako deska, to skuteczne ćwiczenie wzmacniające mięśnie brzucha, mięśnie korpusu oraz mięśnie stabilizujące. Oto opis techniki wykonania tego ćwiczenia:\n" +
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