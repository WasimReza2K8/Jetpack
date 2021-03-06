package com.codeartist.androidpractice.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codeartist.androidpractice.model.Employees;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeViewModel extends ViewModel {
    public EmployeeViewModel(){
        Log.i("EmployeeViewModel", "EmployeeViewModel created!");
    }
    String json = "[{'name':'Ram', 'email':'ram@gmail.com', 'age':23," +
            "'photo':'https://homepages.cae.wisc.edu/~ece533/images/airplane.png'}," +
            "{'name':'Shyam', 'email':'shyam23@gmail.com', 'age':28," +
            "'photo':'https://cdn.pixabay.com/photo/2015/03/26/09/47/sky-690293_960_720.jpg'}]";
    ArrayList<Employees> em = new ArrayList();
    private MutableLiveData<ArrayList<Employees>> _employees= new MutableLiveData<ArrayList<Employees>>(em);

   /* public LiveData<ArrayList<Employees>> getEmployees() {
        if (_employees == null) {
            _employees = new MutableLiveData<>();
            onLoad();
        }
        return _employees;
    }*/
   // private MutableLiveData<ArrayList<Employees>> _employees = new MutableLiveData<ArrayList<Employees>>(em);
    public LiveData<ArrayList<Employees>> employees = _employees;
    public void onLoad(){
        Gson gson = new Gson();
        //Log.e("json", json);
        //_employees.postValue(new ArrayList<>());
        Employees[] employees = gson.fromJson(json, Employees[].class);
        ArrayList<Employees> list = new ArrayList<Employees> (Arrays.asList(employees));
        _employees.setValue(list);
        /*for (Employees em:employees) {
           // Log.e("empl", em.getName());
            _employees.getValue().add(em);
        }*/
    }
}
