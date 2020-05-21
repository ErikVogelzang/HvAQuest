package com.example.hvaquest.Model

import androidx.lifecycle.ViewModel
import com.example.hvaquest.Data.QuestRepository

class QuestionViewModel : ViewModel() {
    private val questionRepository = QuestRepository()
    private val questions = questionRepository.getHvaQuest()

    private fun getQuestionData(number: Int) : Question {
        val emptyQuestion = Question("", arrayOf("","",""), "", 0)
        if (questions == null)
            return emptyQuestion
        if (number < 0 || number >= questions.size)
            return emptyQuestion
        else
            return questions[number]
    }

    fun getQuestion(number: Int) : String {
        return getQuestionData(number).question
    }
    fun getAnswers(number: Int) : Array<String> {
        return getQuestionData(number).choices
    }
    fun getCorrectAnswer(number: Int) : String {
        return getQuestionData(number).correctAnswer
    }
    fun getClueImageId(number: Int) : Int {
        return getQuestionData(number).clue
    }
    fun getQuizLength() : Int {
        return questions.size
    }
}