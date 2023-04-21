package com.profjpbaugh.navigationdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.profjpbaugh.navigationdemo.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    private lateinit var binding : FragmentScoreBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun changeData(startTimes : ArrayList<Double>,
                   endTimes : ArrayList<Double>, totalTimes : ArrayList<Double>) {
        var totalScore = 0.0
        for (t in totalTimes) {
            totalScore += t
        }
        binding.totalScore.text = totalScore.toString()

        binding.row1Start.text = startTimes[0].toString()
        binding.row1End.text = endTimes[0].toString()
        binding.row1Tot.text = totalTimes[0].toString()

        binding.row2Start.text = startTimes[1].toString()
        binding.row2End.text = endTimes[1].toString()
        binding.row2Tot.text = totalTimes[1].toString()

        binding.row3Start.text = startTimes[2].toString()
        binding.row3End.text = endTimes[2].toString()
        binding.row3Tot.text = totalTimes[2].toString()

        binding.row4Start.text = startTimes[3].toString()
        binding.row4End.text = endTimes[3].toString()
        binding.row4Tot.text = totalTimes[3].toString()

        binding.row5Start.text = startTimes[4].toString()
        binding.row5End.text = endTimes[4].toString()
        binding.row5Tot.text = totalTimes[4].toString()
    }


}