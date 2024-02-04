package com.notation.feature.Canvas.Manager

import android.graphics.Path

object TempCanvasManager {
    var currentEdit : Path? = null
    init{
        currentEdit = Path()
    }
}