package com.example.myapplicationlaborator2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationlaborator2.databinding.FragmentBooksBinding
import com.example.myapplicationlaborator2.db.BookModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import android.widget.SearchView

const val BOOK_ID = "book_id"

class BooksFragment : Fragment() {

    lateinit var binding: FragmentBooksBinding

    lateinit var adapter: CustomAdapter

    private lateinit var user: FirebaseAuth

    private lateinit var searchView: SearchView

    val repository: BookRepository by lazy {
        BookRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBooksBinding.inflate(inflater, container, false)

        searchView = binding.search
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotEmpty()) {

                    filter(query)
                }
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchView.isSubmitButtonEnabled = newText.isNotEmpty()
                return false
            }

        })


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
