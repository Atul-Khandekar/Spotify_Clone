package com.example.spotifyclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.spotifyclone.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkLoginStatus()
        setUpView()
        setUpBottomNavigation()

    }

    private fun checkLoginStatus() {
        
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            onEnterAnimationComplete()
            replace(R.id.nav_host_fragment, fragment)
        }
    }

    private fun setUpView() {
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setUpBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.homeFragment -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.searchFragment -> {
                    loadFragment(SearchFragment())
                    true
                }

                R.id.libraryFragment -> {
                    loadFragment(LibraryFragment())
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

}