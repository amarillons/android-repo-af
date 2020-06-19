package jp.afb114714.android.app

import android.content.Context
import android.graphics.Picture
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import okhttp3.*
import java.io.IOException

class KasenActivity : AppCompatActivity() {

fun fetchKasenData () {
    val client = OkHttpClient().newBuilder()
        .build()
    val request = Request.Builder()
        .url("https://a99820.xsrv.jp/kasen_api.php")
        .method("GET", null)
        .build()
    val response = client.newCall(request).enqueue(object : Callback {

        override fun onResponse(call: Call, response: Response) {
            Log.d("777",response.body()!!.string())
        }

        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }}
    )


//    Log.d("777",response.toString())

}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kasen)

        title="三十六歌仙"

         fetchKasenData()

        val listView = findViewById(R.id.listView) as ListView

        val p1 = Picture("Kotlin","https://a99820.xsrv.jp/kasens/kakinomoto_hitomaro.jpg")
        val p2 = Picture("Android","https://i.pinimg.com/474x/8f/8f/dc/8f8fdc621b4dec14800bfc5fea096dfd.jpg")
        val p3 = Picture("iOS","https://i.pinimg.com/474x/f4/a8/a5/f4a8a5fb786d94289e659343d2a7b2ed.jpg")
        val p4 = Picture("Swift","https://i.pinimg.com/474x/ef/03/cf/ef03cfcc9b1aced47106ef2b99030065.jpg")
        val p5 = Picture("Java","https://i.pinimg.com/474x/3f/32/3a/3f323aef178b260a85f9ab162b31691e.jpg")


        var pictures = arrayOf(p1,p2,p3,p4,p5)


//        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pictures)
        listView.adapter = RecipeAdapter(this, pictures)


        val button = findViewById<Button>(R.id.button)


//        button.setOnClickListener({
//
//            val intent = Intent()
//            intent.type = "image/*"
//            intent.action = Intent.ACTION_GET_CONTENT
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
//        })

    }

    class Picture constructor(name: String, url: String) {
        val name: String
        val imageUrl: String
        init {
            this.name = name
            this.imageUrl = url
        }
    }


    class RecipeAdapter(private val context: Context,
                        private val pictures: Array<Picture>) : BaseAdapter() {

        private val inflater: LayoutInflater
                = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //1
        override fun getCount(): Int {
            return pictures.size
        }


        //2
        override fun getItem(position: Int): Any {
            return pictures[position]
        }


        //3
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }


        //4
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            // Get view for row item
            val rowView = inflater.inflate(R.layout.my_card_layout, parent, false)

            val title = rowView.findViewById(R.id.title_text_view) as TextView
            val picture: Picture = getItem(position) as Picture
            title.text = picture.name

            val imageView = rowView.findViewById<ImageView>(R.id.icon)
            // imageView.setBackgroundColor(Color.rgb(100, 100, 50))
            // val urlStr: String = "https://i.pinimg.com/564x/ce/3a/c1/ce3ac1afff4aae2a04e0a21ed580704a.jpg"
            Picasso.get().setIndicatorsEnabled(true)
            Picasso.get().isLoggingEnabled = true
            Picasso.get().load(picture.imageUrl).into(imageView);

            return rowView
        }
    }
}
