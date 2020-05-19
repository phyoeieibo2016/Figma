package mm.gov.dtvet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.service_item.view.*
import mm.gov.dtvet.R
import mm.gov.dtvet.ServiceActivity
import mm.gov.dtvet.model.Node
import mm.gov.dtvet.model.Nodes
import mm.gov.dtvet.model.Services

class ServiceAdapter(val serve : Services): RecyclerView.Adapter<CustomViewHolder>(){

    val nodeTitles = listOf("First Title", "Second Title", "Third Title")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellrowservice  = layoutInflater.inflate(R.layout.service_item, parent, false)

        return CustomViewHolder(cellrowservice)
    }

    override fun getItemCount(): Int {
        return serve.nodes.count()
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val nodetitles = serve.nodes.get(position)

        holder?.view?.title?.text = nodetitles.node.title
    }
}

class CustomViewHolder(val view : View) : RecyclerView.ViewHolder(view){
    val nodeTitle =view.title as TextView
}
