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

class FBW_B : AppCompatActivity() {
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
        setContentView(R.layout.activity_fbw_b)

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
        val imageResource1 = R.drawable.przysiad_przod // Zasób obrazu
        val imageDescription1 = "Przysiad ze sztangą z przodu to zaawansowane ćwiczenie angażujące mięśnie nóg, pośladków, mięśnie korpusu oraz mięśnie pleców. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Stanij prosto i weź sztangę w dłonie, trzymając ją przed klatką piersiową. Chwyć sztangę szerzej niż szerokość ramion, tak aby palce były skierowane do przodu.\n" +
                "\n" +
                "Unieś sztangę, podtrzymując ją przed klatką piersiową, przy użyciu siły mięśni przedramion i ramion. Wyprostuj plecy i unieś klatkę piersiową.\n" +
                "\n" +
                "Stabilizuj korpus, napięcie utrzymuj w mięśniach brzucha i pleców. W czasie wykonywania ruchu utrzymuj wzrok skierowany przed siebie.\n" +
                "\n" +
                "Zgiń kolana i biodra, prowadząc ruch do przysiadu. Spuść się, aż uda będą równoległe do podłoża. Utrzymaj kontrolę nad ruchem i utrzymuj stabilną postawę.\n" +
                "\n" +
                "Następnie wznikaj, napędzając ruch głównie za pomocą mięśni nóg. Wyprostuj kolana i biodra, wracając do pozycji wyjściowej.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania przysiadu ze sztangą z przodu, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Utrzymuj kontrolowany ruch, utrzymując napięcie w mięśniach nóg, pośladków, mięśniach korpusu i pleców. Utrzymuj prostą postawę, unikaj przechylenia do przodu lub do tyłu."
        val imageTitle1 = "Przysiady ze sztangą z przodu"

        imageView1.setOnClickListener {
            showImageDialog(imageResource1, imageDescription1, imageTitle1)
        }

        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageResource2 = R.drawable.wyciskanie_lawka_plaska // Zasób obrazu
        val imageDescription2 = "Wyciskanie na ławce płaskiej leżąc to popularne ćwiczenie angażujące głównie mięśnie klatki piersiowej, tricepsów i mięśnie przedramion. Oto opis techniki wykonania tego ćwiczenia:\n" +
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
        val imageTitle2 = "Wyciskanie na ławce płaskiej"

        imageView2.setOnClickListener {
            showImageDialog(imageResource2, imageDescription2, imageTitle2)
        }

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageResource3 = R.drawable.martwy_ciag // Zasób obrazu
        val imageDescription3 = "Martwy ciąg to jedno z podstawowych ćwiczeń siłowych, które angażuje głównie mięśnie pleców, nogi i pośladki. Poniżej znajduje się opis techniki wykonania tego ćwiczenia:\n" +
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
                "Martwy ciąg jest skutecznym ćwiczeniem na rozwinięcie siły i masy mięśniowej, szczególnie mięśni pleców, nóg, pośladków i przedramion. Pamiętaj o prawidłowej technice wykonania, kontrolowanej amplitudzie ruchu, utrzymaniu stabilności pleców i napięciu mięśni brzucha podczas podnoszenia sztangi."
        val imageTitle3 = "Martwy ciąg"

        imageView3.setOnClickListener {
            showImageDialog(imageResource3, imageDescription3, imageTitle3)
        }

        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageResource4 = R.drawable.wioslowanie_sztanga // Zasób obrazu
        val imageDescription4 = "Wiosłowanie sztangą w opadzie jest ćwiczeniem skoncentrowanym na mięśniach pleców, zwłaszcza mięśniach grzbietu. Poniżej znajduje się opis techniki wykonania tego ćwiczenia:\n" +
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
                "Podczas wiosłowania sztangą w opadzie ważne jest, aby utrzymać prawidłową technikę wykonania, kontrolować ruch i skupić się na skurczu mięśni grzbietu. Również pamiętaj o napięciu mięśni brzucha i pleców, aby utrzymać stabilność i zapobiec nadmiernemu zaokrąglaniu pleców."
        val imageTitle4 = "Wiosłowanie sztangą w opadzie"

        imageView4.setOnClickListener {
            showImageDialog(imageResource4, imageDescription4, imageTitle4)
        }

        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageResource5 = R.drawable.wykroki_bulgarskie // Zasób obrazu
        val imageDescription5 = "Wykroki bułgarskie to ćwiczenie, które angażuje głównie mięśnie ud, pośladków oraz mięśnie przywodziciele i przywodziciele bioder. Oto opis techniki wykonania tego ćwiczenia:\n" +
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
                "Podczas wykonywania wykroków bułgarskich, pamiętaj o zachowaniu prawidłowej techniki wykonania, kontrolowanym ruchu i skupieniu na mięśniach ud, pośladków oraz mięśniach przywodziciele i przywodziciele bioder. "
        val imageTitle5 = "Przysiady bułgarskie"

        imageView5.setOnClickListener {
            showImageDialog(imageResource5, imageDescription5, imageTitle5)
        }

        val imageView6 = findViewById<ImageView>(R.id.imageView6)
        val imageResource6 = R.drawable.plank_boczny // Zasób obrazu
        val imageDescription6 = "Plank boczny to ćwiczenie skupiające się na wzmacnianiu mięśni brzucha, mięśni korpusu i stabilizacji bocznej. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Połóż się na boku, na podłożu. Unieś tułów, opierając się na przedramieniu i boku stopy. Przedramię powinno być ułożone prostopadle do tułowia, a łokieć powinien być pod klatką piersiową.\n" +
                "\n" +
                "Napięcie utrzymuj w mięśniach korpusu, utrzymując ciało w linii prostej od głowy do stóp. Nie pozwól, aby biodra opadały w dół lub się podnosiły.\n" +
                "\n" +
                "Utrzymuj tę pozycję, trzymając napięcie przez zalecony czas lub liczbę powtórzeń. Skup się na utrzymaniu stabilności i kontroli ruchu.\n" +
                "\n" +
                "Po zakończeniu ćwiczenia na jednym boku, przejdź na drugi bok i powtórz procedurę, aby wzmocnić obie strony ciała.\n" +
                "\n" +
                "Podczas wykonywania planku bocznego pamiętaj o utrzymaniu prawidłowej techniki wykonania. Utrzymuj kontrolowaną pozycję, utrzymując napięcie w mięśniach korpusu. "
        val imageTitle6 = "Plank boczny"

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
        val imageTitle7 = "Spacer farmera z hantlem/ciężarem"

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