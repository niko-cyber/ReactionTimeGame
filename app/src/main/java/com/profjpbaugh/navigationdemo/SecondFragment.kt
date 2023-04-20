package com.profjpbaugh.navigationdemo

import android.net.Uri
import android.os.Bundle
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
        
        return binding.root
    }



}