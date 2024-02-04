package com.notation.feature.Canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TouchDetectingPathView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val path = Path()
    private val paint = Paint().apply {
        color = android.graphics.Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    private val touchDetectionPoints = mutableListOf<Pair<Float, Float>>()

    init {
        // Initialize touch detection points
        for (i in 0 until 10) {
            touchDetectionPoints.add(Pair(0f, 0f))
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
                updateTouchDetectionPoints(event.x, event.y)
                checkTouchOnLines(event)
            }
            MotionEvent.ACTION_UP -> {
                // Handle touch up event if needed
            }
        }
        invalidate()
        return true
    }

    private fun updateTouchDetectionPoints(x: Float, y: Float) {
        val pathMeasure = android.graphics.PathMeasure(path, false)
        for (i in 0 until 10) {
            val distance = pathMeasure.length * (i + 1) / 10
            val coords = FloatArray(2)
            pathMeasure.getPosTan(distance, coords, null)
            touchDetectionPoints[i] = Pair(coords[0], coords[1])
        }
    }

    private fun checkTouchOnLines(event: MotionEvent) {
        for (i in 0 until 9) {
            val lineStart = touchDetectionPoints[i]
            val lineEnd = touchDetectionPoints[i + 1]
            if (isPointOnLine(lineStart.first, lineStart.second, lineEnd.first, lineEnd.second, event.x, event.y)) {
                // Handle touch on the line
                // You can add your logic here
            }
        }
    }

    private fun isPointOnLine(x1: Float, y1: Float, x2: Float, y2: Float, x: Float, y: Float): Boolean {
        val distance1 = Math.hypot((x - x1).toDouble(), (y - y1).toDouble())
        val distance2 = Math.hypot((x - x2).toDouble(), (y - y2).toDouble())
        val lineLength = Math.hypot((x2 - x1).toDouble(), (y2 - y1).toDouble())
        val buffer = 0.1 // Adjust the buffer according to your preference

        return distance1 + distance2 >= lineLength - buffer && distance1 + distance2 <= lineLength + buffer
    }
}
