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

class EnglanguageActivity : AppCompatActivity() {

    lateinit var backbutton: Button
    lateinit var myanbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_englanguage)

//        var engitem = ArrayList<String>()
//        engitem.add("Where do you want to go?")
//        engitem.add("Safe Migration Information")
//        engitem.add("Preparing For Emergencies")
//        engitem.add("About MICIC")
//        engitem.add("About IOM")
//
//        val arrayadapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1,engitem)
//        listview.adapter = arrayadapter

        // back header button
        backbutton = findViewById(R.id.backimage)
        backbutton.setOnClickListener {
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }

        //myan language for information card
        myanbtn = findViewById(R.id.infomyan)
        myanbtn.setOnClickListener {
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }

        listRecyclerView.layoutManager = LinearLayoutManager(this)
        listRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
//        listRecyclerView.adapter = ServiceAdapter()
        fetchenginfolist()

    }

    fun fetchenginfolist(){

        val base_URL = "https://dtvet.gov.mm/en/api/safe-migration"
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