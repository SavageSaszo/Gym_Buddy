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

class BackAndBiceps : AppCompatActivity() {
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
        setContentView(R.layout.activity_back_and_biceps)

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
        val imageResource2 = R.drawable.wioslowanie_sztanga // Zasób obrazu
        val imageDescription2 = "Wiosłowanie sztangą w opadzie jest ćwiczeniem skoncentrowanym na mięśniach pleców, zwłaszcza mięśniach grzbietu. Poniżej znajduje się opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Rozpocznij, stojąc z nogami na szerokość barków i lekko zgiętymi kolanami. Chwyć sztangę nachwytem, trzymając ją na szerokość barków.\n" +
                "\n" +
                "Zegnij tułów w pasie, aż górna część ciała będzie prawie równoległa do podłoża. Utrzymaj prostą i stabilną postawę, napinając mięśnie brzucha i pleców.\n" +
                "\n" +
                "Zaczynając od wyprostowanych ramion, zgiągaj łokcie i unosź sztangę w kierunku klatki piersiowej, skupiając się na skurczu mięśni grzbietu.\n" +
                "\n" +
                "Zatrzymaj się na chwilę, gdy sztanga dotknie lub zbliży się do klatki piersiowej, a następnie powoli kontroluj ruch, opuszczając sztangę do początkowej pozycji.\n" +
                "\n" +
                "Kontynuuj powtarzanie ruchu przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wiosłowania sztangą w opadzie ważne jest, aby utrzymać prawidłową technikę wykonania, kontrolować ruch i skupić się na skurczu mięśni grzbietu."
        val imageTitle2 = "Pendaly row"

        imageView2.setOnClickListener {
            showImageDialog(imageResource2, imageDescription2, imageTitle2)
        }

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageResource3 = R.drawable.podciaganie_mlotkowe // Zasób obrazu
        val imageDescription3 = "Podciąganie na drążku chwytem młotkowym to ćwiczenie skupione głównie na mięśniach pleców, ramion oraz mięśniach kończyn górnych. Poniżej znajduje się opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Zacznij, stojąc pod drążkiem, trzymając go chwytem młotkowym. Dłonie powinny być skierowane do wewnątrz, a odległość między nimi nieco większa niż szerokość barków.\n" +
                "\n" +
                "Wyciągnij ręce, aby zawisnąć na drążku, utrzymując prostą postawę i napięcie mięśni brzucha.\n" +
                "\n" +
                "Zacznij podciągać się, zginając łokcie i unosząc klatkę piersiową w kierunku drążka. Skup się na skurczu mięśni grzbietu.\n" +
                "\n" +
                "Podciągnij się, aż broda znajdzie się powyżej poziomu drążka. Zatrzymaj się na chwilę, poczując napięcie w mięśniach.\n" +
                "\n" +
                "Powoli kontroluj ruch, gdy opuszczasz się z powrotem do wyciągniętych ramion, utrzymując kontrolę nad opadaniem.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podciąganie na drążku chwytem młotkowym jest efektywnym ćwiczeniem na rozwinięcie siły i masy mięśniowej w plecach, ramionach i kończynach górnych. "
        val imageTitle3 = "Podciaganie chwytem młotkowym"

        imageView3.setOnClickListener {
            showImageDialog(imageResource3, imageDescription3, imageTitle3)
        }

        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageResource4 = R.drawable.plecy_wyciag // Zasób obrazu
        val imageDescription4 = "Początkowo stanowisko wyciągu powinno być odpowiednio dostosowane do twojego wzrostu, tak abyś mógł utrzymać stabilną postawę podczas wykonywania ćwiczenia.\n" +
                "\n" +
                "Zacznij, stojąc twarzą do wyciągu, trzymając drążek na wysokości ramion, z chwytem nachwytem (dłonie zwrócone na zewnątrz).\n" +
                "\n" +
                "Stabilizuj korpus, napięcie mięśni brzucha i pleców, utrzymując prostą postawę.\n" +
                "\n" +
                "Zegnij łokcie i przyciągnij drążek w stronę klatki piersiowej, skupiając się na skurczu mięśni grzbietu.\n" +
                "\n" +
                "W momencie, gdy drążek jest blisko klatki piersiowej, zatrzymaj się na chwilę i poczuj napięcie w mięśniach.\n" +
                "\n" +
                "Powoli kontroluj ruch, gdy drążek jest rozciągany do początkowej pozycji.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Przyciąganie drążka do klatki na wyciągu górnym jest skutecznym ćwiczeniem na rozwinięcie mięśni grzbietu, zwłaszcza mięśni szerokich grzbietu. Pamiętaj o prawidłowej technice wykonania, kontrolowanej amplitudzie ruchu i skupieniu na skurczu mięśni."
        val imageTitle4 = "Przyciąganie drążka do klatki na wyciągu górnym "

