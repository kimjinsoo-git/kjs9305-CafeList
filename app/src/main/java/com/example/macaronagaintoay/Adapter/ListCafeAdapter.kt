package com.example.macaronagaintoay.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.macaronagaintoay.Entity.CafeEntity
import com.example.macaronagaintoay.R

class ListCafeAdapter(context : Context) : RecyclerView.Adapter<ListCafeAdapter.ListCafeHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val items = ArrayList<CafeEntity>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListCafeAdapter.ListCafeHolder {
        var itemview = inflater.inflate(R.layout.item_list_cafe, parent, false)

        return ListCafeHolder(itemview)
    }

    override fun onBindViewHolder(
        holder: ListCafeAdapter.ListCafeHolder,
        position: Int
    ) {
        val cafeList = items[position]

        holder.titleTxt.text = cafeList.cafeName
        holder.noticeTxt.text = cafeList.cafeType
        if(cafeList.OpeningHours.isEmpty()){
            holder.openTimeTxt.text = "-"
        }else{
            holder.openTimeTxt.text = cafeList.OpeningHours
        }
        holder.addressTxt.text = cafeList.NewAddress
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setCafeList(CafeList : List<CafeEntity>){
        items.clear()
        items.addAll(CafeList)
    }

    inner class ListCafeHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var titleTxt : TextView = itemView.findViewById(R.id.title_name_txt)
        var noticeTxt : TextView = itemView.findViewById(R.id.notice_txt)
        var openTimeTxt : TextView = itemView.findViewById(R.id.opentime_txt)
        var addressTxt : TextView = itemView.findViewById(R.id.address_txt)
    }


}