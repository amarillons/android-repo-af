package jp.afb114714.android.app

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
        })
    }
}

