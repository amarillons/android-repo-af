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
import android.util.Log
import kotlinx.android.synthetic.main.activity_kasen.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import org.json.JSONObject

class KasenActivity : AppCompatActivity() {

//    val p1 = Picture("Kotlin","https://a99820.xsrv.jp/kasens/kakinomoto_hitomaro.jpg")
//    val p2 = Picture("Android","https://i.pinimg.com/474x/8f/8f/dc/8f8fdc621b4dec14800bfc5fea096dfd.jpg")
//    val p3 = Picture("iOS","https://i.pinimg.com/474x/f4/a8/a5/f4a8a5fb786d94289e659343d2a7b2ed.jpg")
//    val p4 = Picture("Swift","https://i.pinimg.com/474x/ef/03/cf/ef03cfcc9b1aced47106ef2b99030065.jpg")
    val p5 = Picture("三十六歌仙たち","https://i.pinimg.com/474x/3f/32/3a/3f323aef178b260a85f9ab162b31691e.jpg",  "")

//    var pictures = mutableListOf<Picture>(p1,p2,p3,p4,p5)
    var pictures = mutableListOf<Picture>(p5)

    fun fetchKasenData () {
    val client = OkHttpClient().newBuilder()
        .build()
    val request = Request.Builder()
        .url("https://a99820.xsrv.jp/kasen_api.php")
        .method("GET", null)
        .build()

        val response = client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
//            Log.d("777",response.body()!!.string())

            val jsonData = response.body()!!.string()
            val Jarray = JSONArray(jsonData)


            for (i in 0 until Jarray.length()) {
                val item = Jarray.getJSONObject(i)

                Log.d("777",item.toString())

                val pic = Picture(item.optString("name"),item.optString("image_url"), item.optString("description"))
                pictures.add(pic)
            }

            Log.d("777","pictures added. ${pictures.count()}")
        }

        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }}
    )
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kasen)

        title="三十六歌仙"

        fetchKasenData()

        val listView = findViewById(R.id.listView) as ListView


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

    class Picture constructor(name: String, url: String, description: String) {
        val name: String
        val imageUrl: String
        val description: String
        init {
            this.name = name
            this.imageUrl = url
            this.description = description
        }
    }


    class RecipeAdapter(private val context: Context,
                        private val pictures: MutableList<Picture>) : BaseAdapter() {

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
            val descriptionView = rowView.findViewById(R.id.sub_text_view) as TextView
            title.text = picture.name
            descriptionView.text = picture.description

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
