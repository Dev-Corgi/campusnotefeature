package com.notation.feature.Canvas.Manager

enum class CanvasActionStateType {
    PENCIL,
    ERASOR,
}

enum class CanvasEditStateType {
    EDIT,
    SELECT,
    IMAGE,
}

enum class CanvasTouchStateType {
    NONE,
    DOWN,
    MOVE,
    UP,
}


object CanvasActionState {
    var state : CanvasActionStateType = CanvasActionStateType.PENCIL
}

object CanvasEditState {
}

object CanvasTouchState {
    var state : CanvasTouchStateType = CanvasTouchStateType.NONE
}