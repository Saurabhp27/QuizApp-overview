package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val mUsername = intent.getStringExtra(Constants.User_name)
        val mTotalquestion = intent.getIntExtra(Constants.Total_questions,0)
        val mCorrectanswers = intent.getIntExtra(Constants.correctAnswers,0)

        var name :TextView= findViewById(R.id.tv_name)
        var score :TextView= findViewById(R.id.tv_score)
        var finish : Button= findViewById(R.id.btn_finish)

        name.text = mUsername
        score.text = "Total score is $mCorrectanswers out of $mTotalquestion"

        finish.setOnClickListener(){
            val intent = Intent(this , MainActivity:: class.java)
            startActivity(intent)
            finish()
        }
    }
}