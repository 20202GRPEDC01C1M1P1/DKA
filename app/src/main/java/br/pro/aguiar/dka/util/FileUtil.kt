package br.pro.aguiar.dka.util

import android.content.Context
import java.io.File

object FileUtil {
    fun criarArquivoTemporario(file: File) {
        File.createTempFile("Cache", null, file)
    }
}