package com.codeartist.androidpractice.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.codeartist.androidpractice.repository.Repository;

public class PropertyViewModelFactory implements ViewModelProvider.Factory {
    private Repository repository;
    private String mParam;


    public PropertyViewModelFactory(Repository repository) {
        this.repository = repository;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new PropertyViewModel(repository);
    }
}
