package com.example.myapplicationlaborator2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplicationlaborator2.databinding.FragmentAccountBinding
import com.example.myapplicationlaborator2.databinding.FragmentAddBookBinding
import com.google.firebase.auth.FirebaseAuth

class AccountFragment : Fragment() {
    lateinit var binding: FragmentAccountBinding

    private lateinit var user: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user = FirebaseAuth.getInstance()

        if(user.currentUser != null){
            user.currentUser?.let {
                binding.tvUserEmail.text = it.email

            }
        }

        binding.button.setOnClickListener{
            user.signOut()
            val intent = Intent(activity, Login::class.java)
            startActivity(intent)
        }
    }
}