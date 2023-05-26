package com.example.myapplicationlaborator2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationlaborator2.databinding.FragmentAddBookBinding
import com.example.myapplicationlaborator2.db.BookModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AddBookFragment : Fragment() {

    lateinit var binding: FragmentAddBookBinding

    private lateinit var user: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user = FirebaseAuth.getInstance()
        val account = user.currentUser?.email.toString()
        val repository = BookRepository(requireContext())
        binding.button.setOnClickListener{
            lifecycleScope.launch {
                repository.insert(
                    BookModel(title = binding.titleEdit.text.toString(),
                                author = binding.authorEdit.text.toString(),
                                pages = binding.pagesEdit.text.toString(),
                                account = account)
                )
            }
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}