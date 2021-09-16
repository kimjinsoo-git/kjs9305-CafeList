package com.example.macaronagaintoay.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil.inflate
import com.example.macaronagaintoay.R
import com.example.macaronagaintoay.databinding.FragmentListcafeBinding

class ListCafeFragment : Fragment() {

    lateinit var binding : FragmentListcafeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = inflate(inflater, R.layout.fragment_listcafe, container, false)

        return binding.root
    }
}