package com.codeartist.androidpractice.ui

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codeartist.androidpractice.R
import com.codeartist.androidpractice.adapters.PropertyAdapter
import com.codeartist.androidpractice.model.Property
import com.codeartist.androidpractice.viewmodel.SearchResultViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultActivity : AppCompatActivity() {
    private val mViewModel:SearchResultViewModel by viewModels()

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
        fab.visibility = View.GONE
        listView.setHasFixedSize(true)
        listView.layoutManager = LinearLayoutManager(this)
        val adapter = PropertyAdapter()
        listView.adapter = adapter
        mViewModel.filtered.observe(this, { properties: List<Property> ->
            // Log.i("Observer item size",String.valueOf(mViewModel.properties.getValue().size()));
            mProgressBar.setVisibility(View.GONE)
            adapter.addProperties(properties)
        })
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                mViewModel.doSearch(query)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}