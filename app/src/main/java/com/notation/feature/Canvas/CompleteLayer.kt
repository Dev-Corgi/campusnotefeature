package com.notation.feature.Canvas

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout


class CompleteLayer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr){


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event!!.x.toInt()
        val y = event!!.y.toInt()
        val resultViewList =  findViewsInBoundary(this, x, y)
        Log.d("테스팅","사이즈 ${resultViewList.size}")
        if (resultViewList.size != 0) {
            for(i in 0 until resultViewList.size) {
                resultViewList[i].dispatchTouchEvent(event)
            }
        }
        return true
    }

    private fun findViewsInBoundary(view: View, x: Int, y: Int) : MutableList<View> {
        val resultViewList = mutableListOf<View>()
        if (view is ViewGroup) {
            val viewGroup = view
            for (i in 0 until viewGroup.childCount) {
                val child = viewGroup.getChildAt(i)
                val childBounds = Rect()
                child.getLocalVisibleRect(childBounds)
                if (childBounds.contains(x, y)) {
                    resultViewList.add(child)
//                    println("Found view at (" + x + ", " + y + "): " + child.javaClass.simpleName)
                }
            }
        }
        return resultViewList
    }

}

