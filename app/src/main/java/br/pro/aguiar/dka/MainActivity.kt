package br.pro.aguiar.dka

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.File
import java.io.FileReader


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnValidateFile.setOnClickListener {
            var nomeArquivo = editFileName.text.toString()
            var arquivo = File(filesDir, nomeArquivo)

            if (arquivo.exists()){
                textViewFileExiste.text = "Sim"
            } else
                textViewFileExiste.text = "Não"

            if (arquivo.canRead()) {
                textViewFileLegivel.text = "Sim"
                var fileReader = FileReader(arquivo)
                var bufferedReader = BufferedReader(fileReader)
                var conteudo = bufferedReader.readText()
                editTextTextMultiLine.setText(conteudo)
            } else
                textViewFileLegivel.text = "Não"

            if (arquivo.canWrite())
                enableComponents(true)
            else
                enableComponents(false)
        }

        btnSalveFile.setOnClickListener {
            var conteudo = editTextTextMultiLine.text.toString()
            var arquivo = editFileName.text.toString()
            openFileOutput(arquivo, Context.MODE_PRIVATE).use {
                it.write(conteudo.toByteArray())
            }
        }
    }

    fun enableComponents(bool: Boolean){
        editTextTextMultiLine.isEnabled = bool
        btnSalveFile.isEnabled = bool
    }
}