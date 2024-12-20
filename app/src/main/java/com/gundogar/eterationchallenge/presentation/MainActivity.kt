package com.gundogar.eterationchallenge.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.gundogar.eterationchallenge.R
import com.gundogar.eterationchallenge.databinding.ActivityMainBinding
import com.gundogar.eterationchallenge.utils.invisible
import com.gundogar.eterationchallenge.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private val basketViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        basketViewModel.getBasketItemCount()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)
        setSupportActionBar(binding.toolbar.root)
        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.detailFragment -> {
                    binding.bottomNavigation.invisible()
                }

                else -> {
                    binding.bottomNavigation.show()
                    updateToolbarTitle("E-Market")
                }
            }

//
//            val badge = binding.bottomNavigation.getOrCreateBadge(R.id.basketFragment)
//            badge.isVisible = true
//            badge.number = 10
            setSupportActionBar(binding.toolbar.root)
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                basketViewModel.basketItemCount.collect {
                    val badge = binding.bottomNavigation.getOrCreateBadge(R.id.basketFragment)
                    badge.isVisible = true
                    badge.number = it
                }
            }
        }
    }

    fun updateToolbarTitle(title: String) {
        binding.toolbar.toolbarTitle.text = title
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
