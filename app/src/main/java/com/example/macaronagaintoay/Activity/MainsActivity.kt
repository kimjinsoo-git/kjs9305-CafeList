package com.example.macaronagaintoay.Activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.macaronagaintoay.Adapter.ViewpagerAdapter
import com.example.macaronagaintoay.Fragment.*
import com.example.macaronagaintoay.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class MainsActivity : AppCompatActivity() {



    lateinit var Main_ViewPager : ViewPager
    lateinit var Main_TabLayout : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mains)

        init()

        Main_TabLayout.addTab(Main_TabLayout.newTab().setIcon(R.drawable.round_home_black_48))
        Main_TabLayout.addTab(Main_TabLayout.newTab().setIcon(R.drawable.search))
        Main_TabLayout.addTab(Main_TabLayout.newTab().setIcon(R.drawable.baseline_favorite_border_black_48))
        Main_TabLayout.addTab(Main_TabLayout.newTab().setIcon(R.drawable.baseline_location_on_black_48))
        Main_TabLayout.addTab(Main_TabLayout.newTab().setIcon(R.drawable.baseline_person_black_48))
        Main_TabLayout.tabGravity = TabLayout.GRAVITY_FILL

        var pageAdapter = ViewpagerAdapter(supportFragmentManager)

        Main_ViewPager.adapter = pageAdapter

        Main_ViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(Main_TabLayout))

        Main_TabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Main_ViewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }

    fun init(){

    Main_ViewPager = findViewById(R.id.Main_ViewPager)
    Main_TabLayout = findViewById(R.id.Main_TabLayout)


    }




}