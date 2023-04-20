package com.profjpbaugh.navigationdemo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.profjpbaugh.navigationdemo.databinding.FragmentRestartBinding


class RestartFragment : Fragment() {

    private lateinit var binding : FragmentRestartBinding

    var activityCallback : RestartFragment.ButtonListener? = null

    interface ButtonListener {
        fun updateData(startTimes : ArrayList<Double>,
                       endTimes : ArrayList<Double>, totalTimes : ArrayList<Double>)
    }

    override fun onAttach(context : Context) {
        super.onAttach(context)

        try {
            activityCallback = context as ButtonListener
        }
        catch (e : ClassCastException) {
            throw ClassCastException(context.toString() + " must implement ButtonListener")
        }
    }

    var time = 0.0
    var initialTime = 0.0
    var starts = ArrayList<Double>()
    var ends = ArrayList<Double>()
    var totals = ArrayList<Double>()
    var phase = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRestartBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener{
            time = System.currentTimeMillis().toDouble() / 1000.0
            when (phase) {
                1 -> {
                    initialTime = time
                    starts.add(0.0)
                }
                2 -> {
                    time -= initialTime
                    ends.add(time)
                }
                3 -> {
                    starts.add(ends[0])
                }
                4 -> {
                    time -= initialTime
                    ends.add(time)
                }
                5 -> {
                    starts.add(ends[1])
                }
                6 -> {
                    time -= initialTime
                    ends.add(time)
                }
                7 -> {
                    starts.add(ends[2])
                }
                8 -> {
                    time -= initialTime
                    ends.add(time)
                }
                9 -> {
                    starts.add(ends[3])
                }
                10 -> {
                    time -= initialTime
                    ends.add(time)
                    phase = 1

                    for (i in 0 until starts.size) {
                        totals.add(ends[i] - starts[i]) //add the split for each
                    }
                    dataUpdate(it, starts, ends, totals)
                }
            }

            if (phase != 10) {
                phase++
            }
        }

        return binding.root

    }

    private fun dataUpdate(view : View, startTimes : ArrayList<Double>,
                           endTimes : ArrayList<Double>, totalTimes : ArrayList<Double>) {
        activityCallback?.updateData(startTimes, endTimes, totalTimes)
    }

}