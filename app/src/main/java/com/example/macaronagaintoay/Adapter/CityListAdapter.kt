package com.example.macaronagaintoay.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.macaronagaintoay.R

class CityListAdapter : RecyclerView.Adapter<CityListAdapter.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var list : List<String>
    private lateinit var view : RecyclerView

    fun CityListAdapter(context: Context, list : List<String>){
        this.context = context
        this.list = list


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListAdapter.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_bigcity, null, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityListAdapter.ViewHolder, position: Int) {
        var items : String = getItem(position)!!
        holder.item.text = items
    }

    private fun getItem(position: Int): String? {
        return list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(parent: View) : RecyclerView.ViewHolder(parent){
        var item : TextView = parent.findViewById(R.id.city_name_txt)
    }


}