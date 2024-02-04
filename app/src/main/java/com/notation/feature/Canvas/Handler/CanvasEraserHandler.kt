package com.notation.feature.Canvas.Handler

import android.view.View
import com.notation.feature.Canvas.Actions.DrawPathAction
import com.notation.feature.Canvas.CanvasView.CanvasPathView
import com.notation.feature.Canvas.Elements.CanvasElementType
import com.notation.feature.Canvas.Manager.CanvasTouchStateType

object CanvasEraserHandler {
    fun handleEraser(canvasTouchStateType: CanvasTouchStateType, canvasElementType: CanvasElementType, view : View){
        when(canvasElementType){
            CanvasElementType.PATH -> CanvasPathHandler.removePath(view)
            else -> return
        }
    }
}