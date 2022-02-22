package com.accreditaire.listmaker

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoListAdapter : RecyclerView.Adapter<TodoListViewHolder>() {

    // constant of type arrayOf to store the list
    private val todoLists = arrayOf("Android development", "House work", "Errands")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        //First step: Create an instance variable to contain your todo lists
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_view_holder, parent, false)
        return TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.listPositionTextView.text = (position + 1).toString()
        holder.listTitleTextView.text = todoLists[position]
    }

    override fun getItemCount(): Int {
        return todoLists.size //size of the array
    }

}