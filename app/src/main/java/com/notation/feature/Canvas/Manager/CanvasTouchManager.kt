import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.notation.feature.Canvas.Elements.CanvasElementType
import com.notation.feature.Canvas.Handler.CanvasEraserHandler
import com.notation.feature.Canvas.Handler.CanvasPathHandler
import com.notation.feature.Canvas.Manager.CanvasActionState
import com.notation.feature.Canvas.Manager.CanvasActionStateType
import com.notation.feature.Canvas.Manager.CanvasTouchStateType




object CanvasTouchManager {
   fun handleTouchEvent(event: MotionEvent,context: Context, view : FrameLayout){
        val canvasActionStateType = CanvasActionState.state

       when(canvasActionStateType){
           CanvasActionStateType.PENCIL -> CanvasPathHandler.drawPath(event,context,view)
           CanvasActionStateType.ERASOR -> return
       }
   }
}