package com.gundogar.eterationchallenge.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gundogar.eterationchallenge.R
import com.gundogar.eterationchallenge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // TODO: Shared view model ile badge bilgisi alınabilir
        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.basketFragment)
        badge.isVisible = true
        badge.number = 10

    }
}