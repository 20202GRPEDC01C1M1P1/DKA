package br.pro.aguiar.dka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.pro.aguiar.dka.async.Repository
import br.pro.aguiar.dka.async.UserAsyncTask
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var file = File(filesDir, "exemplo2.txt")
        var fileWriter = FileWriter(file)
        var bufferedWriter = BufferedWriter(fileWriter)
        bufferedWriter.write("Thiago Vieira de Aguiar")

        if (file.exists()){
            textViewMain.text = "Arquivo existe"
        } else {
            textViewMain.text = "Arquivo não existe"
        }
    }
}