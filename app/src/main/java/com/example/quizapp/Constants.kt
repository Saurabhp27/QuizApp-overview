package com.example.quizapp

object Constants {

    const val User_name = "username"
    const val Total_questions = "1"
    const val correctAnswers = "2"

    fun getquestion() : ArrayList<Question>{

        val questionslist = ArrayList<Question>()

        val que1 = Question(1, "which is the above color", R.mipmap.ic_launcher, "red", "yellow ",
          "green", 3)

        questionslist.add(que1)

        val que2 = Question(1, "which is the above color", R.mipmap.ic_launcher, "red", "yellow ",
            "green", 2)

        questionslist.add(que2)
        val que3 = Question(1, "which is the above color", R.mipmap.ic_launcher, "red", "yellow ",
            "green", 1)

        questionslist.add(que3)



        return questionslist
    }
}