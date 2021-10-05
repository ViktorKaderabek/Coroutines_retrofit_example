package com.example.coroutines_example.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coroutines_example.data.api.ApiInterface
import com.example.coroutines_example.data.api.MyDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivityViewMode : ViewModel() {


    var title1 : String = ""
    var number1 : Int = 0
    var number = MutableLiveData<String>()


    fun increaseNumber(){  //function that's increasing number in variable "number1" and then it's override into a number, that is Observable variable

        number1 += 1
        number.value = number1.toString()
    }

    fun getNumber() : LiveData<String>{//Here you are getting the variable number, that's observable and it can be shown for example

        number.value = number1.toString()
        return number

    }


    fun getMyData() {

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {

            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()!!

                val myTitleBuilder = StringBuilder()

                for (myData in responseBody){
                    myTitleBuilder.append(myData.id.toString())

                }
                title1 = myTitleBuilder.toString()
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d(TAG, "meesage " + t.message)
            }
        })
    }
    


}