        imageView4.setOnClickListener {
            showImageDialog(imageResource4, imageDescription4, imageTitle4)
        }

        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageResource5 = R.drawable.wioslowanie_jednoracz // Zasób obrazu
        val imageDescription5 = "Ćwiczenie polega na wykonywaniu ruchu przypominającego wiosłowanie przy użyciu sztangielki i podparcia na ławce. Poniżej znajduje się opis techniki wykonania tego ćwiczenia.\n" +
                "\n" +
                "Rozpocznij, ustawiając ławkę w pozycji poziomej. Stań przy ławce z jedną nogą z przodu, a drugą z tyłu, trzymając sztangielkę w jednej dłoni.\n" +
                "\n" +
                "Zgiń tułów w pasie, tak aby twój grzbiet był równoległy do podłoża. Dłonie powinny znajdować się poniżej barków, a ręka z sztangielką powinna być wyprostowana.\n" +
                "\n" +
                "Zacznij ćwiczenie, zginając rękę w łokciu i przyciągając sztangielkę do boku tułowia. Skup się na skurczu mięśni grzbietu.\n" +
                "\n" +
                "W momencie, gdy sztangielka jest blisko boku tułowia, zatrzymaj się na chwilę i poczuj napięcie w mięśniach.\n" +
                "\n" +
                "Powoli kontroluj ruch, gdy sztangielka jest opuszczana do początkowej pozycji.\n" +
                "\n" +
                "Powtórz ćwiczenie dla drugiej strony, wykonując równą liczbę powtórzeń.\n" +
                "\n" +
                "Wiosłowanie sztangielką w podporze jest doskonałym ćwiczeniem na rozwinięcie mięśni grzbietu, mięśni naramiennych, mięśni ramion i mięśni stabilizujących korpus."
        val imageTitle5 = "Wiosłowanie sztangielką w podporze"

        imageView5.setOnClickListener {
            showImageDialog(imageResource5, imageDescription5, imageTitle5)
        }

        val imageView6 = findViewById<ImageView>(R.id.imageView6)
        val imageResource6 = R.drawable.martwy_ciag // Zasób obrazu
        val imageDescription6 = "Ćwiczenie polega na wykonaniu uginania ramion z wykorzystaniem sztangielek i chwytu młotkowego. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Rozpocznij, trzymając sztangielki w obu dłoniach z chwytem młotkowym. Dłonie powinny być skierowane ku sobie, a sztangielki powinny wisieć swobodnie wzdłuż ciała.\n" +
                "\n" +
                "Stań prosto, napięcie mięśni brzucha i pleców, utrzymując lekkie zgięcie w kolanach.\n" +
                "\n" +
                "Zacznij ćwiczenie, zginając ramiona w łokciach i unosząc sztangielki ku górze. W tym momencie powinieneś czuć skurcz mięśni ramion, zwłaszcza mięśni dwugłowych.\n" +
                "\n" +
                "W momencie, gdy sztangielki znajdą się blisko barków, zatrzymaj się na chwilę i poczuj napięcie w mięśniach.\n" +
                "\n" +
                "Powoli kontroluj ruch, gdy sztangielki są opuszczane do początkowej pozycji.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Uginanie ramion ze sztangielkami chwytem młotkowym jest skutecznym ćwiczeniem na rozwinięcie mięśni ramion, zwłaszcza mięśni dwugłowych."
        val imageTitle6 = "Uginanie ramion ze sztangielkami chwytem młotkowym"

        imageView6.setOnClickListener {
            showImageDialog(imageResource6, imageDescription6, imageTitle6)
        }

        val imageView7 = findViewById<ImageView>(R.id.imageView7)
        val imageResource7 = R.drawable.biceps_supinacja // Zasób obrazu
        val imageDescription7 = "Ćwiczenie polega na uginaniu ramion z wykorzystaniem sztangielek i ruchu supinacji nadgarstków. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Rozpocznij, trzymając sztangielki w obu dłoniach, chwytem nachwytem (dłonie zwrócone na zewnątrz) i dłonie skierowane wzdłuż ciała.\n" +
                "\n" +
                "Stań prosto, napięcie mięśni brzucha i pleców, utrzymując lekkie zgięcie w kolanach.\n" +
                "\n" +
                "Zacznij ćwiczenie, zginając ramiona w łokciach i unosząc sztangielki ku górze. Jednocześnie wykonaj ruch supinacji nadgarstków, obracając dłonie na zewnątrz.\n" +
                "\n" +
                "Kontynuuj ruch aż do momentu, gdy sztangielki znajdą się blisko barków, a nadgarstki będą maksymalnie supinowane.\n" +
                "\n" +
                "Zatrzymaj się na chwilę i poczuj napięcie w mięśniach ramion.\n" +
                "\n" +
                "Powoli kontroluj ruch, gdy sztangielki są opuszczane do początkowej pozycji, przywracając jednocześnie pierwotną pozycję dłoni.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Uginanie ramion ze sztangielkami z supinacją jest skutecznym ćwiczeniem na rozwinięcie mięśni ramion, zwłaszcza mięśni dwugłowych i mięśni przedramion."
        val imageTitle7 = "Uginanie ramion ze sztangielkami z supinacją"

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