package com.example.macaronagaintoay.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.macaronagaintoay.Entity.CafeEntity
import com.example.macaronagaintoay.R

class ListHomeCafeListAdapter(context : Context) : RecyclerView.Adapter<ListHomeCafeListAdapter.ListHomeCafeListHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val items = ArrayList<CafeEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListHomeCafeListAdapter.ListHomeCafeListHolder {
        var itemview = inflater.inflate(R.layout.item_list_cafe, parent, false)

        return ListHomeCafeListHolder(itemview)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: ListHomeCafeListAdapter.ListHomeCafeListHolder,
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

    fun setCafeList(CafeList : List<CafeEntity>){
        items.clear()
        items.addAll(CafeList)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ListHomeCafeListHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var titleTxt : TextView = itemView.findViewById(R.id.title_name_txt)
        var noticeTxt : TextView = itemView.findViewById(R.id.notice_txt)
        var openTimeTxt : TextView = itemView.findViewById(R.id.opentime_txt)
        var addressTxt : TextView = itemView.findViewById(R.id.address_txt)

    }
}