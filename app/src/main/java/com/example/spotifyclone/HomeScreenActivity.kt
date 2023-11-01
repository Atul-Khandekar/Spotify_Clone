package com.example.spotifyclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.spotifyclone.databinding.ActivityHomeScreenBinding
import com.example.spotifyclone.viewmodel.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeScreenBinding
    private val viewModel: HomeScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        checkLoginStatus()
        setUpView()
        setUpBottomNavigation()

    }

    private fun checkLoginStatus() {
        viewModel.getLoginStatus { loginSuccess ->
            if (!loginSuccess) {
                logout()
            }
        }

    }

    private fun logout() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
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