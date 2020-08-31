package com.example.photoviewer.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photoviewer.R
import java.security.cert.CertPath

class GalleryAdapter(val context: Context, var images: List<String>, var photoListener: PhotoListener)
    : RecyclerView.Adapter<GalleryAdapter.ViewHolder>(){    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images[position]
        Glide.with(context).load(image).into(holder.imageView)
        // now set the text in title and description of blog_layout
        holder.itemView.setOnClickListener { photoListener.onPhotoClick(image) }
    }

    public interface PhotoListener{
        fun onPhotoClick(path: String)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var imageView: ImageView = itemView.findViewById(R.id.image)
    }


}

