package com.example.macaronagaintoay.Fragment.advertision

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.macaronagaintoay.Activity.DetailActivity
import com.example.macaronagaintoay.R

class AdvertisionSecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v = inflater.inflate(R.layout.fragment_advertision_second, container, false)

        v.findViewById<Button>(R.id.second_next_btn).setOnClickListener {
            var intent = Intent(context, DetailActivity::class.java)
            startActivity(intent)
        }

        return v
    }
}