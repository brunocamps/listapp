package com.accreditaire.listmaker

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // you need to add a primary constructor
    // Construction will take in the View and pass it to its superclass constructor
    //change the actual text within the view

    var listPositionTextView = itemView.findViewById<TextView>(R.id.itemNumber)
    var listTitleTextView = itemView.findViewById<TextView>(R.id.itemString)
}