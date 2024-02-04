//package com.notation.feature.Canvas
//
//import ViewTouchManager
//import android.util.AttributeSet
//import android.content.Context
//import android.graphics.Canvas
//import android.graphics.Color
//import android.graphics.Paint
//import android.graphics.Path
//import android.util.Log
//import android.view.MotionEvent
//import android.view.View
//import android.widget.FrameLayout
//import android.widget.LinearLayout
//import com.notation.feature.Canvas.Manager.CanvasActionState
//import com.notation.feature.Canvas.Manager.CanvasActionStateType
//import kotlin.math.abs
//
//class DrawPath @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
//) : View(context, attrs, defStyleAttr){
//    private var paint : Paint? = null
//    private var path: Path? = null
//    private var mPath : Path? = null
//    private var pathList = ArrayList<PaintPath>()
//    private var undonePathList = ArrayList<PaintPath>()
//    private var mX:Float?=null
//    private var mY:Float?=null
//    private var touchTolerance: Float = 4f
//    private var currentColor: Int = Color.RED
//    private var currentStroke: Float = 10f
//
//
//    init{
//        mPath = Path()
//        paint = Paint()
//        paint!!.color = Color.RED
//        paint!!.strokeWidth=10f
//        paint!!.style=Paint.Style.STROKE
//        paint!!.isAntiAlias=true
//    }
//
//    override fun onDraw(canvas: Canvas) {
//        Log.d("테스팅","그리기전 ${pathList.size}")
////        if(pathList.size>0) {
////            for (path in pathList) {
////                Log.d("테스팅","그리기!")
////                paint!!.color = path.color
////                paint!!.strokeWidth = path.stroke
////                canvas!!.drawPath(path.path, paint!!)
////            }
////        }
//        canvas!!.drawPath(mPath!!,paint!!)
//    }
//
//    override fun onTouchEvent(event: MotionEvent?): Boolean{
//        val xPos : Float = event!!.x
//        val yPos : Float = event.y
//
//
//                when(event!!.action){
//
//                    MotionEvent.ACTION_DOWN->{
//                        penciltouchStart(xPos,yPos)
//                        invalidate()
//                    }
//                    MotionEvent.ACTION_MOVE-> {
//                        penciltouchMove(xPos,yPos)
//                        invalidate()
//                    }
//
//                    MotionEvent.ACTION_UP->{
//                        penciltouchUp()
//                        invalidate()
//                    }
//
//                    else-> {
//
//                    }
//
//        }
//        return true
//        }
//
//    private fun penciltouchStart(xPos: Float, yPos: Float) {
////        mPath = Path()
////        val paintPath = PaintPath(mPath!!,currentColor,currentStroke)
////        pathList.add(paintPath)
////        mPath!!.reset()
//        mPath!!.moveTo(xPos,yPos)
////        mX=xPos
////        mY=yPos
//        Log.d("테스팅","시작")
//    }
//
//    private fun penciltouchMove(xPos: Float, yPos: Float) {
////        val dX:Float = abs( xPos-mX!!)
////        val dY:Float = abs(yPos-mY!!)
////        if(dX>=touchTolerance||dY>=touchTolerance){
////            mPath!!.quadTo(mX!!,mY!!,(xPos+mX!!)/2,(yPos+mY!!)/2)
////            mX=xPos
////            mY=yPos
////        }
//
//        mPath!!.lineTo(xPos,yPos)
//
//    }
//
//    private fun penciltouchUp() {
////       mPath!!.lineTo(mX!!,mY!!)
//        Log.d("테스팅","종료")
//    }
//
//
//
//    fun setUndo(){
//        var size = pathList.size
//        if(size>0){
//            undonePathList.add(pathList[size -1])
//            pathList.removeAt(size -1)
//            invalidate()
//        }
//    }
//
//    fun setRedo(){
//        var size = undonePathList.size
//        if(size>0){
//            pathList.add(undonePathList[size -1])
//            undonePathList.removeAt(size -1)
//            invalidate()
//        }
//    }
//
//    fun setColor(){
//        currentColor = Color.BLUE
//    }
//
//    fun setStroke(){
//        currentStroke = 20f
//        }
//
//    fun setErasor(){
//        CanvasActionState.state = CanvasActionStateType.ERASOR
//    }
//
//    fun setPencil(){
//        CanvasActionState.state = CanvasActionStateType.PENCIL
//    }
//
//    }
//
