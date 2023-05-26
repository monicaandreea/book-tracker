package com.example.myapplicationlaborator2

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationlaborator2.databinding.FragmentBooksBinding
import com.example.myapplicationlaborator2.db.BookModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

const val BOOK_ID = "book_id"

class BooksFragment : Fragment() {

    lateinit var binding: FragmentBooksBinding

    lateinit var adapter: CustomAdapter

    private lateinit var user: FirebaseAuth

    val repository: BookRepository by lazy {
        BookRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CustomAdapter{ model ->
            val bundle = Bundle()
            bundle.putInt(BOOK_ID, model.id ?: 0)

            val fragment = BookDetailsFragment()
            fragment.arguments = bundle

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, fragment, null)
                .addToBackStack(null)
                .commit()
        }

        binding.recyclerView.adapter = adapter

       /* binding.floatingAdd.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, AddBookFragment::class.java, null)
                .addToBackStack(null)
                .commit()
        } */
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.search)

        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
    }

    fun filter(text: String) {
        user = FirebaseAuth.getInstance()
        var account = user.currentUser?.email.toString()

        lifecycleScope.launch(){
            adapter.update(repository.searchAllBooks(account, text))
        }
    }

    override fun onResume() {

        user = FirebaseAuth.getInstance()
        var account = user.currentUser?.email.toString()

        super.onResume()
        lifecycleScope.launch(){
            adapter.update(repository.getAllBooks(account))
        }
    }
}

/*  <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/floating_add"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginEnd="16dp"
        android:layout_marginBottom="64dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        tools:ignore="MissingConstraints" />*/