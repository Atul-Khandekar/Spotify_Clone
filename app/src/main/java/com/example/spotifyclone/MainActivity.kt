package com.example.spotifyclone

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.spotifyclone.constants.AppConstants
import com.example.spotifyclone.databinding.ActivityMainBinding
import com.example.spotifyclone.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val viewModel: MainViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLoginStatus()
        setUpView()
        setUpOnClickListeners()
        setUpObservers()
    }

    private fun setUpObservers() {

        viewModel.errorMessage.observe(this) { message ->
            message?.let {
                if (message.isNotEmpty()) {
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.accessToken.observe(this) {token ->
            token?.let { loggedInSuccessfully() }
        }
    }

    private fun setUpOnClickListeners() {
        binding.txtLogIn.setOnClickListener {
            loadLogInPage()
        }
    }

    private fun loadLogInPage() {
        val authUri = Uri.parse(AppConstants.BASE_AUTH_URL).buildUpon()
            .appendPath("authorize")
            .appendQueryParameter("client_id", AppConstants.CLIENT_ID)
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("redirect_uri", AppConstants.REDIRECT_URI)
            .appendQueryParameter("state", AppConstants.STATE)
            .appendQueryParameter("scope", AppConstants.SCOPES)
            .appendQueryParameter("show_dialog", "true")
            .build()
        openLoginPage(authUri)
    }

    private fun openLoginPage(authUri: Uri?) {
        val intent = Intent(Intent.ACTION_VIEW, authUri)
        startActivity(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.data?.getQueryParameter(AppConstants.CODE)?.also { code ->
            viewModel.getAuthorizationToken(code)
        }
    }

    private fun setUpView() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun getLoginStatus() {
        viewModel.getLoginStatus { isLoggedIn ->
            if (isLoggedIn) {
                loggedInSuccessfully()
            }
        }
    }

    private fun loggedInSuccessfully() {
        val intent = Intent(this, HomeScreenActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}