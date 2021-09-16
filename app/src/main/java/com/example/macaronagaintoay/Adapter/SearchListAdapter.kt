package com.example.macaronagaintoay.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.macaronagaintoay.Dao.OnItemClick
import com.example.macaronagaintoay.Entity.SearchEntity
import com.example.macaronagaintoay.R


class SearchListAdapter(context : Context, listener: OnItemClick)
    :RecyclerView.Adapter<SearchListAdapter.SearchViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val mCallback = listener
    private val items = ArrayList<SearchEntity>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):SearchViewHolder {
        var itemview = inflater.inflate(R.layout.item_search,parent,false)
        return SearchViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val current = items[position]
        holder.maincategory.text = current.search
        holder.xicon.setOnClickListener{
            mCallback.deleteItem(current)
        }



    }


    fun setSearch(search: List<SearchEntity>){
        items.clear()
        items.addAll(search)

    }


    override fun getItemCount(): Int {
        return items.size
    }


    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val maincategory : TextView = itemView.findViewById(R.id.main_category)
        val xicon : ImageView = itemView.findViewById(R.id.item_search_xicon)
    }


}
