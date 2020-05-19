package mm.gov.dtvet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_service.*
import mm.gov.dtvet.adapter.ServiceAdapter
import mm.gov.dtvet.model.Node
import mm.gov.dtvet.model.Services
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class ServiceActivity : AppCompatActivity() {

    lateinit var backbtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

//        var serviceitem = ArrayList<String>()
//        serviceitem.add("အကူအညီရယူရန်")
//        serviceitem.add("ကလေးသူငယ်များ အတွက်အကူအညီရယူရန်")
//        serviceitem.add("မေးလေ့ရှိသော မေးခွန်းများ")
//
//        val arrayadapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1,serviceitem)
//        listview.adapter = arrayadapter

        backbtn = findViewById(R.id.backimage)
        backbtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        listRecyclerView.layoutManager = LinearLayoutManager(this)
        listRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
//        listRecyclerView.adapter = ServiceAdapter()
        fetservicelist()
    }
    fun fetservicelist(){

        val base_URL = "https://dtvet.gov.mm/my/api/services"
        val req = Request.Builder().url(base_URL).build()
        val client = OkHttpClient()

        client.newCall(req).enqueue(object : okhttp3.Callback{
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val body = response?.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val serve = gson.fromJson(body,Services::class.java)

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

