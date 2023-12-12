package com.example.spotifyclone.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BottomOffSetDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val size = state.itemCount
        val position = parent.getChildAdapterPosition(view)
        if (size > 0 && position == size - 1) {
            outRect.set(0, 0, 0, offset)
        } else {
            outRect.set(0, 0, 0, 0)
        }
    }
}