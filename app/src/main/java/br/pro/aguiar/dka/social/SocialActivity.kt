package br.pro.aguiar.dka.social

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.pro.aguiar.dka.R
import com.google.firebase.auth.FirebaseAuth

class SocialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social)
    }

    override fun onDestroy() {
        super.onDestroy()
        FirebaseAuth.getInstance().signOut()
    }
}