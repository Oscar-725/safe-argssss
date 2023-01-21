package com.dev.leonardom.intronavigationcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev.leonardom.intronavigationcomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val oscarcambi:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}