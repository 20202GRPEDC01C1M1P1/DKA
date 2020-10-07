package br.pro.aguiar.dka

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // editFileName
        // btnValidateFile
        // textViewFileExiste
        // textViewFileLegivel
        // editTextTextMultiLine
        // btnSalveFile
        // File

        btnValidateFile.setOnClickListener {
            var nomeArquivo = editFileName.text.toString()
            var file = File(filesDir, nomeArquivo)

            if (file.exists()){
                textViewFileExiste.text = "Sim"
            } else
                textViewFileExiste.text = "Não"

            if (file.canRead())
                textViewFileLegivel.text = "Sim"
            else
                textViewFileLegivel.text = "Não"

            if (file.canWrite())
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