package br.pro.aguiar.at

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var loginButton: LoginButton
    private lateinit var callbackManager: CallbackManager
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.login_button)
        loginButton.setReadPermissions("email", "public_profile")
//        loginButton.setFragment(this)

        loginButton.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    handleFacebookAccessToken(loginResult.accessToken)
                }
                override fun onCancel() {
                    Snackbar
                        .make(
                            root_main_layout,
                            "Operação cancelada.",
                            Snackbar.LENGTH_LONG
                        ).show()
                }
                override fun onError(error: FacebookException) {
                    Snackbar
                        .make(
                            root_main_layout,
                            "${error.message}",
                            Snackbar.LENGTH_LONG
                        ).show()
                }
            });
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.i("URLPhoto", "${user?.photoUrl}")
                    // navegar para outra tela
                } else {
                    Toast.makeText(baseContext,
                        "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}