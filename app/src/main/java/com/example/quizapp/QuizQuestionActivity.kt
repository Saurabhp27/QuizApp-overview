package com.example.quizapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {


    private var mUsername : String? = null
    private var mcorrectanswers = 0

    private var questionlist: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int? = null
    var currentposition = 1

    private var btn_submit : Button? = null
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mUsername = intent.getStringExtra(Constants.User_name)


        btn_submit = findViewById(R.id.btn_submit)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)


        questionlist = Constants.getquestion()
        assigningdata()

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btn_submit?.setOnClickListener(this)

    }

    private fun assigningdata() {

        defaultview()
        val question: Question = questionlist!![currentposition - 1]

        progressBar?.progress =
            currentposition // Setting the current progress in the progressbar using the position of question
        tvProgress?.text =
            "$currentposition" + "/" + progressBar?.getMax() // Setting up the progress text

        // Now set the current question and the options in the UI
        tvQuestion?.text = question.question
        ivImage?.setImageResource(question.image)
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionthree



    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultview()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizQuestionActivity,
            R.drawable.selected_option_border_bg
        )
    }


    private fun defaultview() {
        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0, it)
        }
        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuizQuestionActivity,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {

            R.id.tv_option_one -> {
                tvOptionOne?.let {
                    selectedOptionView(it, 1)
                }

            }

            R.id.tv_option_two -> {
                tvOptionTwo?.let {
                    selectedOptionView(it, 2)
                }

            }

            R.id.tv_option_three -> {
                tvOptionThree?.let {
                    selectedOptionView(it, 3)
                }

            }


            R.id.tv_option_four -> {
                tvOptionFour?.let {
                    selectedOptionView(it, 4)
                }

            }

            R.id.btn_submit ->
            {
                if (mSelectedOptionPosition== 0){
                    currentposition++

                    when {
                        currentposition <= questionlist!!.size -> {
                            assigningdata()
                        }
                        else -> {
                           val i = Intent(this, ResultActivity:: class.java)
                            i.putExtra(Constants.User_name, mUsername)
                            i.putExtra(Constants.correctAnswers, mcorrectanswers)
                            i.putExtra(Constants.Total_questions, questionlist?.size)
                            startActivity(i)
                            finish()
                        }

                    }
                }
                else{
                    val question: Question = questionlist!![currentposition - 1]
                    if (mSelectedOptionPosition != question!!.Correctoption){
                        answerview(mSelectedOptionPosition!!, R.drawable.wrong_option_border_bg)
                    }else{
                        mcorrectanswers++
                    }
                    answerview(question.Correctoption, R.drawable.correct_option_border_bg)
                }

                if (currentposition == questionlist!!.size){
                    btn_submit?.text = "Finish"
                }
                else {
                    btn_submit?.text = "Next Question"
                }

                mSelectedOptionPosition = 0
            }
        }
    }


    private fun answerview ( answer : Int , drawableview : Int){
        when (answer){
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(this , drawableview)
            }
            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(this , drawableview)
            }
            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(this , drawableview)
            }
        }
    }
}