package com.example.macaronagaintoay.Fragment.advertision

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.macaronagaintoay.R

class AdvertisionFirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v = inflater.inflate(R.layout.fragment_advertision_first, container, false)

        v.findViewById<Button>(R.id.first_next_btn).setOnClickListener {
            Navigation.findNavController(v).navigate(R.id.action_fragment_main_to_listCafeFragment)
        }

        return v
    }
}