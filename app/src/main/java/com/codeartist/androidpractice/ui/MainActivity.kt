package com.codeartist.androidpractice.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codeartist.androidpractice.R
import com.codeartist.androidpractice.adapters.PropertyAdapter
import com.codeartist.androidpractice.model.Property
import com.codeartist.androidpractice.viewmodel.PropertyViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mViewModel:PropertyViewModel by viewModels()


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
        val adapter = PropertyAdapter()
        listView.adapter = adapter
        mViewModel.properties.observe(this, { properties: List<Property> ->
            // Log.i("Observer item size",String.valueOf(mViewModel.properties.getValue().size()));
            mProgressBar.setVisibility(View.GONE)
            adapter.addProperties(properties)
        })

        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                mViewModel.doSearch(query)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.menu_search).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(true) // Do not iconify the widget; expand it by default
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort -> {
                mViewModel.sort()
            }
            R.id.sortByType -> {
                mViewModel.sortByType()
            }
            R.id.normal -> {
                mViewModel.defaultProperty()
            }
        }
        return true
    }
}