package com.example.myapplicationlaborator2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BooksModel(val title: String, val duration: String, val image: Int) : Parcelable