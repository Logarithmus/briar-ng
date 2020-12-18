package dev.logarithmus.p2pdroid.wifi.server

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener

class RecyclerItemClickListener : OnItemTouchListener {
    private var mListener: OnItemClickListener? = null
    private var recyclerViewItemClickListener: OnRecyclerViewItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    interface OnRecyclerViewItemClickListener {
        fun onItemClick(parentView: View?, childView: View?, position: Int)
    }

    var mGestureDetector: GestureDetector

    constructor(context: Context?, listener: OnItemClickListener?) {
        mListener = listener
        mGestureDetector = GestureDetector(context, object : SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }
        })
    }

    constructor(context: Context?, listener: OnRecyclerViewItemClickListener?) {
        recyclerViewItemClickListener = listener
        mGestureDetector = GestureDetector(context, object : SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }
        })
    }

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView = view.findChildViewUnder(e.x, e.y)
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener!!.onItemClick(childView, view.indexOfChild(childView))
        } else if (childView != null && recyclerViewItemClickListener != null && mGestureDetector.onTouchEvent(e)) {
            recyclerViewItemClickListener!!.onItemClick(view, childView, view.indexOfChild(childView))
        }
        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}
    override fun onRequestDisallowInterceptTouchEvent(b: Boolean) {}
}