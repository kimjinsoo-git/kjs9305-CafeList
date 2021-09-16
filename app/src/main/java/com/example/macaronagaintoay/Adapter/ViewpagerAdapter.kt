package com.example.macaronagaintoay.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.macaronagaintoay.Fragment.*


@Suppress("DEPRECATION")
class ViewpagerAdapter(fm : FragmentManager ) : FragmentStatePagerAdapter(fm){

    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0){
            return MainFragment()
        } else if(position == 1){
            return SearchFragment()
        } else if(position == 2){
            return FavoritesFragment()
        } else if(position == 3){
            return MapFragment()
        } else if(position == 4){
            return ProfileFragment()
        }
        return MainFragment()
    }


}