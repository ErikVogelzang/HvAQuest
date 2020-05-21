package com.example.hvaquest.UI


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hvaquest.Model.QuestionViewModel
import com.example.hvaquest.R
import kotlinx.android.synthetic.main.fragment_clue.*
import kotlinx.android.synthetic.main.fragment_question.*

/**
 * A simple [Fragment] subclass.
 */
class ClueFragment : Fragment() {

    private val args: ClueFragmentArgs by navArgs()
    private lateinit var questionViewModel: QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clue, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        questionViewModel = ViewModelProviders.of(activity!!).get(QuestionViewModel::class.java)
        ivClue.setImageResource(questionViewModel.getClueImageId(args.questionNumber))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnNext.setOnClickListener {
            val nextQuestion = args.questionNumber + 1
            var action: NavDirections
            if (nextQuestion < questionViewModel.getQuizLength())
                action = ClueFragmentDirections.actionClueFragmentToQuestionFragment(nextQuestion)
            else
                action = ClueFragmentDirections.actionClueFragmentToCompletedFragment()
            findNavController().navigate(action)
        }
    }
}
