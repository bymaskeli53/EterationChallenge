package com.gundogar.eterationchallenge.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.gundogar.eterationchallenge.R
import com.gundogar.eterationchallenge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    // private lateinit var  navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // TODO: Shared view model ile badge bilgisi alınabilir
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)
        setSupportActionBar(binding.toolbar.root)
        setupActionBarWithNavController(navController)

        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.basketFragment)
        badge.isVisible = true
        badge.number = 10
        setSupportActionBar(binding.toolbar.root)
//        navController = Navigation.findNavController(this, R.id.nav_host_fragment_main)
//        setupActionBarWithNavController(navController)
//        binding.bottomNavigation.setupWithNavController(navController)

//        navController.addOnDestinationChangedListener{_,destination,_ ->
//            if (destination.id == R.id.detailFragment) {
//                binding.bottomNavigation.visibility = View.INVISIBLE
//            } else {
//                binding.bottomNavigation.visibility = View.VISIBLE
//            }
//        }
    }

    fun updateToolbarTitle(title: String) {
        binding.toolbar.toolbarTitle.text = "Product Detail"
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
