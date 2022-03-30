package com.example.bobopizza.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.bobopizza.R
import com.example.bobopizza.databinding.ActivityMainBinding
import com.example.bobopizza.domain.viewmodels.HomeViewModel
import com.example.bobopizza.domain.repository.Repository
import com.example.bobopizza.domain.viewmodels.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(findViewById(R.id.top_bar))

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}