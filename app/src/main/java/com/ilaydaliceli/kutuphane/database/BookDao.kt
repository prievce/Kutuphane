package com.ilaydaliceli.kutuphane.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ilaydaliceli.kutuphane.model.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    suspend fun getAll(): List<Book>

    @Query("SELECT * FROM books WHERE title = :bookTitle")
    suspend fun findByName (bookTitle : String) : Book

    @Insert
    suspend fun insertAll(list : List<Book>)
}
