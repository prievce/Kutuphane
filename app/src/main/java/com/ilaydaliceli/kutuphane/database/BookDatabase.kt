package com.ilaydaliceli.kutuphane.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ilaydaliceli.kutuphane.model.Book

@Database(entities = [Book::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao


    companion object {
        @Volatile //sakladığı değerin Thread'ler tarafından okunmaya çalışıldığında hepsinde aynı değerin okunacağının garantisini verir.
        private var INSTANCE: BookDatabase? = null

        fun getInstance(context: Context): BookDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    "country-db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


