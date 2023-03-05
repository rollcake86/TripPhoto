package com.rollcake.tripPhoto.authentication

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import androidx.lifecycle.viewModelScope
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.rollcake.tripPhoto.MainActivity
import com.rollcake.tripPhoto.databinding.ActivityAuthenticationBinding
import com.rollcake.tripPhoto.network.TripApi
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * This class should be the starting point of the app, It asks the users to sign in / register, and redirects the
 * signed in users to the RemindersActivity.
 */
class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding
    private val viewModel by viewModels<LoginViewModel>()

    companion object {
        const val TAG = "AuthenticationActivity"
        const val SIGN_IN_RESULT_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.loginButton.setOnClickListener {
            launchSignInFlow()

            //    serviceKey=hm3Ng%2Bp0hajUH1lyqqB1JTmURPuIidiOj%2BoR1I49TQDEJPB9eY9CrArmUXrlx1PQ1DqvA%2B%2FqNSJWJhFa73mamw%3D%3D&
//    numOfRows=10&
//    pageNo=1&
//    MobileOS=ETC&
//    MobileApp=AppTest&
//    _type=json&
//    listYN=Y&
//    arrange=C&
//    mapX=126.981611&
//    mapY=37.568477&


        }

        viewModel.authenticationState.observe(this) { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> startMainActivity()
                else -> Log.e(
                    TAG,
                    "Authentication state that doesn't require any UI change $authenticationState"
                )
            }
        }

    }

    private fun launchSignInFlow() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                providers
            ).build(), SIGN_IN_RESULT_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
                startActivity(intent)
                this.finish()
            } else {
                Toast.makeText(this, "Error : ${response?.error?.errorCode}", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun startMainActivity() {
        val activityIntent = Intent(this, MainActivity::class.java)
        startActivity(activityIntent)
    }


}
