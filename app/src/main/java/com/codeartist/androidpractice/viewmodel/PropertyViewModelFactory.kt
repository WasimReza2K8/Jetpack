package com.codeartist.androidpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codeartist.androidpractice.repository.PropertyRepository


class PropertyViewModelFactory(private val repository: PropertyRepository) : ViewModelProvider.Factory {
    private val mParam: String? = null
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PropertyViewModel(repository) as T
    }
}