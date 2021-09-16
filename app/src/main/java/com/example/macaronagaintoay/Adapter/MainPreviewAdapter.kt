package com.example.macaronagaintoay.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.macaronagaintoay.Activity.DetailActivity
import com.example.macaronagaintoay.dataclass.Preview
import com.example.macaronagaintoay.R

class MainPreviewAdapter : RecyclerView.Adapter<MainPreviewAdapter.ViewHolder>() {
    lateinit var context : Context
    lateinit var list : MutableList<Preview>


    fun MainPreviewAdapter(context : Context, list: MutableList<Preview>){

        this.context = context
        this.list = list
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_main_card, null, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var items : Preview = getItem(position)!!
        holder.Card_name.text = items.name
        holder.Card_menu.text = items.menu
        holder.Card_pay.text = items.pay
        holder.Card_day.text = items.closed
        holder.Card_address.text = items.address
        holder.Card_image.setImageResource(items.image)

        holder.Card_layout.setOnClickListener {
            var intent = Intent(context, DetailActivity::class.java)
            context.startActivity(intent)
        }



    }

    private fun getItem(position: Int): Preview? {
        return list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        var Card_layout : LinearLayout = parent.findViewById(R.id.card_layout)
        var Card_name : TextView = parent.findViewById(R.id.card_name)
        var Card_menu : TextView = parent.findViewById(R.id.card_menu)
        var Card_pay : TextView = parent.findViewById(R.id.card_pay)
        var Card_day : TextView = parent.findViewById(R.id.card_day)
        var Card_address : TextView = parent.findViewById(R.id.card_address)
        var Card_image : ImageView = parent.findViewById(R.id.card_image)



    }


}