package com.example.macaronagaintoay.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.macaronagaintoay.R

class BigCityListAdapter : RecyclerView.Adapter<BigCityListAdapter.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var list : List<String>
    private lateinit var view : RecyclerView

    fun BigCityListAdapter(context: Context, list : List<String>, view : RecyclerView){
        this.context = context
        this.list = list
        this.view = view

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BigCityListAdapter.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_bigcity, null, false)

        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: BigCityListAdapter.ViewHolder, position: Int) {
        var items : String = getItem(position)!!
        holder.item.text = items




    }

    private fun getItem(position: Int): String? {
        return list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(parent : View) : RecyclerView.ViewHolder(parent){
        var layout : LinearLayoutCompat = parent.findViewById(R.id.layout_bigcity)
        var item : TextView = parent.findViewById(R.id.city_name_txt)
    }


}