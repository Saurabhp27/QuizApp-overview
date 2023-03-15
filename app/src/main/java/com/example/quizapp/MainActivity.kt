package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var EditName : EditText? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ButtonStart : Button = findViewById(R.id.StartButton)
        EditName = findViewById(R.id.Editname)

        ButtonStart.setOnClickListener() {
            if (EditName?.text?.isEmpty() == true) {
                Toast.makeText(this, "Enter Your Name ", Toast.LENGTH_SHORT).show()
            } else {
                val Intent = Intent(this, QuizQuestionActivity::class.java)
                Intent.putExtra(Constants.User_name, EditName?.text.toString())
                startActivity(Intent)
            }
        }

    }

}