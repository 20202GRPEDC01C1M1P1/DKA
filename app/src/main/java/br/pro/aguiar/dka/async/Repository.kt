package br.pro.aguiar.dka.async

import android.os.AsyncTask
import android.util.Log

class Repository : AsyncTask<String, Unit, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
        Log.i("AsyncTask", "PreExecute")
    }

    override fun doInBackground(vararg entrada: String?): String {
        Thread.sleep(3000L)
        return entrada[0]!!
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        Log.i("AsyncTask","$result")
    }
}