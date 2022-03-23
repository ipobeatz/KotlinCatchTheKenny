package com.ibrahimcakir.kotlincatchthekenny

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

var score = 0
var imageArray = ArrayList<ImageView>()
var runnable = Runnable { }
var handler = Handler(Looper.getMainLooper())


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById(R.id.imageView)
        imageArray.add(imageView)

        val imageView1: ImageView = findViewById(R.id.imageView1)
        imageArray.add(imageView1)

        val imageView2: ImageView = findViewById(R.id.imageView2)
        imageArray.add(imageView2)

        val imageView3: ImageView = findViewById(R.id.imageView3)
        imageArray.add(imageView3)

        val imageView4: ImageView = findViewById(R.id.imageView4)
        imageArray.add(imageView4)

        val imageView5: ImageView = findViewById(R.id.imageView5)
        imageArray.add(imageView5)

        val imageView6: ImageView = findViewById(R.id.imageView6)
        imageArray.add(imageView6)

        val imageView7: ImageView = findViewById(R.id.imageView7)
        imageArray.add(imageView7)

        val imageView8: ImageView = findViewById(R.id.imageView8)
        imageArray.add(imageView8)

        hideImages()



        object : CountDownTimer(15500, 1000) {
            override fun onFinish() {
                val timeText: TextView = findViewById(R.id.timeText)
                timeText.text = "Time : 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)

                alert.setTitle("Game Over")
                alert.setMessage("Restart the Game?")
                alert.setPositiveButton("Yes") { dialog, which ->
                    val intent = intent
                    finish()
                    startActivity(intent)

                }
                alert.setNegativeButton("no") { dialog, which ->
                    Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_LONG).show()
                }
                alert.show()
            }

            override fun onTick(p0: Long) {
                val timeText: TextView = findViewById(R.id.timeText)
                timeText.text = "Time: " + p0 / 1000
            }
        }.start()
    }

    fun hideImages() {
        runnable = object : Runnable {
            override fun run() {

                for (image in imageArray) {
                    image.visibility = View.INVISIBLE

                }
                val random = Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable, 500)

            }

        }
        handler.post(runnable)
    }


    fun increaseScore(view: View) {
        score = score + 1
        val scoreText: TextView = findViewById(R.id.scoreText)
        scoreText.text = "score: $score"

    }
}