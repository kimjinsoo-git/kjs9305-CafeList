package com.example.macaronagaintoay.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.macaronagaintoay.Fragment.DetailFragment.DetailLocationFragment
import com.example.macaronagaintoay.Fragment.DetailFragment.InstagramFragment

@Suppress("DEPRECATION")
class DetailViewPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        if(position == 0){

            return InstagramFragment()

        }else if(position == 1){

            return DetailLocationFragment()

        }
        return InstagramFragment()

    }


}