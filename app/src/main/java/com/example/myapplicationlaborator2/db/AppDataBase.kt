package com.example.myapplicationlaborator2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookModel::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun bookDao() : BookDao

    companion object{
        private var INSTANCE: AppDataBase?=null

        fun getInstance(context: Context): AppDataBase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java, "book.db"
                )
                    .build()
            }
            return INSTANCE
        }
    }
}