package mm.gov.dtvet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_service.*
import mm.gov.dtvet.adapter.ServiceAdapter
import mm.gov.dtvet.model.Services
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class InformationActivity : AppCompatActivity() {

    lateinit var englang: Button
    lateinit var backbutton1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

//        var informationitem = ArrayList<String>()
//        informationitem.add("သင်ဘယ်နိုင်ငံသွားချင်လဲ")
//        informationitem.add("လုံခြုံစိတ်ချရသော ရွှေ့ပြောင်းသွားလာမှု")
//        informationitem.add("အရေးပေါ် အခြေအနေဆိုင်ရာ ကြိုတင်ပြင်ဆင်မှု")
//        informationitem.add("MICIC အကြောင်း")
//        informationitem.add("IOM အကြောင်း")
//
//
//        val arrayadapter =
//            ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, informationitem)
//        listview.adapter = arrayadapter


        // language for information card

        englang = findViewById(R.id.infoeng)
        englang.setOnClickListener {
            val intent = Intent(this, EnglanguageActivity::class.java)
            startActivity(intent)
        }

        // back header button, when click back button then go to home page
        backbutton1 = findViewById(R.id.backimage)
        backbutton1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        listRecyclerView.layoutManager = LinearLayoutManager(this)
        listRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        fetchinformationlist()

    }

    fun fetchinformationlist(){

        val base_URL = "https://dtvet.gov.mm/my/api/safe-migration"
        val req = Request.Builder().url(base_URL).build()
        val client = OkHttpClient()

        client.newCall(req).enqueue(object : okhttp3.Callback{
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val body = response?.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val serve = gson.fromJson(body, Services::class.java)

                runOnUiThread {
                    listRecyclerView.adapter = ServiceAdapter(serve)
                }
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("Failed to execute the data")
            }
        })
    }
}