package com.example.spotifyclone.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.spotifyclone.R
import com.example.spotifyclone.constants.AppConstants
import com.example.spotifyclone.databinding.ActivityHomeScreenBinding
import com.example.spotifyclone.utils.Prefs
import com.example.spotifyclone.viewmodel.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeScreenBinding
    private val viewModel: HomeScreenViewModel by viewModels()

    @Inject
    lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        checkLoginStatus()
        setUpObservers()
        setUpView()
        setUpBottomNavigation()
        getUserProfile()
    }

    private fun setUpObservers() {

        lifecycleScope.launch {
            viewModel.profileFetched.collectLatest { isProfileFetched ->
                if (isProfileFetched) {
                    Toast.makeText(
                        applicationContext,
                        prefs.getString(AppConstants.USER_ID, "Empty"),
                        Toast.LENGTH_LONG
                    ).show()
                    Toast.makeText(
                        applicationContext,
                        prefs.getString(AppConstants.USER_NAME, "name"),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            viewModel.profileFetchingError.collectLatest { errorMessage ->
                errorMessage?.let {
                    Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getUserProfile() {
        if (!viewModel.profileFetched.value) {
            viewModel.getCurrentUserProfile()
        }
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

    private fun setUpView() {
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setUpBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = binding.bottomNavigation
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

}