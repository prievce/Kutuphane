package com.ilaydaliceli.kutuphane.service

import com.ilaydaliceli.kutuphane.model.Book
import retrofit2.Call
import retrofit2.http.GET

interface BookAPI {

    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    //https://wolnelektury.pl/api/authors/adam-mickiewicz/kinds/liryka/parent_books/?format=json
    @GET("/api/authors/adam-mickiewicz/kinds/liryka/parent_books/")
    fun getBooks(): Call<List<Book>>
}