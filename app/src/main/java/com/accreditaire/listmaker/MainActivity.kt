package com.accreditaire.listmaker

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accreditaire.listmaker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TodoListAdapter.TodoListClickListener {


    private lateinit var binding: ActivityMainBinding
    private lateinit var todoListRecyclerView: RecyclerView
    // instantiate
    private val listDataManager: ListDataManager = ListDataManager(this)

    companion object {
        const val INTENT_LIST_KEY = "list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val lists = listDataManager.readLists()
        todoListRecyclerView = findViewById(R.id.listsRecyclerView)
        todoListRecyclerView.layoutManager = LinearLayoutManager(this)
        todoListRecyclerView.adapter = TodoListAdapter(lists, this)
        // at this point you need to create an adapter.


        binding.fab.setOnClickListener { _ ->
//            val adapter = todoListRecyclerView.adapter as TodoListAdapter //cast
//            adapter.addNewItem()
            showCreateTodoListDialog()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun myTestMethod() {
        println("Hello")
    }

    private fun showCreateTodoListDialog() {
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)
        val myDialog = AlertDialog.Builder(this) // this: Pass on the activity
        val todoTitleEditText = EditText(this) //this: pass on the activity
        todoTitleEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

        myDialog.setTitle(dialogTitle)
        myDialog.setView(todoTitleEditText)

        myDialog.setPositiveButton(positiveButtonTitle) {
            dialog, _ ->
                val adapter = todoListRecyclerView.adapter as TodoListAdapter
                val list = TaskList(todoTitleEditText.text.toString())
                listDataManager.saveList(list)
                adapter.addList(list)
                showTaskListItems(list)
                dialog.dismiss()
        }
        myDialog.create().show()
    }

    private fun showTaskListItems(list: TaskList) {
        val taskListItem = Intent(this, DetailActivity::class.java)
        taskListItem.putExtra(INTENT_LIST_KEY, list)
        startActivity(taskListItem)
    }

    override fun listItemClicked(list: TaskList) {
        showTaskListItems(list)
    }


//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}