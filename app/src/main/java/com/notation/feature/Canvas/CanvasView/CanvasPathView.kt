package com.notation.feature.Canvas.CanvasView

import ViewTouchManager
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.notation.feature.Canvas.Elements.CanvasElementType
import com.notation.feature.Canvas.Elements.CanvasPathElement
import com.notation.feature.Canvas.Manager.CanvasTouchStateType

class CanvasPathView(context: Context, attrs: AttributeSet? = null,canvasPathElement : CanvasPathElement) : View(context, attrs) {
    private val path : Path?
    private val paint : Paint?
    private val regionPoints = canvasPathElement.regionPoints!!

    init {
        path = canvasPathElement.path
        paint = canvasPathElement.paint
    }


    override fun onDraw(canvas: Canvas) {
        // Draw your path
        canvas!!.drawPath(path!!, paint!!)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        Log.d("테스팅", "터치감지!")
        for (i in 0 until regionPoints.size-1) {
            if(isPointOnLine(regionPoints[i].first,regionPoints[i].second,regionPoints[i+1].first,regionPoints[i+1].second,event.x,event.y)){
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // Reset path when a new touch starts
                    }
                    MotionEvent.ACTION_MOVE -> {
                        ViewTouchManager.handleTouchEvent(CanvasTouchStateType.MOVE,CanvasElementType.PATH,this)
                    }
                    MotionEvent.ACTION_UP -> {
                        // Handle click event here or perform any necessary action
                        handleClick(x, y)
                    }
                }
            }
        }
        return true
    }

    override fun onGenericMotionEvent(event: MotionEvent?): Boolean {
        Log.d("테스팅", "제네릭")
        return super.onGenericMotionEvent(event)
    }


    private fun handleClick(x: Float, y: Float) {
        // Implement your logic for handling the click event
        // You can check if the touch coordinates (x, y) are within the path
        // For example, you can use path.contains(x, y)
        // If the touch is inside the path, perform the desired action
    }

    private fun isPointOnLine(x1: Float, y1: Float, x2: Float, y2: Float, x: Float, y: Float): Boolean {
        val m = (y2 - y1) / (x2 - x1)
        val b = y1 - m * x1
        val calculatedY = m * x + b
        return -5f< calculatedY - y && calculatedY - y < 5f
    }

}