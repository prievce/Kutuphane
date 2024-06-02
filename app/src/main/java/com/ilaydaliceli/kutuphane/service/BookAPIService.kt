package com.ilaydaliceli.kutuphane.service

import com.ilaydaliceli.kutuphane.model.Book
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookAPIService {

    //https://raw.githubusercontent.com/dudeonthehorse/datasets/master/amazon.books.json
    private val BASE_URL = "https://wolnelektury.pl/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BookAPI::class.java)


    fun getData(): Call<List<Book>> {
        return api.getBooks()
    }



}