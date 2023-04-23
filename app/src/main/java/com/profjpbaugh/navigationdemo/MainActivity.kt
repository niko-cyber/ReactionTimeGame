package com.profjpbaugh.navigationdemo

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.profjpbaugh.navigationdemo.databinding.ActivityMainBinding
import com.profjpbaugh.navigationdemo.ui.main.MainFragment

class MainActivity : AppCompatActivity(), SecondFragment.OnFragmentInteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onFragmentInteraction(uri: Uri) {

    }




}