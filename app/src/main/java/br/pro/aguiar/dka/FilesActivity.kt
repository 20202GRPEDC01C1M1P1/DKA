package br.pro.aguiar.dka

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_files.*
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class FilesActivity : AppCompatActivity() {
    lateinit var file: File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_files)

        file = intent.getSerializableExtra("file") as File

        btnCreateFile.setOnClickListener {
            var nomeArquivo = editFileName.text.toString()
            var arquivo = File(file, nomeArquivo)
            arquivo.createNewFile()
            validarArquivo(arquivo)
        }
        btnValidateFile.setOnClickListener {
            var nomeArquivo = editFileName.text.toString()
            var arquivo = File(file, nomeArquivo)
            validarArquivo(arquivo)
        }
        btnSalveFile.setOnClickListener {
            var conteudo = editTextTextMultiLine.text.toString()
            WriterFileAsyncTask().execute(conteudo)
        }
    }

    private fun validarArquivo(arquivo: File) {
        if (arquivo.exists()) {
            textViewFileExiste.text = "Sim"
            btnCreateFile.isEnabled = false
        } else {
            textViewFileExiste.text = "Não"
            btnCreateFile.isEnabled = true
        }

        if (arquivo.canRead()) {
            textViewFileLegivel.text = "Sim"
            ReaderFileAsyncTask().execute(arquivo)
        } else
            textViewFileLegivel.text = "Não"

        if (arquivo.canWrite())
            enableComponents(true)
        else
            enableComponents(false)
    }

    fun enableComponents(bool: Boolean){
        editTextTextMultiLine.isEnabled = bool
        btnSalveFile.isEnabled = bool
    }

    inner class WriterFileAsyncTask: AsyncTask<String, Unit, Unit>() {

        override fun doInBackground(vararg conteudos: String?) {
            var arquivo = editFileName.text.toString()
            openFileOutput(arquivo, Context.MODE_PRIVATE).use {
                conteudos.forEach {msg ->
                    it.write(msg?.toByteArray())
                }
            }
        }
        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            Toast.makeText(
                this@FilesActivity, "Conteúdo armazenado com sucesso.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    inner class ReaderFileAsyncTask: AsyncTask<File, Unit, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progressBarReadFIle.visibility = View.VISIBLE
            Toast.makeText(
                this@FilesActivity, "Lendo arquivo em segundo plano",
                Toast.LENGTH_LONG
            ).show()
        }
        override fun doInBackground(vararg files: File?): String {
            var fileReader = FileReader(files[0])
            var bufferedReader = BufferedReader(fileReader)
            var conteudo = bufferedReader.readText()
            return conteudo
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            editTextTextMultiLine.setText(result)
            progressBarReadFIle.visibility = View.GONE
            Toast.makeText(
                this@FilesActivity, "Conteúdo lido com sucesso.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}