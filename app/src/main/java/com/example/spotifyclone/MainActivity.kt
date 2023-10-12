package com.example.spotifyclone

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spotifyclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpView()
        setUpOnClickListeners()
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

    }

    private fun setUpView() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}