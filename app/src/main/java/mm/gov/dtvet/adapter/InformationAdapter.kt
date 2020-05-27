package mm.gov.dtvet.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.service_item.view.*
import mm.gov.dtvet.R
import mm.gov.dtvet.ServiceDetailsActivity
import mm.gov.dtvet.model.Services

class InformationAdapter(val info : Services): RecyclerView.Adapter<InfoViewHolder>(){

    val nodeTitles = listOf("First Title", "Second Title", "Third Title")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellrowinfo  = layoutInflater.inflate(R.layout.information_item, parent, false)

        return InfoViewHolder(cellrowinfo)
    }

    override fun getItemCount(): Int {
        return info.nodes.count()
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {

        val nodetitles = info.nodes.get(position)

        holder?.view?.title?.text = nodetitles.node.title
    }

}

class InfoViewHolder(val view : View) : RecyclerView.ViewHolder(view){
    val nodeTitle =view.title as TextView
    init {
        view.setOnClickListener {
            val intent = Intent(view.context, ServiceDetailsActivity::class.java)
            view.context.startActivity(intent)
        }
    }
}