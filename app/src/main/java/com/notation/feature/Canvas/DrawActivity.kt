package com.notation.feature.Canvas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import com.notation.feature.databinding.ActivityDrawBinding
import kotlinx.parcelize.Parcelize

class DrawActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrawBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDrawBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ControlUndo.setOnClickListener{
            binding.PaintArea.setUndo()
        }
        binding.ControlRedo.setOnClickListener{
            binding.PaintArea.setRedo()
        }
        binding.ControlColor.setOnClickListener{
            binding.PaintArea.setColor()
        }
        binding.ControlStroke.setOnClickListener{
            binding.PaintArea.setStroke()
        }
        binding.ControlEraser.setOnClickListener{
            binding.PaintArea.setErasor()
        }




    }




}