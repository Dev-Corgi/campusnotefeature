package com.notation.feature.Canvas

import CanvasTouchManager
import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.notation.feature.Canvas.Manager.CanvasActionState
import com.notation.feature.Canvas.Manager.CanvasActionStateType
import com.notation.feature.Canvas.Manager.PaintManager
import com.notation.feature.Canvas.Manager.TempCanvasManager
import com.notation.feature.R


class EditLayer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr){
    private var pathList = ArrayList<PaintPath>()
    private var undonePathList = ArrayList<PaintPath>()



    override fun onDraw(canvas: Canvas) {
        canvas!!.drawPath(TempCanvasManager.currentEdit!!,PaintManager.currentPaint!!)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean{
        CanvasTouchManager.handleTouchEvent(event,context,(context as Activity).findViewById(R.id.CompleteArea))
        invalidate()
        return true
        }

    fun setUndo(){
        var size = pathList.size
        if(size>0){
            undonePathList.add(pathList[size -1])
            pathList.removeAt(size -1)
            invalidate()
        }
    }

    fun setRedo(){
        var size = undonePathList.size
        if(size>0){
            pathList.add(undonePathList[size -1])
            undonePathList.removeAt(size -1)
            invalidate()
        }
    }

    fun setColor(){
        PaintManager.currentPaint!!.color = Color.BLUE
    }

    fun setStroke(){
        PaintManager.currentPaint!!.strokeWidth = 20f
        }

    fun setErasor(){
        Log.d("테스팅","지우개 온!")
        CanvasActionState.state = CanvasActionStateType.ERASOR
//        (context as Activity).findViewById<View>(R.id.PaintArea).isEnabled = false
    }

    fun setPencil(){
        CanvasActionState.state = CanvasActionStateType.PENCIL
    }




}

