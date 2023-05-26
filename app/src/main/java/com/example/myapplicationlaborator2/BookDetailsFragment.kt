package com.example.myapplicationlaborator2

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationlaborator2.databinding.FragmentBookDetailsBinding
import com.example.myapplicationlaborator2.db.BookModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class BookDetailsFragment : Fragment() {
    lateinit var binding: FragmentBookDetailsBinding

    private lateinit var user: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repo = BookRepository(requireContext())

        user = FirebaseAuth.getInstance()
        val account = user.currentUser?.email.toString()

        val bundle = this.arguments

        if(bundle != null){
            val id = bundle.getInt(BOOK_ID)
            lifecycleScope.launch{
                val book = repo.getBookDetails(id)
                binding.titleDetails.text = SpannableStringBuilder(book.title)
                binding.authorDetails.text = SpannableStringBuilder(book.author)
                binding.pagesDetails.text = SpannableStringBuilder(book.pages)
            }


            binding.buttonEdit.setOnClickListener {
                lifecycleScope.launch{
                    repo.update(
                        BookModel(
                            id,
                            title = binding.titleDetails.text.toString(),
                            author = binding.authorDetails.text.toString(),
                            pages = binding.pagesDetails.text.toString(),
                            account = account
                        )
                    )
                }
                requireActivity().supportFragmentManager.popBackStack()
            }

            binding.buttonDelete.setOnClickListener {
                lifecycleScope.launch {
                    repo.delete(id)
                }
                    requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }
}