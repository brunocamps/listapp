package com.accreditaire.listmaker

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoListAdapter(private val lists: ArrayList<TaskList>, val clickListener: TodoListClickListener) : RecyclerView.Adapter<TodoListViewHolder>() {

    // constant of type arrayOf to store the list (old)
    interface TodoListClickListener {
        fun listItemClicked(list: TaskList)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        //First step: Create an instance variable to contain your todo lists
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_view_holder, parent, false)
        return TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.listPositionTextView.text = (position + 1).toString()
        holder.listTitleTextView.text = lists[position].name
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position])
        }
    }

    override fun getItemCount(): Int {
        return lists.size //size of the array
    }

    fun addList(list: TaskList) {
        lists.add(list)
        notifyItemInserted(lists.size-1)
    }

}