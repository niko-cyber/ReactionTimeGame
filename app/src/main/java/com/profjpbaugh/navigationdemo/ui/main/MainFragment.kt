package com.profjpbaugh.navigationdemo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.Navigation
import com.profjpbaugh.navigationdemo.R
import com.profjpbaugh.navigationdemo.databinding.FragmentMainBinding
import kotlin.random.Random

class MainFragment : Fragment() {

    var time = 0L
    var initialTime = 0L
    var starts = ArrayList<Float>()
    var ends = ArrayList<Float>()
    var totals = ArrayList<Float>()
    var phase = 1


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding : FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentMainBinding.inflate(inflater, container, false)

        val motionLayout = binding.motionLayout
        // get constraintSets for start and end states
        val constraintSetStart = motionLayout.getConstraintSet(R.id.start)
        val constraintSetEnd = motionLayout.getConstraintSet(R.id.end)

        constraintSetEnd?.setHorizontalBias(R.id.animationButton, Random.nextFloat())
        constraintSetEnd?.setVerticalBias(R.id.animationButton, Random.nextFloat())
        constraintSetEnd?.applyTo(motionLayout)

        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
                // called when the transition starts
                Log.i("MainFragment", phase.toString())
                time = System.currentTimeMillis()
                when (phase) {
                    1 -> {
                        initialTime = time
                        starts.add(0.0F)
                    }
                    2 -> {
                        time -= initialTime
                        ends.add(time.toFloat() / 1000F)
                    }
                    3 -> {
                        starts.add(ends[0])
                    }
                    4 -> {
                        time -= initialTime
                        ends.add(time.toFloat() / 1000F)
                    }
                    5 -> {
                        starts.add(ends[1])
                    }
                    6 -> {
                        time -= initialTime
                        ends.add(time.toFloat() / 1000F)
                    }
                    7 -> {
                        starts.add(ends[2])
                    }
                    8 -> {
                        time -= initialTime
                        ends.add(time.toFloat() / 1000F)
                    }
                    9 -> {
                        starts.add(ends[3])
                    }
                    10 -> {
                        time -= initialTime
                        ends.add(time.toFloat() / 1000F)
                        phase = 1

                        for (i in 0 until starts.size) {
                            totals.add(ends[i] - starts[i]) //add the split for each
                        }
                        logData(starts, ends, totals)
                        //updateData(starts, ends, totals)
                        val action : MainFragmentDirections.MainToSecond =
                            MainFragmentDirections.mainToSecond(
                                starts.toFloatArray(), ends.toFloatArray(), totals.toFloatArray())

                        //action.to(MainFragmentDirections.mainToSecond())

                        Navigation.findNavController(binding.motionLayout).navigate(action)

                    }
                }


                if (phase != 10) {
                    phase++
                }
            }
            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
                // called when the transition is in progress
            }
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                // called when the transition is completed
                if (motionLayout?.progress == 0.0f) {
                    // this is start
                    // set the end position to random value
                    constraintSetEnd?.setHorizontalBias(R.id.animationButton, Random.nextFloat())
                    constraintSetEnd?.setVerticalBias(R.id.animationButton, Random.nextFloat())
                    constraintSetEnd?.applyTo(motionLayout)
                } else {
                    // this is end
                    // set the start position to random value
                    constraintSetStart?.setHorizontalBias(R.id.animationButton, Random.nextFloat())
                    constraintSetStart?.setVerticalBias(R.id.animationButton, Random.nextFloat())
                    constraintSetStart?.applyTo(motionLayout)
                }
            }
            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {
                // called when a transition trigger is activated

            }
        })


        return binding.root
    }//end


    fun logData(startTimes : ArrayList<Float>,
                   endTimes : ArrayList<Float>, totalTimes : ArrayList<Float>) {
        Log.i("MainFragment", startTimes.toString())
        Log.i("MainFragment", endTimes.toString())
        Log.i("MainFragment", totalTimes.toString())

    }



}