package com.example.macaronagaintoay.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.macaronagaintoay.R
import com.naver.maps.map.MapView

class MapFragment : Fragment() {

    lateinit var MapView : MapView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_map, container, false)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MapView = view!!.findViewById(R.id.map_map_view)
        MapView.onCreate(savedInstanceState)

    }
    override fun onStart() {
        super.onStart()
        MapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        MapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        MapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        MapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        MapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        MapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        MapView.onLowMemory()
    }


}