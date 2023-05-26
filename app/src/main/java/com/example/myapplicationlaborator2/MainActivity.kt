package com.example.myapplicationlaborator2

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.security.Key

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var user: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        setupWithNavController(bottomNavigationView, navController)

     /*  val KEY_SPLASH = "slpash"

        val sharedPreferences: SharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE)

        if(!sharedPreferences.getBoolean(KEY_SPLASH, false)){
            supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer, SplashFragment::class.java, null)
                .commit()
            sharedPreferences.edit().putBoolean(KEY_SPLASH, true).apply()
        } else {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.mainContainer, BooksFragment::class.java, null)
                .commit()
        }*/
    }

}


/*        <androidx.fragment.app.FragmentContainerView
        android:id="@+id/mainContainer"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:defaultNavHost = "true"
        android:name="androidx.navigation.fragment.NavHostFragment"
        app:navGraph="@navigation/nav_graph"
        tools:ignore="MissingConstraints" />*/