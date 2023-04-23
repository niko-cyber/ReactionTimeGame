package com.profjpbaugh.navigationdemo

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.profjpbaugh.navigationdemo.databinding.FragmentSecondBinding
import com.profjpbaugh.navigationdemo.ui.main.MainFragmentDirections


/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {

    var starts = ArrayList<Float>()
    var ends = ArrayList<Float>()
    var totals = ArrayList<Float>()

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri : Uri)
    }

    private lateinit var binding : FragmentSecondBinding



    override fun onStart() {
        super.onStart()

        arguments?.let {
            val args = SecondFragmentArgs.fromBundle(it)
        }


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.backButton.setOnClickListener {

            val action = SecondFragmentDirections.secondToMain()


            // action.to(MainFragmentDirections.mainToSecond())

            Navigation.findNavController(it).navigate(action)
        }
        arguments?.let {
            val args = SecondFragmentArgs.fromBundle(it)
            starts = args.startTimes.toList() as ArrayList<Float>
            ends = args.endTimes.toList() as ArrayList<Float>
            totals = args.totalTimes.toList() as ArrayList<Float>
            updateData(starts, ends, totals)
        }

        return binding.root
    }

    fun updateData(startTimes : ArrayList<Float>,
                   endTimes : ArrayList<Float>, totalTimes : ArrayList<Float>) {
        Log.i("SecondFragment", startTimes.toString())
        Log.i("SecondFragment", endTimes.toString())
        Log.i("SecondFragment", totalTimes.toString())
        var totalScore = 0.0
        for (t in totalTimes) {
            totalScore += t
        }
        binding.totalScore.text = "Total: " + String.format("%.2f", totalScore) + "s"

        binding.row1Start.text = String.format("%.2f", startTimes[0])
        binding.row1End.text = String.format("%.2f", endTimes[0])
        binding.row1Tot.text = String.format("%.2f", totalTimes[0])

        binding.row2Start.text = String.format("%.2f", startTimes[1])
        binding.row2End.text = String.format("%.2f", endTimes[1])
        binding.row2Tot.text = String.format("%.2f", totalTimes[1])

        binding.row3Start.text = String.format("%.2f", startTimes[2])
        binding.row3End.text = String.format("%.2f", endTimes[2])
        binding.row3Tot.text = String.format("%.2f", totalTimes[2])

        binding.row4Start.text = String.format("%.2f", startTimes[3])
        binding.row4End.text = String.format("%.2f", endTimes[3])
        binding.row4Tot.text = String.format("%.2f", totalTimes[3])

        binding.row5Start.text = String.format("%.2f", startTimes[4])
        binding.row5End.text = String.format("%.2f", endTimes[4])
        binding.row5Tot.text = String.format("%.2f", totalTimes[4])
    }


}