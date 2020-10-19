package br.pro.aguiar.dka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

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
}