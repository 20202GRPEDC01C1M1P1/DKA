package br.pro.aguiar.dka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.pro.aguiar.dka.async.Repository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Repository().execute("Termo de Busca")
        Log.i("AsyncTask", "MainThread")
    }
}