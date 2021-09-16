package com.example.macaronagaintoay.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.macaronagaintoay.Adapter.DetailViewPagerAdapter
import com.example.macaronagaintoay.R
import com.google.android.material.tabs.TabLayout

class DetailActivity : AppCompatActivity() {

    lateinit var Main_ViewPager : ViewPager
    lateinit var Main_TabLayout : TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        init()

        Main_TabLayout.addTab(Main_TabLayout.newTab().setIcon(R.drawable.instagram))
        Main_TabLayout.addTab(Main_TabLayout.newTab().setIcon(R.drawable.map))
        Main_TabLayout.tabGravity = TabLayout.GRAVITY_FILL

        var pageAdapter = DetailViewPagerAdapter(supportFragmentManager)

        Main_ViewPager.adapter = pageAdapter

        Main_ViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(Main_TabLayout))

        Main_TabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Main_ViewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })





    }



   fun init(){

       Main_ViewPager = findViewById(R.id.detail_viewpager)
       Main_TabLayout = findViewById(R.id.detail_tablayout)

    }
}