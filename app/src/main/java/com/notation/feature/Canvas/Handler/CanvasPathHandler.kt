package com.notation.feature.Canvas.Handler

import android.R.attr.height
import android.R.attr.width
import android.content.Context
import android.graphics.Color
import android.graphics.Path
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AbsoluteLayout
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.notation.feature.Canvas.CanvasView.CanvasPathView
import com.notation.feature.Canvas.Elements.CanvasPathElement
import com.notation.feature.Canvas.Manager.TempCanvasManager
import kotlin.math.abs


object CanvasPathHandler {
    private var mpath : Path? = null
    private var mX:Float? = null
    private var mY:Float? = null
    private var touchTolerance: Float = 4f

    fun drawPath(event : MotionEvent,context: Context,view: FrameLayout){
        val xPos : Float = event.x
        val yPos : Float = event.y

                when(event.action){

                    MotionEvent.ACTION_DOWN->{
                        penciltouchStart(xPos,yPos)
                    }
                    MotionEvent.ACTION_MOVE-> {
                        penciltouchMove(xPos,yPos)
                    }

                    MotionEvent.ACTION_UP->{
                        penciltouchUp(context,view)
                    }

                    else-> {

                    }
                }
    }

    private fun completePath(context: Context,view : FrameLayout){
        val canvasPathElement = CanvasPathElement(mpath!!)
        val canvasPathView = CanvasPathView(context,null,canvasPathElement)
        val params = FrameLayout.LayoutParams(canvasPathElement.width.toInt(), canvasPathElement.height.toInt())  // 너비 300, 높이 200
        params.leftMargin = canvasPathElement.xPos.toInt()  // x 위치
        params.topMargin = canvasPathElement.yPos.toInt()   // y 위치

        canvasPathView.setBackgroundColor(Color.BLUE)

        view.addView(canvasPathView,params)
    }

    fun removePath(view : View){
        (view.getParent() as? FrameLayout)?.removeView(view)
    }

    private fun penciltouchStart(xPos: Float, yPos: Float) {
        TempCanvasManager.currentEdit = null
        mpath = Path()
        TempCanvasManager.currentEdit = mpath
        mpath!!.reset()
        mpath!!.moveTo(xPos,yPos)
        mX=xPos
        mY=yPos
    }

    private fun penciltouchMove(xPos: Float, yPos: Float) {
        val dX:Float = abs( xPos-mX!!)
        val dY:Float = abs(yPos-mY!!)
        if(dX>=touchTolerance||dY>=touchTolerance){
            mpath!!.quadTo(mX!!,mY!!,(xPos+mX!!)/2,(yPos+mY!!)/2)
            mX=xPos
            mY=yPos
        }
    }

    private fun penciltouchUp(context: Context,view: FrameLayout) {
        mpath!!.lineTo(mX!!,mY!!)
        completePath(context,view)
    }





}