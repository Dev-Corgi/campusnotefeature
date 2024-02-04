package com.notation.feature.Canvas.Manager

import android.graphics.Color
import android.graphics.Paint

object PaintManager {
    var currentPaint : Paint? = null
    init{
        currentPaint = Paint()
        currentPaint!!.color = Color.BLACK
        currentPaint!!.strokeWidth=10f
        currentPaint!!.style=Paint.Style.STROKE
        currentPaint!!.isAntiAlias=true
    }
}