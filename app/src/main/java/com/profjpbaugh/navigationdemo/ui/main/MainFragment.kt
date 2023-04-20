package com.profjpbaugh.navigationdemo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
        binding.nextBtn.setOnClickListener {

            val action : MainFragmentDirections.MainToSecond = MainFragmentDirections.mainToSecond()


            //action.to(MainFragmentDirections.mainToSecond())

            Navigation.findNavController(it).navigate(action)
        }
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
    }//end onCreateView
}