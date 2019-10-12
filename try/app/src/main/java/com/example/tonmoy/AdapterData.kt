package com.example.tonmoy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults

class AdapterData(val listdata : RealmResults<Data>):RecyclerView.Adapter<AdapterData.ViewHolder>() {

    var p:Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lay_layout, parent, false)
        p = parent.context
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listdata.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = listdata[position]

        holder.task.text = data!!.place
        holder.date.text = data.date
        holder.time.text = data.time
    }




    class ViewHolder(item: View):RecyclerView.ViewHolder(item)
    {
        var task = item.findViewById(R.id.task) as TextView
        var date = item.findViewById(R.id.date) as TextView
        var time = item.findViewById(R.id.time) as TextView
    }
}