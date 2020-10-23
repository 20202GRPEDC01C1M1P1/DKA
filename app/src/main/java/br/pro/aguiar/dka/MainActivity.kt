package br.pro.aguiar.dka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var mStorageRef: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mStorageRef = FirebaseStorage.getInstance()
                        .getReference()

        var fileStorage
                = File(filesDir, "imgStorage.png")

        var arquivoStorage = mStorageRef
            .child("AguiarPixels.png")

        var task = arquivoStorage
            .getFile(fileStorage)

        task.addOnSuccessListener {
            Log.i("FirebaseStorage", "Arquivo baixado com sucesso!")
        }.addOnFailureListener {
            Log.i("FirebaseStorage", "Download falhou: ${it.message}")
        }


    }

    /*
//    fun actionBtnClick(view: View) {
//        var type: Int
//        when (view.id){
//            R.id.btnArmInternoFile -> type = 1
//            R.id.btnArmInternoCache -> type = 2
//            R.id.btnArmExternoFile -> type = 3
//            R.id.btnArmExternoCache -> type = 4
//            else -> type = 1
//        }
//        Intent(this, ListFilesActivity::class.java).let {
//            it.putExtra("type", type)
//            startActivity(it)
//        }
//    }

     */
}