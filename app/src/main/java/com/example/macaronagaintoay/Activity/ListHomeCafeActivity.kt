package com.example.macaronagaintoay.Activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.macaronagaintoay.R
import com.example.macaronagaintoay.viewmodel.ListHomeCafeViewModel
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.macaronagaintoay.Adapter.ListHomeCafeListAdapter
import com.example.macaronagaintoay.databinding.ActivityListhomecafeBinding

class ListHomeCafeActivity : AppCompatActivity() {
    lateinit var binding : ActivityListhomecafeBinding
    private val viewModel : ListHomeCafeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listhomecafe)

        binding = setContentView(this, R.layout.activity_listhomecafe)
        binding.listCafe = viewModel

        val layoutManager = LinearLayoutManager(this)

        val mAdapter = ListHomeCafeListAdapter(this)

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