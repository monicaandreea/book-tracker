package com.example.myapplicationlaborator2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.myapplicationlaborator2.databinding.Fragment2Binding


class Fragment2 : Fragment() {

  /*  private lateinit var binding: Fragment2Binding
    private var CHANNEL_ID = "channel"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        if (bundle !=null){
            val book = bundle.getParcelable<BooksModel>(Book)
            binding.name.text = book?.title ?: "Title"
            binding.button.setOnClickListener {
               notification(book)
            }
        }

    }

    private fun notification(model: BooksModel?){
        val builder =
            NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(model?.title)
                .setContentText(model?.duration)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)

            val notificationManager = requireActivity().getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
        val notificationManager = NotificationManagerCompat.from(requireContext())
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build())
    } */

}