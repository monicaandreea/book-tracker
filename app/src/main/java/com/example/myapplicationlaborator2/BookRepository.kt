package com.example.myapplicationlaborator2

import android.content.Context
import com.example.myapplicationlaborator2.db.AppDataBase
import com.example.myapplicationlaborator2.db.BookDao
import com.example.myapplicationlaborator2.db.BookModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository(context: Context) {

    var db: BookDao = AppDataBase.getInstance(context)?.bookDao()!!

    suspend fun getAllBooks(account: String):List<BookModel>{
        return withContext(Dispatchers.IO){
            db.getAllBooks(account)
        }
    }

    suspend fun insert(book: BookModel){
        return withContext(Dispatchers.IO){
            db.insertAll(book)
        }
    }

    suspend fun delete(id: Int){
        return withContext(Dispatchers.IO){
            db.deleteBook(id)
        }
    }

    suspend fun getBookDetails(id: Int) : BookModel{
        return withContext(Dispatchers.IO){
            db.getBookDetails(id)
        }
    }

    suspend fun update(book: BookModel){
        return withContext(Dispatchers.IO){
            db.updateBook(book)
        }
    }

    suspend fun searchAllBooks(account: String, text: String):List<BookModel>{
        return withContext(Dispatchers.IO){
            db.searchAllBooks(account, text)
        }
    }

}