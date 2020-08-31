package com.example.photoviewer.util

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import java.util.*

object ImageGallery {
    fun listOfImages(context: Context): ArrayList<String> {
        val uri: Uri
        val cursor: Cursor?
        val column_index_data: Int
        var column_index_folder_name: Int
        val listOfAllImage =
            ArrayList<String>()
        var absolutePathOfImage: String
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.MediaColumns.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        )
        val orderBy = MediaStore.Video.Media.DATE_TAKEN
        cursor = context.contentResolver.query(uri, projection, null, null, "$orderBy DESC")
        column_index_data = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)

        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data)
            listOfAllImage.add(absolutePathOfImage)
        }
        return listOfAllImage
    }
}