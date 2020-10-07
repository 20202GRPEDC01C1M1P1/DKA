package br.pro.aguiar.dka

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
                ReaderFileAsyncTask().execute(arquivo)
            } else
                textViewFileLegivel.text = "Não"

            if (arquivo.canWrite())
                enableComponents(true)
            else
                enableComponents(false)
        }
        btnSalveFile.setOnClickListener {
            var conteudo = editTextTextMultiLine.text.toString()
            WriterFileAsyncTask().execute(conteudo)
        }
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
                this@MainActivity, "Conteúdo armazenado com sucesso.",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    inner class ReaderFileAsyncTask: AsyncTask<File, Unit, String>() {
        override fun doInBackground(vararg files: File?): String {
            var fileReader = FileReader(files[0])
            var bufferedReader = BufferedReader(fileReader)
            var conteudo = bufferedReader.readText()
            return conteudo
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            editTextTextMultiLine.setText(result)
            Toast.makeText(
                this@MainActivity, "Conteúdo lido com sucesso.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}