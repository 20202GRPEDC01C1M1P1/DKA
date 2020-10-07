package br.pro.aguiar.dka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.pro.aguiar.dka.async.Repository
import br.pro.aguiar.dka.async.UserAsyncTask

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Repository().execute("Termo de Busca")
        UserAsyncTask().execute(1)
        Log.i("AsyncTask", "MainThread")
    }
}