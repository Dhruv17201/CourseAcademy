package com.example.courseacademy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CourseAdapter(var courselist:ArrayList<course>)
    : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.get_courselist, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val currentitem = courselist[position]
        holder.getitle.text = currentitem.title
        holder.starrating.text = currentitem.rating
        holder.courseimg  = currentitem.courseimg
    }

    override fun getItemCount(): Int {
        return courselist.size
    }

    class CourseViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

            var getitle:TextView = view.findViewById(R.id.title)
            var starrating:TextView = view.findViewById(R.id.rating)
            var courseimg:ImageView = view.findViewById(R.id.courseimg)



    }
}

