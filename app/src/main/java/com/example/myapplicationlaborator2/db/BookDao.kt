package com.example.myapplicationlaborator2.db

import androidx.room.*
import com.example.myapplicationlaborator2.db.BookModel

@Dao
interface BookDao {
    @Query("SELECT * FROM book_items where account LIKE :account")
    suspend fun getAllBooks(account: String): List<BookModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(note: BookModel)

    @Query("DELETE FROM book_items WHERE id = :id")
    suspend fun deleteBook(id: Int)

    @Query("SELECT * FROM book_items WHERE id LIKE :id")
    suspend fun getBookDetails(id : Int) : BookModel

    @Update
    suspend fun updateBook(book : BookModel)

    @Query("SELECT * FROM book_items where account LIKE :account AND (title LIKE :text OR author LIKE :text)")
    suspend fun searchAllBooks(account: String, text: String): List<BookModel>

}