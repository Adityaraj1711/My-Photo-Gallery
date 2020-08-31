package com.example.photoviewer

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photoviewer.adapter.GalleryAdapter
import com.example.photoviewer.clicklistener.PhotoListener
import com.example.photoviewer.util.ImageGallery
import com.example.photoviewer.view.FullImage

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var galleryAdapter: GalleryAdapter
    lateinit var images : List<String>
    lateinit var galleryNumber : TextView
    val TAG = "MainActivity"
    val MY_READ_PERMISSION_CODE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")
        galleryNumber = findViewById(R.id.gallery_number)
        recyclerView = findViewById(R.id.recyclerview_gallery_images)

        // permission check
        val listPermissionNeeded = ArrayList<String>()
        if (ContextCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            listPermissionNeeded.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this, listPermissionNeeded.toTypedArray<String>(), MY_READ_PERMISSION_CODE)
            Log.d(TAG, "permission granted")
        } else {
            Log.d(TAG, "permission requested")
            loadImages()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == MY_READ_PERMISSION_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "read external storage permission granted", Toast.LENGTH_SHORT)
                loadImages()
            } else {
                Toast.makeText(this, "read external storage permission not denied", Toast.LENGTH_SHORT)
            }
        }
    }
    private fun loadImages() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 4)
        images = ImageGallery.listOfImages(this)
        galleryAdapter = GalleryAdapter(this, images, object : GalleryAdapter.PhotoListener{
            override fun onPhotoClick(path: String) {
                Toast.makeText(this@MainActivity,  path.toString(), Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, FullImage::class.java)
                intent.putExtra("image_path", path)
                startActivity(intent)
            }
        })
        recyclerView.adapter = galleryAdapter
        galleryNumber.text = "Photos(" + images.size + ")"
        Log.d(TAG, galleryNumber.text.toString())
        Log.d(TAG, images.toString())

    }
}