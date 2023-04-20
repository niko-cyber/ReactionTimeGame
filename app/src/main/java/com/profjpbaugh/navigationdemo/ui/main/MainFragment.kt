package com.profjpbaugh.navigationdemo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.profjpbaugh.navigationdemo.R
import com.profjpbaugh.navigationdemo.databinding.FragmentMainBinding

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
        return binding.root
    }//end onCreateView

   override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding.nextBtn.setOnClickListener {

           val action : MainFragmentDirections.MainToSecond = MainFragmentDirections.mainToSecond()


          // action.setMessage()

           Navigation.findNavController(it).navigate(action)
       }


  }//end onViewCreated

}