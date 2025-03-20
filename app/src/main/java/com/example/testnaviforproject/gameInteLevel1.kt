package com.example.testnaviforproject

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class gameInteLevel1 : AppCompatActivity() {
    private var timer: CountDownTimer? = null
    private val initialTime: Long = 20000
    private var lifeCount: Int = 3
    private var currentQuestionIndex: Int = 0
    private val questions: List<Question> = listOf(
        Question("Q1: I(28)", listOf("28x", "28x +C", "0 +C", "28x^2 +C"), 1),
        Question("Q2: I(3e^x)", listOf("3e^2x +C", "3e^x +C", "4e^(3x)/4 +C", "3e^x /4 +C"), 1),
        Question("Q3: I(x^5)", listOf("x^6 /6 +C", "x^6/ 5 +C", "6x^6 +C", "x^5 /5 +C"), 0)
    )
    private lateinit var timerText: TextView
    private lateinit var lvText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.game_i_level1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gameInteLevel1)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lvText = findViewById<TextView>(R.id.tvLife)
        timerText = findViewById<TextView>(R.id.tvTimer)
        val optionI1Btn1 = findViewById<Button>(R.id.btnI1Op1)
        val optionI1Btn2 = findViewById<Button>(R.id.btnI1Op2)
        val optionI1Btn3 = findViewById<Button>(R.id.btnI1Op3)
        val optionI1Btn4 = findViewById<Button>(R.id.btnI1Op4)


        displayQuestion(currentQuestionIndex)

        optionI1Btn1.setOnClickListener {
            checkAnswer(0)
        }

        optionI1Btn2.setOnClickListener {
            checkAnswer(1)
        }

        optionI1Btn3.setOnClickListener {
            checkAnswer(2)
        }

        optionI1Btn4.setOnClickListener {
            checkAnswer(3)
        }
        startTimer()
        lvText.text = "Lives: $lifeCount"

    }
    private fun displayQuestion(index: Int) {
        val currentQuestion = questions[index]
        val questText = findViewById<TextView>(R.id.questDtv)
        val optionI1Btn1 = findViewById<Button>(R.id.btnI1Op1)
        val optionI1Btn2 = findViewById<Button>(R.id.btnI1Op2)
        val optionI1Btn3 = findViewById<Button>(R.id.btnI1Op3)
        val optionI1Btn4 = findViewById<Button>(R.id.btnI1Op4)

        questText.text = currentQuestion.questionText
        optionI1Btn1.text = currentQuestion.options[0]
        optionI1Btn2.text = currentQuestion.options[1]
        optionI1Btn3.text = currentQuestion.options[2]
        optionI1Btn4.text = currentQuestion.options[3]
    }

    private fun checkAnswer(selectedOptionIndex: Int) {
        val currentQuestion = questions[currentQuestionIndex]
        if (selectedOptionIndex == currentQuestion.correctOptionIndex) {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                displayQuestion(currentQuestionIndex)
            } else {
                val intent = Intent(this@gameInteLevel1, lvlComplete::class.java)
                intent.putExtra("lifeCount", lifeCount)
                intent.putExtra("timeLeft", timerText.text.toString())
                startActivity(intent)
                finish()
            }
        } else {
            decrementLifeCount()
        }
    }
    private fun startTimer() {
        timer = object : CountDownTimer(initialTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                timerText.text = "Time: $seconds seconds"
            }

            override fun onFinish() {
                goToMainActivity()
            }
        }

        timer?.start()
    }

    private fun decrementLifeCount() {
        lifeCount--
        lvText.text = "Lives: $lifeCount"


        if (lifeCount <= 0) {
            goToMainActivity()
        }
    }
    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    data class Question(val questionText: String, val options: List<String>, val correctOptionIndex: Int)
}