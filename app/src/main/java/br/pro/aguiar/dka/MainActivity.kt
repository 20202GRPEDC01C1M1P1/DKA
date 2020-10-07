package br.pro.aguiar.dka

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import br.pro.aguiar.dka.async.Repository
import br.pro.aguiar.dka.async.UserAsyncTask
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.File
import java.io.FileReader


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFileOutput("exemplo.txt", Context.MODE_PRIVATE).use {
            it.write("Thiago Vieira de Aguiar".toByteArray())
            it.write("\nthiago.aguiar@prof.infnet.edu.br".toByteArray())
            it.write("\n(21) 995344540".toByteArray())
            it.write("\nInfnet".toByteArray())
        }


        var file = File(filesDir, "exemplo.txt")
        if (file.exists()){

            var fileReader = FileReader(file)

            var bufferedReader = BufferedReader(fileReader)

            bufferedReader.readLines().forEach {
                textViewMain.text = textViewMain.text.toString() + "\n" + it
            }
        }

        // Escrever em arquivo
//        openFileOutput("exemplo.txt", Context.MODE_PRIVATE).use {
//            it.write("Thiago Vieira de Aguiar".toByteArray())
//        }

//        var file = File(filesDir, "exemplo2.txt")
//        var fileWriter = FileWriter(file)
//        var bufferedWriter = BufferedWriter(fileWriter)
//        bufferedWriter.write("Thiago Vieira de Aguiar")
//
//        if (file.exists()){
//            textViewMain.text = "Arquivo existe"
//        } else {
//            textViewMain.text = "Arquivo não existe"
//        }
    }
}