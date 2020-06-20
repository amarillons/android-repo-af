package jp.afb114714.android.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_sub2.*

class SubActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub2)

        title = "２つめの画面"
        val imageUrl: String = "https://i.pinimg.com/564x/ce/3a/c1/ce3ac1afff4aae2a04e0a21ed580704a.jpg"

        button.setOnClickListener({
            Picasso.get().load(imageUrl).into(imageView);
        })
    }
}
