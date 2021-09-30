package com.example.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.ShoppingDatabase
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.data.repositories.ShoppingRepository
import com.example.shoppinglist.other.ShoppingItemAdapter
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {
    lateinit var viewModel : ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        viewModel = ViewModelProviders.of(this , factory).get(ShoppingViewModel::class.java)


        val adapter = ShoppingItemAdapter(listOf()  , viewModel)
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItem().observe(this , Observer {
            adapter.item = it
            adapter.notifyDataSetChanged()
        })



//        fab.setOnClickListener {
//            AddShoppingItemDialog(this, object : AddDialogListener{
//                override fun onAddButtonClicked(item: ShoppingItem) {
//                    viewModel.upsert(item)
//                }
//            }).show()
//        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.add( 0 ,0,0,"Add")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.title){
            "Add"-> {
                AddShoppingItemDialog(this, object : AddDialogListener{
                    override fun onAddButtonClicked(item: ShoppingItem) {

                        viewModel.upsert(item)
                    }
                }).show()
            }

        }
        return super.onOptionsItemSelected(item)
    }


}