package com.codeartist.androidpractice.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.codeartist.androidpractice.model.Property;
import com.codeartist.androidpractice.repository.Repository;

import java.util.List;

public class PropertyViewModel extends ViewModel {
    private Repository repository;
    public LiveData<List<Property>> properties;


    public PropertyViewModel(Repository repository) {
        this.repository = repository;
        properties = repository.finalProperties;
    }

    public void makeQuery() {
        repository.getProperties();
    }

    public void sort(){
        repository.sort();
    }

    public void sortByType(){
        repository.sortByType();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository.mCompositeDisposable.clear();
    }
}

