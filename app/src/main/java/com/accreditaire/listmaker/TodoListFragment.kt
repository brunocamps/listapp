package com.accreditaire.listmaker

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TodoListFragment : Fragment(), TodoListAdapter.TodoListClickListener { // this class
    //implements the methods after :

    private lateinit var todoListRecyclerView: RecyclerView
    // instantiate
    private lateinit var listDataManager: ListDataManager

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lists = listDataManager.readLists()
        todoListRecyclerView = view.findViewById(R.id.listsRecyclerView)
        todoListRecyclerView.layoutManager = LinearLayoutManager(activity)
        todoListRecyclerView.adapter = TodoListAdapter(lists, this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            //listener = context
            listDataManager = ListDataManager(context)
        }
    }

    interface OnFragmentInteractionListener {
        fun onTodoListClicked(list: TaskList)
    }



    companion object {
        @JvmStatic
        fun newInstance(): TodoListFragment {
            return TodoListFragment()
        }
    }

    override fun listItemClicked(list: TaskList) {
        listener?.onTodoListClicked(list)
    }

    fun addList(list: TaskList) {
        listDataManager.saveList(list)
        val todoAdapter = todoListRecyclerView.adapter as TodoListAdapter
        todoAdapter.addList(list)


    }

    fun saveList(list: TaskList) {
        listDataManager.saveList(list)
        updateLists()
    }

    private fun updateLists() {
        val lists = listDataManager.readLists()
        todoListRecyclerView.adapter = TodoListAdapter(lists, this)
    }
}