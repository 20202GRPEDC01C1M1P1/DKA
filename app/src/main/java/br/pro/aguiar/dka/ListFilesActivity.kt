package br.pro.aguiar.dka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_files.*
import java.io.File

class ListFilesActivity : AppCompatActivity() {
    lateinit var file: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_files)

        var type = intent.getIntExtra("type", 1)
        when (type){
            1 -> file = filesDir
            2 -> file = cacheDir
            3 -> {
                if (Environment.getExternalStorageState() in
                    setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY))
                    file = getExternalFilesDir(null)!!
            }
            4 -> {
                if (Environment.getExternalStorageState() in
                    setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY))
                    file = externalCacheDir!!
            }
        }

        fabAddFile.setOnClickListener {
            Intent(this, FilesActivity::class.java).let {
                it.putExtra("file", file)
                startActivity(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        var arquivos = file.list()
        listViewFiles.adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, arquivos
        )
        listViewFiles.setOnItemClickListener { adapterView, view, i, l ->
            var arqSelec = File(file, arquivos[i])
            Toast.makeText(
                this,
                if (arqSelec.isDirectory) "É um diretório" else "É um arquivo",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}