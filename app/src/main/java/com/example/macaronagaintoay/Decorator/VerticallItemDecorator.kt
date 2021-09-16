package com.example.macaronagaintoay.Decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticallItemDecorator(private val divHeight : Int) : RecyclerView.ItemDecoration(){

    @Override
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = divHeight

        outRect.bottom = divHeight

    }
}