package com.accreditaire.listmaker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TaskListAdapter(var list: TaskList) : RecyclerView.Adapter<TaskListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        // you're going to need a layout for this
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.task_view_holder, parent, false)
        return TaskListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        holder.taskTextView?.text = list.tasks[position]
    }

    override fun getItemCount(): Int {
        return list.tasks.size
    }
}