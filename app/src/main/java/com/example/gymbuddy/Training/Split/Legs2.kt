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

class Legs2 : AppCompatActivity() {
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
        setContentView(R.layout.activity_legs2)

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
        val imageResource1 = R.drawable.nogi_suwnica_poziom // Zasób obrazu
        val imageDescription1 = "Wypychanie nóg na maszynie siedząc to ćwiczenie skupiające się na wzmacnianiu mięśni czworogłowych ud, czyli mięśni przednich ud. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Usiądź na maszynie w odpowiedniej pozycji, tak aby stopy spoczywały na platformie do wypychania nóg, a kolana były wyprostowane. Dopasuj oparcie i siedzenie do swojej wysokości, aby mieć stabilną i wygodną pozycję.\n" +
                "\n" +
                "Umieść stopy na platformie w odpowiedniej szerokości, z palcami skierowanymi do przodu lub lekko na zewnątrz.\n" +
                "\n" +
                "Chwyć uchwyty na bokach siedzenia lub trzymaj się poręczy, aby utrzymać stabilność ciała.\n" +
                "\n" +
                "Zaczynając od ugięcia nóg w stawach kolanowych, wyprostuj nogi i wypchnij platformę na zewnątrz. Skoncentruj się na pracy mięśni czworogłowych ud.\n" +
                "\n" +
                "Zatrzymaj się na chwilę na końcowym wyproście, aby poczuć napięcie w mięśniach nóg.\n" +
                "\n" +
                "Powoli zgiń nogi, aby powrócić do pozycji wyjściowej, kontrolując ruch.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania wypychania nóg na maszynie siedząc, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Utrzymuj kontrolowany ruch, utrzymując napięcie w mięśniach czworogłowych ud. Unikaj impulsywnego ruchu i wykorzystywania innych mięśni do pomocy. Skoncentruj się na precyzyjnym wypychaniu platformy i kontrolowanym zginaniu nóg. "
        val imageTitle1 = "Prostowanie nóg na maszynie"

        imageView1.setOnClickListener {
            showImageDialog(imageResource1, imageDescription1, imageTitle1)
        }

        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageResource2 = R.drawable.wykroki_hantle // Zasób obrazu
        val imageDescription2 = "Wykroki z hantlami są skutecznym ćwiczeniem wzmacniającym mięśnie nóg, pośladków oraz mięśnie korpusu. Oto opis techniki wykonania tego ćwiczenia:\n" +
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
                "Podczas wykonywania wykroków z hantlami, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Kontroluj ruch, utrzymuj stabilność tułowia i utrzymuj napięcie w mięśniach korpusu. Unikaj nadmiernego wysuwania kolana przed linię palców stopy i kontroluj oddech. "
        val imageTitle2 = "Wykroki ze sztangielkami"

        imageView2.setOnClickListener {
            showImageDialog(imageResource2, imageDescription2, imageTitle2)
        }

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageResource3 = R.drawable.przysiad_przod // Zasób obrazu
        val imageDescription3 = "Przysiad ze sztangą z przodu to ćwiczenie skupiające się głównie na wzmacnianiu mięśni ud, pośladków i mięśni rdzenia. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Stań prosto z sztangą podniesioną na wysokość klatki piersiowej, trzymając ją na przedramionach i chwytając za róg sztangi. Przyjmij szerokość chwytu nieco szerszą niż szerokość barków.\n" +
                "\n" +
                "Umieść sztangę na przedramionach, trzymając ją palcami i opierając ją na górnej części klatki piersiowej. Łokcie powinny być uniesione, aby utrzymać stabilność sztangi.\n" +
                "\n" +
                "Stopy ustaw szerokość barków, a palce stóp lekko skieruj na zewnątrz.\n" +
                "\n" +
                "Zacznij opuszczać ciało w dół, jak przy tradycyjnym przysiadzie. Zachowaj naturalną krzywiznę w dolnej części pleców i utrzymaj klatkę piersiową wyprostowaną.\n" +
                "\n" +
                "Kontynuuj opadanie, aż uda znajdą się w pozycji równoległej do podłogi. Upewnij się, że kolana nie przekraczają linii palców stóp.\n" +
                "\n" +
                "Wypchnij się mocno, używając głównie mięśni ud, aby wrócić do pozycji wyjściowej. Napnij pośladki i mięśnie brzucha podczas wznoszenia.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania przysiadu ze sztangą z przodu, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Utrzymuj kontrolowany ruch, utrzymując równowagę i napięcie mięśniowe. Unikaj przechylenia się do przodu lub do tyłu, trzymając klatkę piersiową wyprostowaną. Skoncentruj się na precyzyjnym opadaniu i wypychaniu, korzystając głównie z mięśni ud. "
        val imageTitle3 = "Przysiady ze sztangą z przodu"

