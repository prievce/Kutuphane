package com.ilaydaliceli.kutuphane.viewmodel

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilaydaliceli.kutuphane.database.BookDao
import com.ilaydaliceli.kutuphane.database.BookDatabase
import com.ilaydaliceli.kutuphane.model.Book
import com.ilaydaliceli.kutuphane.service.BookAPIService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (application: Application) : AndroidViewModel(application){

    private val bookAPI = BookAPIService()

    val bookData = MutableLiveData<List<Book>>()
    val bookLoad = MutableLiveData<Boolean>()
    val bookError = MutableLiveData<Boolean>()

    private var bookDatabase : BookDatabase?=null
    private var bookDao: BookDao?= null

    val book = MutableLiveData<Book>()

    init {
        bookDatabase= BookDatabase.getInstance(application)
        bookDao = bookDatabase?.bookDao()
    }


    fun getDataFromAPI(){
        bookLoad.value=true

        bookAPI.getData().enqueue(object: Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                bookData.value=response.body()
                bookLoad.value = false
                bookError.value = false
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                bookLoad.value = false
                bookError.value = true
                Log.e("RetrofitError", t.message.toString())
            }

        })

    }

    fun insertAll(list: List<Book>) = viewModelScope.launch {
        bookDao?.insertAll(list)
    }

    fun findByName(name:String) = viewModelScope.launch {
        book.value=bookDao?.findByName(name)
    }

}