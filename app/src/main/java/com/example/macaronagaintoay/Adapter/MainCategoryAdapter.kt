package com.example.macaronagaintoay.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.macaronagaintoay.R


class MainCategoryAdapter : RecyclerView.Adapter<MainCategoryAdapter.ViewHolder>() {
    private lateinit var context: Context
    private lateinit var list :List<String>


    fun MainCategoryAdapter(context: Context, list: List<String>) {
        this.context = context
        this.list = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_main_category, null, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var items : String = getItem(position)!!
        holder.item.setText(items)
        holder.item.width = 200

    }


    private fun getItem(position: Int): String? {
        return list[position]
    }


    override fun getItemCount(): Int {
        return list.size
    }


    inner class ViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        public var item : TextView = parent.findViewById(R.id.main_category)


    }


}