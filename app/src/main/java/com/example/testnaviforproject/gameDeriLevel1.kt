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


class gameDeriLevel1 : AppCompatActivity() {
    private var timer: CountDownTimer? = null
    private val initialTime: Long = 20000
    private var lifeCount: Int = 3
    private var currentQuestionIndex: Int = 0
    private val questions: List<Question> = listOf(
        Question("Q1: D(x^100)", listOf("1/x^(100)", "100x^99", "100x", "99x^100"), 2),
        Question("Q2: D(pi x^(sqrt(2))", listOf("pi x^(sqrt(2)-1)", "pi sqrt(2) x^(sqrt(2)-1)", "sqrt(2)x/ pi", "pi x^sqrt(1/3)/sqrt(2)"), 1),
        Question("Q3: D(7x^3+6x^2+10x)", listOf("14x^2 + 6x", "7x^2 + 6x + 10", "21x^3 + 12x^2 + 10", "21x^2 + 12x +10"), 3)
    )
    private lateinit var timerText: TextView
    private lateinit var lvText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.game_d_level1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gameDeriLevel1)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lvText = findViewById<TextView>(R.id.tvLife)
        timerText = findViewById<TextView>(R.id.tvTimer)
        val optionD1Btn1 = findViewById<Button>(R.id.btnD1Op1)
        val optionD1Btn2 = findViewById<Button>(R.id.btnD1Op2)
        val optionD1Btn3 = findViewById<Button>(R.id.btnD1Op3)
        val optionD1Btn4 = findViewById<Button>(R.id.btnD1Op4)


        displayQuestion(currentQuestionIndex)

        optionD1Btn1.setOnClickListener {
            checkAnswer(0)
        }

        optionD1Btn2.setOnClickListener {
            checkAnswer(1)
        }

        optionD1Btn3.setOnClickListener {
            checkAnswer(2)
        }

        optionD1Btn4.setOnClickListener {
            checkAnswer(3)
        }
        startTimer()
        lvText.text = "Lives: $lifeCount"

    }
    private fun displayQuestion(index: Int) {
        val currentQuestion = questions[index]
        val questText = findViewById<TextView>(R.id.questDtv)
        val optionD1Btn1 = findViewById<Button>(R.id.btnD1Op1)
        val optionD1Btn2 = findViewById<Button>(R.id.btnD1Op2)
        val optionD1Btn3 = findViewById<Button>(R.id.btnD1Op3)
        val optionD1Btn4 = findViewById<Button>(R.id.btnD1Op4)

        questText.text = currentQuestion.questionText
        optionD1Btn1.text = currentQuestion.options[0]
        optionD1Btn2.text = currentQuestion.options[1]
        optionD1Btn3.text = currentQuestion.options[2]
        optionD1Btn4.text = currentQuestion.options[3]
    }

    private fun checkAnswer(selectedOptionIndex: Int) {
        val currentQuestion = questions[currentQuestionIndex]
        if (selectedOptionIndex == currentQuestion.correctOptionIndex) {
            if (currentQuestionIndex < questions.size - 1) {
                // Proceed to the next question
                currentQuestionIndex++
                displayQuestion(currentQuestionIndex)
            } else {
                val intent = Intent(this@gameDeriLevel1, lvlComplete::class.java)
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