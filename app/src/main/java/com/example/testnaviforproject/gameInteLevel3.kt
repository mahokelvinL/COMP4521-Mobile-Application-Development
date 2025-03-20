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

class gameInteLevel3 : AppCompatActivity() {
    private var timer: CountDownTimer? = null
    private val initialTime: Long = 20000
    private var lifeCount: Int = 3
    private var currentQuestionIndex: Int = 0
    private val questions: List<Question> = listOf(
        Question("Q1: I(-5cos(pi x))", listOf("-5sin(pi x) /pi +C", "-5sin(pi x) /pi", "5sin(x)/pi +C", "5picos(x) +C"), 0),
        Question("Q2: I(9e^(x/4))", listOf("36e^(x/4)", "9e^(1/4)/4 +C", "9e^(x/4) /4 +C", "36e^(x/4) +C"), 3),
        Question("Q3: I(sec(x)tan(x))", listOf("1- cos^2(x) +C", "1 - tan^2(x) +C", "sec(x) +C", "cot(x) +C"), 2)
    )
    private lateinit var timerText: TextView
    private lateinit var lvText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.game_i_level3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gameInteLevel3)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lvText = findViewById<TextView>(R.id.tvLife)
        timerText = findViewById<TextView>(R.id.tvTimer)
        val optionI3Btn1 = findViewById<Button>(R.id.btnI3Op1)
        val optionI3Btn2 = findViewById<Button>(R.id.btnI3Op2)
        val optionI3Btn3 = findViewById<Button>(R.id.btnI3Op3)
        val optionI3Btn4 = findViewById<Button>(R.id.btnI3Op4)


        displayQuestion(currentQuestionIndex)

        optionI3Btn1.setOnClickListener {
            checkAnswer(0)
        }

        optionI3Btn2.setOnClickListener {
            checkAnswer(1)
        }

        optionI3Btn3.setOnClickListener {
            checkAnswer(2)
        }

        optionI3Btn4.setOnClickListener {
            checkAnswer(3)
        }
        startTimer()
        lvText.text = "Lives: $lifeCount"

    }
    private fun displayQuestion(index: Int) {
        val currentQuestion = questions[index]
        val questText = findViewById<TextView>(R.id.questDtv)
        val optionI3Btn1 = findViewById<Button>(R.id.btnI3Op1)
        val optionI3Btn2 = findViewById<Button>(R.id.btnI3Op2)
        val optionI3Btn3 = findViewById<Button>(R.id.btnI3Op3)
        val optionI3Btn4 = findViewById<Button>(R.id.btnI3Op4)

        questText.text = currentQuestion.questionText
        optionI3Btn1.text = currentQuestion.options[0]
        optionI3Btn2.text = currentQuestion.options[1]
        optionI3Btn3.text = currentQuestion.options[2]
        optionI3Btn4.text = currentQuestion.options[3]
    }

    private fun checkAnswer(selectedOptionIndex: Int) {
        val currentQuestion = questions[currentQuestionIndex]
        if (selectedOptionIndex == currentQuestion.correctOptionIndex) {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                displayQuestion(currentQuestionIndex)
            } else {
                val intent = Intent(this@gameInteLevel3, lvlComplete::class.java)
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