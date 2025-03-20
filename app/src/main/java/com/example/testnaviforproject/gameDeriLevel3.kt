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

class gameDeriLevel3 : AppCompatActivity() {
    private var timer: CountDownTimer? = null
    private val initialTime: Long = 20000
    private var lifeCount: Int = 3
    private var currentQuestionIndex: Int = 0
    private val questions: List<Question> = listOf(
        Question("Q1: D(x^2 e^x)", listOf("2xe^x", "e^x + x^2e^x", "2x e^x + x^2 e^x", "2x^2 e^x + e^x x^2"), 2),
        Question("Q2: D(2cos(x)-6sec(x))", listOf("-2sin(x) - 6sec(x)tan(x)", "-2cos(x) - 6tan^2(x)", "2sec(x)tan(x) - sec^2(x)", "2csc^2(x) - 6cos(x) + 3"), 0),
        Question("Q3: D(x^2 cot(x))", listOf("2xtan(x)", "2xcot(x)-x^2csc^2(x)", "2xcsc^2(x)", "2xcsc^2(x) + x^2cot(x)"), 1)
    )
    private lateinit var timerText: TextView
    private lateinit var lvText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.game_d_level3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gameDeriLevel3)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lvText = findViewById<TextView>(R.id.tvLife)
        timerText = findViewById<TextView>(R.id.tvTimer)
        val optionD3Btn1 = findViewById<Button>(R.id.btnD3Op1)
        val optionD3Btn2 = findViewById<Button>(R.id.btnD3Op2)
        val optionD3Btn3 = findViewById<Button>(R.id.btnD3Op3)
        val optionD3Btn4 = findViewById<Button>(R.id.btnD3Op4)


        displayQuestion(currentQuestionIndex)

        optionD3Btn1.setOnClickListener {
            checkAnswer(0)
        }

        optionD3Btn2.setOnClickListener {
            checkAnswer(1)
        }

        optionD3Btn3.setOnClickListener {
            checkAnswer(2)
        }

        optionD3Btn4.setOnClickListener {
            checkAnswer(3)
        }
        startTimer()
        lvText.text = "Lives: $lifeCount"

    }
    private fun displayQuestion(index: Int) {
        val currentQuestion = questions[index]
        val questText = findViewById<TextView>(R.id.questDtv)
        val optionD3Btn1 = findViewById<Button>(R.id.btnD3Op1)
        val optionD3Btn2 = findViewById<Button>(R.id.btnD3Op2)
        val optionD3Btn3 = findViewById<Button>(R.id.btnD3Op3)
        val optionD3Btn4 = findViewById<Button>(R.id.btnD3Op4)

        questText.text = currentQuestion.questionText
        optionD3Btn1.text = currentQuestion.options[0]
        optionD3Btn2.text = currentQuestion.options[1]
        optionD3Btn3.text = currentQuestion.options[2]
        optionD3Btn4.text = currentQuestion.options[3]
    }

    private fun checkAnswer(selectedOptionIndex: Int) {
        val currentQuestion = questions[currentQuestionIndex]
        if (selectedOptionIndex == currentQuestion.correctOptionIndex) {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                displayQuestion(currentQuestionIndex)
            } else {
                val intent = Intent(this@gameDeriLevel3, lvlComplete::class.java)
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