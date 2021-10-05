package com.example.coroutines_example.presentation

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coroutines_example.R
import com.example.coroutines_example.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainActivityViewMode
    private var message: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)


        mainViewModel = ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        ).get(MainActivityViewMode::class.java)

        mainViewModel.getNumber().observe(this, Observer { binding.textView.text = it })
        mainViewModel.getNumber().observe(this, Observer { message = it })

        binding.btnGetData.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {

                  delay(4500L)

                  mainViewModel.getMyData()
                  binding.id1.append(mainViewModel.title1)
                  binding.id2.text = mainViewModel.title1
                  binding.id3.append(mainViewModel.title1)
                  binding.id4.append(mainViewModel.title1)
                  binding.id5.append(mainViewModel.title1)

              }
            repeat(2) { Toast.makeText(applicationContext, "please wait...", LENGTH_LONG).show()}
        }

        binding.increase.setOnClickListener {
            mainViewModel.increaseNumber()
        }

        binding.delay.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {

                delay(4500L)
                Toast.makeText(applicationContext, message, LENGTH_SHORT).show()
            }

            /*CoroutineScope(Dispatchers.IO).launch {

                delay(4500L)

                mainViewModel.getMyData()
                binding.id1.append(mainViewModel.title1)
                binding.id2.text = mainViewModel.title1
                binding.id3.append(mainViewModel.title1)
                binding.id4.append(mainViewModel.title1)
                binding.id5.append(mainViewModel.title1)

            }*/

            repeat(2) { Toast.makeText(applicationContext, "please wait...", LENGTH_LONG).show() }
        }


    }
}