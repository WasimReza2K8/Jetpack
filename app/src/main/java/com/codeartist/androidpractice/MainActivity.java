package com.codeartist.androidpractice;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codeartist.androidpractice.adapters.EmployeeAdapter;
import com.codeartist.androidpractice.viewmodel.EmployeeViewModel;

public class MainActivity extends AppCompatActivity {


    String userJson = "[{'name': 'Alex','id': 1}, "
            + "{'name': 'Brian','id':2}, "
            + "{'name': 'Charles','id': 3}]";
    private EmployeeViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "Called ViewModelProvider.get");
        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        viewModel.onLoad();
        RecyclerView listView = findViewById(R.id.employees);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this));
        EmployeeAdapter adapter = new EmployeeAdapter(viewModel.employees.getValue());
        listView.setAdapter(adapter);

        /*viewModel.employees.observe(new LifecycleOwner() {
            @NonNull
            @Override
            public Lifecycle getLifecycle() {
                return null;
            }
        });*/
        Log.i("item size",String.valueOf(viewModel.employees.getValue().size()));
    }
}

