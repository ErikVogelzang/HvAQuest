package com.example.hvaquest.UI


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.size
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hvaquest.Model.QuestionViewModel
import com.example.hvaquest.R
import kotlinx.android.synthetic.main.fragment_question.*





/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment : Fragment() {

    private val args: QuestionFragmentArgs by navArgs()
    private lateinit var questionViewModel: QuestionViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnConfirm.setOnClickListener {
            val selectedRadioButtonId = rg.checkedRadioButtonId
            val radioGroupUnchecked = -1
            if (selectedRadioButtonId != radioGroupUnchecked) {
                val selectedRadioButton = view.findViewById<RadioButton>(selectedRadioButtonId)
                val selectedRbText = selectedRadioButton.text.toString()
                if (selectedRbText == questionViewModel.getCorrectAnswer(args.questionNumber)) {
                    val action = QuestionFragmentDirections.actionQuestionFragmentToClueFragment(args.questionNumber)
                    findNavController().navigate(action)
                }
                else
                    rg.clearCheck()
            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        questionViewModel = ViewModelProviders.of(activity!!).get(QuestionViewModel::class.java)
        tvQuestion.text = questionViewModel.getQuestion(args.questionNumber)
        val answers = questionViewModel.getAnswers(args.questionNumber)
        val numOfAnswers = 3
        if (answers.size >= numOfAnswers) {
            rgAnswer1.text = answers[0]
            rgAnswer2.text = answers[1]
            rgAnswer3.text = answers[2]
        }
    }
}
