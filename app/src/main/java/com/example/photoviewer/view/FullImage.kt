package com.example.photoviewer.view

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.photoviewer.R
import com.github.chrisbanes.photoview.PhotoView
import java.io.File


class FullImage : AppCompatActivity() {
    private val TAG = "FullImage"
    lateinit var imageViewFullImage: PhotoView
    lateinit var imagePath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image)
        Log.d(TAG, "onCreate")
        bindViews()
        getIntentData()
        clickListener()
    }

    private fun getIntentData() {
        val intent = intent
        if(intent.hasExtra("image_path")){
            imagePath = intent.getStringExtra("image_path")!!
            val file = File(imagePath)
            val imageUri: Uri = Uri.fromFile(file)

            Glide.with(this)
                .load(imageUri)
                .into(imageViewFullImage)
        }
    }

    private fun clickListener() {
        return
    }

    private fun bindViews() {
        imageViewFullImage = findViewById(R.id.imageViewFull)
    }

}