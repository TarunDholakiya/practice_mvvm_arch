package com.tarun.myapplication.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tarun.myapplication.R
import com.tarun.myapplication.adapter.ItemAdapter
import com.tarun.myapplication.utils.viewModelFactory
import com.tarun.myapplication.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val itemViewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory { ItemViewModel() })
            .get(ItemViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvItems.layoutManager = LinearLayoutManager(this)
        itemViewModel.getItemList()
        observeItems()
    }

    private fun observeItems() {
        itemViewModel.items.observe(this, Observer {
            if (it.isNotEmpty()) {
                rvItems.adapter = ItemAdapter(it)
            } else {
                Toast.makeText(this, getString(R.string.no_results_found), Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}
