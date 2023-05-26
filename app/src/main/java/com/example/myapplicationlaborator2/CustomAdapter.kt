package com.example.myapplicationlaborator2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationlaborator2.databinding.BookItemBinding
import com.example.myapplicationlaborator2.db.BookModel

class CustomAdapter(private val onItemClick: (BookModel) -> Unit) : RecyclerView.Adapter<CustomAdapter.BookHolder>() {

    lateinit var binding: BookItemBinding
    var list = mutableListOf <BookModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        binding = BookItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BookHolder(binding)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val item = list[position]
        holder.image.setImageDrawable(
            ContextCompat.getDrawable(
                holder.image.context, R.drawable.book_image
            )
        )
        holder.author.text = item.author
        holder.title.text = item.title
        holder.constraint.setOnClickListener {
           onItemClick(item)
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun update(new_list: List<BookModel>){
        list.clear()
        list.addAll(new_list)
        notifyDataSetChanged()
    }

    class BookHolder(binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val author = binding.author
        val image = binding.image
        val constraint = binding.constraint
    }

}
