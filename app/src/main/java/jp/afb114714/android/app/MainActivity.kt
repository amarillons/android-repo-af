package jp.afb114714.android.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "なんでも一覧表"

        nextScreenButton.setOnClickListener({
            Log.d("777","clicked")

            val intent = Intent(this, SubActivity2::class.java)

            startActivity(intent)
        })

        listButton.setOnClickListener({
            val intent = Intent(this, KasenActivity::class.java)

            startActivity(intent)
        })
    }
}

