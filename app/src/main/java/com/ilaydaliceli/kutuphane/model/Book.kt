package com.ilaydaliceli.kutuphane.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



@Entity (tableName = "books")
data class Book(

    @PrimaryKey(autoGenerate = true) val id:Int=0,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title : String,
    @ColumnInfo(name = "simple_thumb")
    @SerializedName("simple_thumb")
    val bookUrl: String,
    @ColumnInfo(name = "kind")
    @SerializedName("kind")
    val authors:String


)
