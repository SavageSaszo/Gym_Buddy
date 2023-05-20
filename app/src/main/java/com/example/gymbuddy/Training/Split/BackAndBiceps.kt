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
        val imageDescription1 = ""
        val imageTitle1 = "Martwy ciąg"

        imageView1.setOnClickListener {
            showImageDialog(imageResource1, imageDescription1, imageTitle1)
        }

        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageResource2 = R.drawable.wioslowanie_sztanga // Zasób obrazu
        val imageDescription2 = ""
        val imageTitle2 = "Pendaly row"

        imageView2.setOnClickListener {
            showImageDialog(imageResource2, imageDescription2, imageTitle2)
        }

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageResource3 = R.drawable.podciaganie_nachwytem // Zasób obrazu
        val imageDescription3 = ""
        val imageTitle3 = "Podciaganie nachwytem"

        imageView3.setOnClickListener {
            showImageDialog(imageResource3, imageDescription3, imageTitle3)
        }

        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageResource4 = R.drawable.martwy_ciag // Zasób obrazu
        val imageDescription4 = ""
        val imageTitle4 = "Martwy ciąg"

        imageView4.setOnClickListener {
            showImageDialog(imageResource4, imageDescription4, imageTitle4)
        }

        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageResource5 = R.drawable.martwy_ciag // Zasób obrazu
        val imageDescription5 = ""
        val imageTitle5 = "Martwy ciąg"

        imageView5.setOnClickListener {
            showImageDialog(imageResource5, imageDescription5, imageTitle5)
        }

        val imageView6 = findViewById<ImageView>(R.id.imageView6)
        val imageResource6 = R.drawable.martwy_ciag // Zasób obrazu
        val imageDescription6 = ""
        val imageTitle6 = "Martwy ciąg"

        imageView6.setOnClickListener {
            showImageDialog(imageResource6, imageDescription6, imageTitle6)
        }

        val imageView7 = findViewById<ImageView>(R.id.imageView7)
        val imageResource7 = R.drawable.martwy_ciag // Zasób obrazu
        val imageDescription7 = ""
        val imageTitle7 = "Martwy ciąg"

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