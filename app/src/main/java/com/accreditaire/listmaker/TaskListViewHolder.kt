package com.accreditaire.listmaker

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // reference to text view
    val taskTextView = itemView?.findViewById<TextView>(R.id.textview_task) as TextView // casting



}