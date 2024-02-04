import android.util.Log
import android.view.View
import com.notation.feature.Canvas.Elements.CanvasElementType
import com.notation.feature.Canvas.Handler.CanvasEraserHandler
import com.notation.feature.Canvas.Manager.CanvasActionState
import com.notation.feature.Canvas.Manager.CanvasActionStateType
import com.notation.feature.Canvas.Manager.CanvasTouchStateType



object ViewTouchManager {
   fun handleTouchEvent(canvasTouchStateType: CanvasTouchStateType, canvasElementType: CanvasElementType, view : View){
       Log.d("테스팅","선위 지나감!")
       val canvasActionStateType = CanvasActionState.state

       when(canvasActionStateType){
           CanvasActionStateType.PENCIL -> return
           CanvasActionStateType.ERASOR -> CanvasEraserHandler.handleEraser(canvasTouchStateType,canvasElementType,view)
       }
   }
}