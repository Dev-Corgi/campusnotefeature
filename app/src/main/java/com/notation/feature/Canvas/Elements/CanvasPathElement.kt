package com.notation.feature.Canvas.Elements

import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.RectF
import com.notation.feature.Canvas.Manager.CanvasLayerManager
import com.notation.feature.Canvas.Manager.PaintManager


class CanvasPathElement(
var path :Path
) : CanvasElement(
) {
    val points : Array<Pair<Float,Float>>
    val color : String
    val strokeWidth : Float
    val regionPoints : Array<Pair<Float,Float>>
    val paint : Paint?
    val startPoint : FloatArray

    init {
        type = CanvasElementType.PATH
        layerNum = CanvasLayerManager.layerNum
        paint = PaintManager.currentPaint
        color = String.format("#%06X", 0xFFFFFF and PaintManager.currentPaint!!.color)
        strokeWidth = PaintManager.currentPaint!!.strokeWidth

        val bounds = RectF()
        path.computeBounds(bounds, true)
        xPos = bounds.left - PaintManager.currentPaint!!.strokeWidth / 2
        yPos = bounds.top - PaintManager.currentPaint!!.strokeWidth / 2
        width = bounds.width() + PaintManager.currentPaint!!.strokeWidth
        height = bounds.height() + PaintManager.currentPaint!!.strokeWidth

        path = translatePath(path,-xPos,-yPos)

        val pathMeasure = PathMeasure(path, false)
        startPoint = getStartPoint(pathMeasure)
        pathMeasure.getPosTan(0f, startPoint, null)
        points = getAllPointsFromPath(pathMeasure)
        regionPoints = getEquallySpacedPoints(pathMeasure, 10f)
    }


    private fun getAllPointsFromPath(pathMeasure: PathMeasure): Array<Pair<Float, Float>> {
        val pointsArray = mutableListOf<Pair<Float, Float>>()

        val length = pathMeasure.length
        val distance = 0.1f // Adjust this value to control the distance between points

        val position = FloatArray(2)

        for (i in 0 until (length / distance).toInt()) {
            val distanceToPath = i * distance
            pathMeasure.getPosTan(distanceToPath, position, null)

            val point = Pair(position[0], position[1])
            pointsArray.add(point)
        }

        return pointsArray.toTypedArray()
    }

    private fun getEquallySpacedPoints(pathMeasure: PathMeasure, interval: Float): Array<Pair<Float, Float>> {
        val pathLength = pathMeasure.length
        val points = mutableListOf<Pair<Float, Float>>()

        var distance = 0f
        while (distance <= pathLength) {
            val coords = FloatArray(2)
            pathMeasure.getPosTan(distance, coords, null)
            points.add(coords[0] to coords[1])
            distance += interval
        }

        return points.toTypedArray()
    }

    fun getStartPoint(pathMeasure: PathMeasure): FloatArray {
        val startPoint = FloatArray(2)

        // Get the start point of the path
        pathMeasure.getPosTan(0f, startPoint, null)

        return startPoint
    }

    fun translatePath(path: Path, dx: Float, dy: Float): Path {
        val matrix = Matrix()
        matrix.setTranslate(dx, dy)

        val translatedPath = Path()
        path.transform(matrix, translatedPath)

        return translatedPath
    }
}