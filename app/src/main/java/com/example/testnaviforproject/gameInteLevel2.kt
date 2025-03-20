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

class gameInteLevel2 : AppCompatActivity() {
    private var timer: CountDownTimer? = null
    private val initialTime: Long = 20000
    private var lifeCount: Int = 3
    private var currentQuestionIndex: Int = 0
    private val questions: List<Question> = listOf(
        Question("Q1: I(csc^2(x))", listOf("1/sin(x) +C", "1/sin^ 2(x) +C", "-cot(x) +C", "-cot(x)"), 2),
        Question("Q2: I(-sin(x)", listOf("cos(x) +C", "sin^2 (x)+C", "sin^2(x) /2 + C", "2sin^2(x)/ sqrt(2) +C"), 0),
        Question("Q3: I(7cos(5x)", listOf("7sin(5x) +C", "7sin(5x)/5 +C", "-7sin(x) /5 +C", "35cos(5x) +C"), 1)
    )
    private lateinit var timerText: TextView
    private lateinit var lvText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.game_i_level2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gameInteLevel2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lvText = findViewById<TextView>(R.id.tvLife)
        timerText = findViewById<TextView>(R.id.tvTimer)
        val optionI2Btn1 = findViewById<Button>(R.id.btnI2Op1)
        val optionI2Btn2 = findViewById<Button>(R.id.btnI2Op2)
        val optionI2Btn3 = findViewById<Button>(R.id.btnI2Op3)
        val optionI2Btn4 = findViewById<Button>(R.id.btnI2Op4)


        displayQuestion(currentQuestionIndex)

        optionI2Btn1.setOnClickListener {
            checkAnswer(0)
        }

        optionI2Btn2.setOnClickListener {
            checkAnswer(1)
        }

        optionI2Btn3.setOnClickListener {
            checkAnswer(2)
        }

        optionI2Btn4.setOnClickListener {
            checkAnswer(3)
        }
        startTimer()
        lvText.text = "Lives: $lifeCount"

    }
    private fun displayQuestion(index: Int) {
        val currentQuestion = questions[index]
        val questText = findViewById<TextView>(R.id.questDtv)
        val optionI2Btn1 = findViewById<Button>(R.id.btnI2Op1)
        val optionI2Btn2 = findViewById<Button>(R.id.btnI2Op2)
        val optionI2Btn3 = findViewById<Button>(R.id.btnI2Op3)
        val optionI2Btn4 = findViewById<Button>(R.id.btnI2Op4)

        questText.text = currentQuestion.questionText
        optionI2Btn1.text = currentQuestion.options[0]
        optionI2Btn2.text = currentQuestion.options[1]
        optionI2Btn3.text = currentQuestion.options[2]
        optionI2Btn4.text = currentQuestion.options[3]
    }

    private fun checkAnswer(selectedOptionIndex: Int) {
        val currentQuestion = questions[currentQuestionIndex]
        if (selectedOptionIndex == currentQuestion.correctOptionIndex) {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                displayQuestion(currentQuestionIndex)
            } else {
                val intent = Intent(this@gameInteLevel2, lvlComplete::class.java)
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