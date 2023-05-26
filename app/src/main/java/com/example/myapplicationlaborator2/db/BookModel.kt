package com.example.myapplicationlaborator2.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplicationlaborator2.R
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "book_items")

data class BookModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "author") var author: String,
    @ColumnInfo(name = "pages") var pages: String,
    @ColumnInfo(name = "account") var account: String = "",
    @ColumnInfo(name = "image") var image: Int = 0,
    @ColumnInfo(name = "readStatus") var readStatus: Boolean = false
)