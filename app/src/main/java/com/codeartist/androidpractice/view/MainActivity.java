package com.codeartist.androidpractice.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codeartist.androidpractice.R;
import com.codeartist.androidpractice.adapters.PropertyAdapter;
import com.codeartist.androidpractice.model.Property;
import com.codeartist.androidpractice.viewmodel.PropertyViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private PropertyViewModel mViewModel;
    private ProgressBar mProgressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "Called ViewModelProvider.get");
        mViewModel = ViewModelProviders.of(this).get(PropertyViewModel.class);
        RecyclerView listView = findViewById(R.id.employees);
        mProgressBar =  findViewById(R.id.progressBar);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                mViewModel.makeQuery();
            }
        });
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this));
        PropertyAdapter adapter = new PropertyAdapter(new ArrayList<Property>());
        listView.setAdapter(adapter);

        mViewModel.properties.observe(this, properties -> {
            Log.i("Observer item size",String.valueOf(mViewModel.properties.getValue().size()));
            mProgressBar.setVisibility(View.GONE);
            adapter.addProperties(properties);
        });

        //Log.i("item size",String.valueOf(mViewModel.properties.getValue().size()));
    }
}