        imageView3.setOnClickListener {
            showImageDialog(imageResource3, imageDescription3, imageTitle3)
        }

        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageResource4 = R.drawable.wyciskanie_suwnica // Zasób obrazu
        val imageDescription4 = "Wypychanie platformy, znane również jako suwnica na nogi, to ćwiczenie skupiające się na wzmacnianiu mięśni nóg, głównie mięśni czworogłowych ud. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Postaw się na platformie suwnicy, tak aby twoje stopy były ustawione na platformie, a kolana znajdowały się w linii z osią obciążenia.\n" +
                "\n" +
                "Chwyć uchwyty bocznego panelu suwnicy lub trzymaj się poręczy, aby utrzymać stabilność ciała.\n" +
                "\n" +
                "Zacznij opuszczać ciało w dół, zginając nogi w stawach kolanowych. Ruch ten powinien być kontrolowany i powolny.\n" +
                "\n" +
                "Gdy osiągniesz pełne wyprostowanie kolan, wypchnij platformę w górę, prostując nogi. Skoncentruj się na pracy mięśni czworogłowych ud.\n" +
                "\n" +
                "Zatrzymaj się na chwilę na końcowym wyproście, aby poczuć napięcie w mięśniach nóg.\n" +
                "\n" +
                "Powoli zgiń nogi, aby powrócić do pozycji wyjściowej, kontrolując ruch.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania wypychania platformy, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Utrzymuj kontrolowany ruch i napięcie mięśniowe w mięśniach nóg. Unikaj impulsywnego ruchu i wykorzystywania innych mięśni do pomocy. Skup się na precyzyjnym wypychaniu platformy i kontrolowanym zginaniu nóg."
        val imageTitle4 = "Wypychanie nogami na suwnicy"

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
        val imageTitle5 = "Wspięcie na palcach stojąc na maszynie"

        imageView5.setOnClickListener {
            showImageDialog(imageResource5, imageDescription5, imageTitle5)
        }

        val imageView6 = findViewById<ImageView>(R.id.imageView6)
        val imageResource6 = R.drawable.unoszenie_nog // Zasób obrazu
        val imageDescription6 = "Uginanie nóg na maszynie leżąc to ćwiczenie skupiające się na wzmacnianiu mięśni tylnych ud, czyli mięśni dwugłowych uda. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Usiądź na maszynie do uginania nóg, leżąc na brzuchu i umieść stopy pod rolkami maszyny. Dopasuj poduszkę rolki do wysokości łydek, aby mieć stabilną pozycję.\n" +
                "\n" +
                "Złap uchwyty maszyny lub chwyć się poręczy, aby utrzymać stabilność ciała.\n" +
                "\n" +
                "Wyprostuj nogi, aby zaczynały się równolegle do podłogi, z łydkami opartymi na rolkach.\n" +
                "\n" +
                "Zacznij zginanie nóg w stawach kolanowych, unosząc rolki maszyny w stronę pośladków. Skoncentruj się na pracy mięśni dwugłowych uda.\n" +
                "\n" +
                "Zatrzymaj się na chwilę na końcowym zgięciu, aby poczuć napięcie w mięśniach tylnych ud.\n" +
                "\n" +
                "Powoli wyprostuj nogi, aby powrócić do pozycji wyjściowej, kontrolując ruch.\n" +
                "\n" +
                "Powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania uginania nóg na maszynie leżąc, pamiętaj o utrzymaniu prawidłowej techniki wykonania. Utrzymuj kontrolowany ruch, utrzymując napięcie w mięśniach dwugłowych uda. Unikaj impulsywnego ruchu i wykorzystywania innych mięśni do pomocy. Skoncentruj się na precyzyjnym zginaniu nóg i kontrolowanym wyproście."
        val imageTitle6 = "Uginanie nóg na maszynie leżąc"

        imageView6.setOnClickListener {
            showImageDialog(imageResource6, imageDescription6, imageTitle6)
        }

        val imageView7 = findViewById<ImageView>(R.id.imageView7)
        val imageResource7 = R.drawable.martwy_ciag_na_prstych_nogach // Zasób obrazu
        val imageDescription7 = "Martwy ciąg na prostych nogach to jedno z najpopularniejszych ćwiczeń siłowych, które angażuje wiele mięśni, w tym mięśnie pleców, pośladków, ud, łydek i mięśnie rdzenia. Oto opis techniki wykonania tego ćwiczenia:\n" +
                "\n" +
                "Stań przed sztangą, umieszczoną na podłodze, w taki sposób, że twoje stopy są ustawione na szerokość barków.\n" +
                "\n" +
                "Chwyć sztangę, trzymając ją z szerokością chwytu trochę szerszą niż szerokość barków. Palce powinny być skierowane na zewnątrz.\n" +
                "\n" +
                "Skieruj wzrok przed siebie, utrzymując naturalną krzywiznę w dolnej części pleców i lekko uniesioną klatkę piersiową.\n" +
                "\n" +
                "Zacznij opuszczać ciało, zginając biodra i kolana, aby sięgnąć po sztangę. Upewnij się, że utrzymujesz stabilność pleców i utrzymujesz sztangę blisko ciała w trakcie opadania.\n" +
                "\n" +
                "Gdy sztanga znajdzie się na wysokości łydek lub nieco poniżej, wypchnij się mocno, prostując biodra i kolana. Skoncentruj się na pracy mięśni pleców, pośladków i ud.\n" +
                "\n" +
                "Zatrzymaj się na chwilę na końcowym wyproście, aby poczuć napięcie w mięśniach.\n" +
                "\n" +
                "Powoli opuść sztangę, kontrolując ruch, i powtórz ćwiczenie przez zaleconą liczbę powtórzeń.\n" +
                "\n" +
                "Podczas wykonywania martwego ciągu na prostych nogach, pamiętaj o utrzymaniu prawidłowej techniki wykonania."
        val imageTitle7 = "Martwy ciąg na lekko ugiętych nogach"

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