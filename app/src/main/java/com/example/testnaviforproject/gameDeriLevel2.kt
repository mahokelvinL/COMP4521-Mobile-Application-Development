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

class gameDeriLevel2 : AppCompatActivity() {
    private var timer: CountDownTimer? = null
    private val initialTime: Long = 20000
    private var lifeCount: Int = 3
    private var currentQuestionIndex: Int = 0
    private val questions: List<Question> = listOf(
        Question("Q1: D(4x^3 - 3x^2 + 2)", listOf("12x^2 - 6x", "12x^3 - 6", "4x^2 - 6x + 2", "4x^2 - 6x"), 0),
        Question("Q2: D(x/(x-4)", listOf("x/(x-4)^2", "-4x/(x-4)", "(x^2-4)/(x-4)^2", "-4/(x-4)^2"), 3),
        Question("Q3: D(cot(x))", listOf("tan(x)", "-csc(x)", "sec^2(x)", "-csc^2(x)"), 3)
    )
    private lateinit var timerText: TextView
    private lateinit var lvText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.game_d_level2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gameDeriLevel2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lvText = findViewById<TextView>(R.id.tvLife)
        timerText = findViewById<TextView>(R.id.tvTimer)
        val optionD2Btn1 = findViewById<Button>(R.id.btnD2Op1)
        val optionD2Btn2 = findViewById<Button>(R.id.btnD2Op2)
        val optionD2Btn3 = findViewById<Button>(R.id.btnD2Op3)
        val optionD2Btn4 = findViewById<Button>(R.id.btnD2Op4)


        displayQuestion(currentQuestionIndex)

        optionD2Btn1.setOnClickListener {
            checkAnswer(0)
        }

        optionD2Btn2.setOnClickListener {
            checkAnswer(1)
        }

        optionD2Btn3.setOnClickListener {
            checkAnswer(2)
        }

        optionD2Btn4.setOnClickListener {
            checkAnswer(3)
        }
        startTimer()
        lvText.text = "Lives: $lifeCount"

    }
    private fun displayQuestion(index: Int) {
        val currentQuestion = questions[index]
        val questText = findViewById<TextView>(R.id.questDtv)
        val optionD2Btn1 = findViewById<Button>(R.id.btnD2Op1)
        val optionD2Btn2 = findViewById<Button>(R.id.btnD2Op2)
        val optionD2Btn3 = findViewById<Button>(R.id.btnD2Op3)
        val optionD2Btn4 = findViewById<Button>(R.id.btnD2Op4)

        questText.text = currentQuestion.questionText
        optionD2Btn1.text = currentQuestion.options[0]
        optionD2Btn2.text = currentQuestion.options[1]
        optionD2Btn3.text = currentQuestion.options[2]
        optionD2Btn4.text = currentQuestion.options[3]
    }

    private fun checkAnswer(selectedOptionIndex: Int) {
        val currentQuestion = questions[currentQuestionIndex]
        if (selectedOptionIndex == currentQuestion.correctOptionIndex) {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                displayQuestion(currentQuestionIndex)
            } else {
                val intent = Intent(this@gameDeriLevel2, lvlComplete::class.java)
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