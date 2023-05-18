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
        val imageDescription1 = "Stań prosto, stopy lekko szerzej niż szerokość barków." +
                " Umieść sztangę na podporach sztangi na wysokości klatki piersiowej." +
                " Chwyć sztangę nieco szerszym niż szerokość barków uchwytem." +
                " Umieść ją na górnej części mięśni karku i barkach." +
                " Wdychając głęboko, unieś sztangę, prostując nogi i biodra." +
                " Trzymaj klatkę piersiową wyprostowaną i skieruj wzrok przed siebie." +
                " Opadaj, zginając kolana i biodra, jakbyś siadał na wyimaginowanym krześle." +
                " Pamiętaj, aby utrzymać kolana nad linią stóp." +
                " Kontroluj ruch, aż uda będą równoległe do podłoża lub nieco niżej." +
                " Napięciem mięśni ud i pośladków, wróć do pozycji wyjściowej, unosząc sztangę."
        val imageTitle1 = "Przysiady ze sztangą z przodu"

        imageView1.setOnClickListener {
            showImageDialog(imageResource1, imageDescription1, imageTitle1)
        }

        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageResource2 = R.drawable.wyciskanie_lawka_plaska // Zasób obrazu
        val imageDescription2 = "Połóż się na ławce płaskiej, trzymając sztangę na szerokość barków." +
                " Umieść stopy płasko na podłodze, mocno zakotwiczając je dla stabilności." +
                " Chwyć sztangę uchwytem nachwytem, ręce nieco szersze niż szerokość barków." +
                " Wdychając powoli, opuść sztangę, kontrolując ruch," +
                " aż dotknie ona delikatnie klatki piersiowej. Następnie wydychając," +
                " wypchnij sztangę do góry, prostując ramiona. Pamiętaj o utrzymaniu" +
                " stabilnej pozycji ciała, napięciu mięśni brzucha i napięciu mięśni klatki" +
                " piersiowej. Unosząc sztangę, skup się na wyciskaniu siłą mięśni klatki" +
                " piersiowej, a nie na prostowaniu łokci. Powoli opuść sztangę z powrotem" +
                " do pozycji wyjściowej, kontrolując ruch i zapewniając kontrolowane opadanie."
        val imageTitle2 = "Wyciskanie na ławce płaskiej"

        imageView2.setOnClickListener {
            showImageDialog(imageResource2, imageDescription2, imageTitle2)
        }

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageResource3 = R.drawable.martwy_ciag // Zasób obrazu
        val imageDescription3 = "Stań przed sztangą, stopy ustaw szerzej niż szerokość barków." +
                " Chwyć sztangę uchwytem nachwytem, ręce trochę szerzej niż szerokość barków." +
                " Upewnij się, że palce są skierowane na zewnątrz. Zegnij kolana i biodra," +
                " schylając się, aby złapać sztangę. Trzymając plecy proste, unieś sztangę," +
                " prostując nogi i biodra. Pamiętaj, aby utrzymać sztangę blisko ciała i skupić" +
                " się na napięciu mięśni pleców i nogi. W górnej pozycji, gdy ciało jest" +
                " wyprostowane, zatrzymaj się na chwilę, a następnie powoli opuść sztangę," +
                " kontrolując ruch. Powtórz ruch, unosząc i opuszczając sztangę, utrzymując" +
                " prawidłową technikę i kontrolę przez całe ćwiczenie. Pamiętaj, aby oddychać" +
                " równomiernie i nie napinać nadmiernie kręgosłupa."
        val imageTitle3 = "Martwy ciąg"

        imageView3.setOnClickListener {
            showImageDialog(imageResource3, imageDescription3, imageTitle3)
        }

        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageResource4 = R.drawable.wioslowanie_sztanga // Zasób obrazu
        val imageDescription4 = "Stań prosto, stopy lekko szerzej niż szerokość barków." +
                " Trzymaj sztangę na wyciągniętych ramionach, uchwytem nachwytem," +
                " nieco szerszym niż szerokość barków. Wdychając powoli," +
                " zegnij kolana nieznacznie i nachyl się w przód, utrzymując prostą pozycję pleców." +
                " Zaczynając od dolnego odcinka pleców, unieś sztangę, wciągając łopatki do siebie." +
                " Skoncentruj się na napięciu mięśni pleców i zaciskaniu ich podczas" +
                " unoszenia sztangi. W górnej pozycji, gdy sztanga znajduje się blisko dolnej" +
                " części klatki piersiowej, zatrzymaj się na chwilę, a następnie powoli" +
                " opuść sztangę, kontrolując ruch. Powtórz ruch, unosząc i opuszczając sztangę," +
                " utrzymując prawidłową technikę i kontrolę przez całe ćwiczenie." +
                " Pamiętaj o równomiernym oddychaniu i utrzymaniu stabilnej pozycji ciała."
        val imageTitle4 = "Wiosłowanie sztangą w opadzie"

        imageView4.setOnClickListener {
            showImageDialog(imageResource4, imageDescription4, imageTitle4)
        }

        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageResource5 = R.drawable.wykroki_bulgarskie // Zasób obrazu
        val imageDescription5 = "Stań w pozycji, mając jedną stopę opartą na platformie za sobą," +
                " a drugą stopę ustawioną około dwóch długości nóg przed sobą. Trzymaj sztangę" +
                " na wysokości klatki piersiowej, trzymając ją na szerokość barków. Wdychając" +
                " powoli, zegnij kolano przednią nogą, opuszczając ciało w dół. Upewnij się," +
                " że kolano przedniej nogi nie wychodzi poza linię palców stopy. Unikaj " +
                "przesuwania ciężaru ciała do przodu, utrzymując równowagę. Główny nacisk" +
                " powinien być skierowany na pracę mięśni nóg, szczególnie na mięśnie czworogłowe" +
                " uda. W górnej pozycji, gdy przednia noga jest wyprostowana, zatrzymaj się" +
                " na chwilę, a następnie powoli opuść ciało, kontrolując ruch. Powtórz ruch," +
                " zginając i prostując kolano przednią nogą, utrzymując prawidłową technikę i" +
                " kontrolę przez całe ćwiczenie. Pamiętaj o równomiernym oddychaniu i utrzymaniu" +
                " stabilnej pozycji ciała."
        val imageTitle5 = "Przysiady bułgarskie"

        imageView5.setOnClickListener {
            showImageDialog(imageResource5, imageDescription5, imageTitle5)
        }

        val imageView6 = findViewById<ImageView>(R.id.imageView6)
        val imageResource6 = R.drawable.plank_boczny // Zasób obrazu
        val imageDescription6 = "Połóż się na boku, podpierając się na przedramieniu," +
                " ustawionym pod ramieniem, tak aby łokieć znajdował się pod linią barków." +
                " Nogi powinny być wyprostowane i złączone. Utwórz linię prostą od głowy do stóp," +
                " napinając mięśnie brzucha i pośladków. Unieś biodra, utrzymując napięcie" +
                " w mięśniach korpusu. Trzymaj tę pozycję, skupiając się na utrzymaniu stabilności" +
                " i napinaniu mięśni bocznych brzucha. Pamiętaj o równomiernym oddychaniu i" +
                " utrzymaniu prawidłowej postawy ciała przez cały czas trwania ćwiczenia." +
                " Wytrzymaj w tej pozycji przez określony czas lub dopóki możesz utrzymać" +
                " prawidłową formę. Następnie powtórz na drugim boku. Kontynuuj trening," +
                " naprzemiennie pracując po obu stronach."
        val imageTitle6 = "Plank boczny"

        imageView6.setOnClickListener {
            showImageDialog(imageResource6, imageDescription6, imageTitle6)
        }

        val imageView7 = findViewById<ImageView>(R.id.imageView7)
        val imageResource7 = R.drawable.spacer_farmera // Zasób obrazu
        val imageDescription7 = "Spacer farmera hantlem jednorącz to efektywne ćwiczenie siłowe" +
                " angażujące wiele mięśni. Weź hantel jednorącz, trzymając go w jednej ręce." +
                " Wyprostuj plecy, utrzymując naturalną krzywiznę kręgosłupa. Rozpocznij" +
                " spacer, wykonując kroki i jednocześnie unosząc hantel do góry. Skup się" +
                " na utrzymaniu stabilności i równowagi. To ćwiczenie wzmacnia ramiona," +
                " barki, mięśnie rdzenia i dolną część ciała. Dostosuj obciążenie do swojej" +
                " kondycji fizycznej i wykonuj regularnie, by osiągnąć najlepsze rezultaty."
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