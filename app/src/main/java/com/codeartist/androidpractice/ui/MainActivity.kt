package com.codeartist.androidpractice.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codeartist.androidpractice.PropertyApplication
import com.codeartist.androidpractice.R
import com.codeartist.androidpractice.adapters.PropertyAdapter
import com.codeartist.androidpractice.model.Property
import com.codeartist.androidpractice.viewmodel.PropertyViewModel
import com.codeartist.androidpractice.viewmodel.PropertyViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mViewModel by lazy {
        ViewModelProvider(this, PropertyViewModelFactory((application as PropertyApplication)
                .repository)).get(PropertyViewModel::class.java)
        // PropertyViewModelFactory((application as PropertyApplication).repository).get(MyViewModel::class.java)

    }

    //private lateinit var mViewModel: PropertyViewModel
    private lateinit var mProgressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MainActivity", "Called ViewModelProvider.get")
        //val repository = Repository.getInstance(this.applicationContext)
        // mViewModel = ViewModelProviders.of(this, PropertyViewModelFactory(repository)).get(PropertyViewModel::class.java)
        val listView = findViewById<RecyclerView>(R.id.employees)
        mProgressBar = findViewById(R.id.progressBar)
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            mProgressBar.setVisibility(View.VISIBLE)
            mViewModel.makeQuery()
        }
        listView.setHasFixedSize(true)
        listView.layoutManager = LinearLayoutManager(this)
        val adapter = PropertyAdapter(ArrayList())
        listView.adapter = adapter
        mViewModel.properties.observe(this, { properties: List<Property> ->
            // Log.i("Observer item size",String.valueOf(mViewModel.properties.getValue().size()));
            mProgressBar.setVisibility(View.GONE)
            adapter.addProperties(properties)
        })

        //Log.i("item size",String.valueOf(mViewModel.properties.getValue().size()));
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort -> {

                // do your sign-out stuff
                mViewModel.sort()
            }
            R.id.sortByType -> {

                // do your sign-out stuff
                mViewModel.sortByType()
            }
        }
        return true
    }
}