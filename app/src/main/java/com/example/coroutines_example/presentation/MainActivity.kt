package com.example.coroutines_example.presentation

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines_example.R
import com.example.coroutines_example.data.api.ApiInterface
import com.example.coroutines_example.data.api.MyDataItem
import com.example.coroutines_example.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainActivityViewModel
    private var message: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)


        mainViewModel = ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        ).get(MainActivityViewModel::class.java)

        binding.recyclerview.layoutManager =
            LinearLayoutManager(this)
        binding.recyclerview.adapter =
            mainViewModel.fastAdapter // Nastavuje recyclerview co bude obsahem
        binding.recyclerview.setHasFixedSize(true)



        mainViewModel.getNumber().observe(this, Observer { binding.textView.text = it })
        mainViewModel.getNumber().observe(this, Observer { message = it })

        val apiInterface = ApiInterface.ApiInterface.create().getMovies()

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue( object : Callback<List<MyDataItem>> {
            override fun onResponse(call: Call<List<MyDataItem>>?, response: Response<List<MyDataItem>>?) {

                val responseBody = response?.body()!!

                val myTitleBuilder = StringBuilder()

                for (myData in responseBody){

                    myTitleBuilder.append(myData.name.toString())

                    mainViewModel.itemAdapter.add(AdapterOfItem(MyDataItem(name = myTitleBuilder.toString())))

                }

            }



            override fun onFailure(call: Call<List<MyDataItem>>?, t: Throwable?) {

            }
        })

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