package com.example.macaronagaintoay.Activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.macaronagaintoay.Adapter.ListCafeAdapter
import com.example.macaronagaintoay.R
import com.example.macaronagaintoay.databinding.ActivityListcafeBinding
import com.example.macaronagaintoay.viewmodel.ListCafeViewModel

class ListCafeActivity : AppCompatActivity() {
    lateinit var binding : ActivityListcafeBinding
    private val viewModel : ListCafeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_listcafe)
        binding.listCafe = viewModel
        binding.lifecycleOwner = this@ListCafeActivity



        val layoutManager = LinearLayoutManager(this)

        val mAdapter = ListCafeAdapter(this)

        binding.listItemRv.layoutManager = layoutManager

        binding.listItemRv.adapter = mAdapter

        binding.listCafe!!.listCafe.observe(this, Observer { listcafe->
            listcafe.let {
                mAdapter.setCafeList(it)
                mAdapter.notifyDataSetChanged()

            }
        })

    }


}