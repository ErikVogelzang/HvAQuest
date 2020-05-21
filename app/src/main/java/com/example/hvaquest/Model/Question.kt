package com.example.hvaquest.Model

data class Question(
    val question: String,
    val choices: Array<String>,
    val correctAnswer: String,
    val clue: Int
)
