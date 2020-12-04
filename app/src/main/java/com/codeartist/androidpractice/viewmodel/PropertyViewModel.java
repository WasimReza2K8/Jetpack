package com.codeartist.androidpractice.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.codeartist.androidpractice.PropertyApplication;
import com.codeartist.androidpractice.model.Property;
import com.codeartist.androidpractice.repository.Repository;

import java.util.List;

public class PropertyViewModel extends ViewModel {
    private Repository repository;
    public LiveData<List<Property>> properties;

    public PropertyViewModel() {
        repository = Repository.getInstance(PropertyApplication.getInstance());
        properties = repository.properties;
    }

    public void getProperties() {
         makeQuery();

    }

    public void makeQuery() {
        repository.getProperties();
        //Log.i("result", String.valueOf(properties.getValue().size()));
        //repository.pfpfp
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository.mCompositeDisposable.clear();
    }
}
