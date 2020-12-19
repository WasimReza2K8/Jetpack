package com.codeartist.androidpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeartist.androidpractice.repository.PropertyRepository
import kotlinx.coroutines.launch

class PropertyViewModel(private val repository: PropertyRepository) : ViewModel() {

    val properties = repository.finalProperties
    fun makeQuery() {
        viewModelScope.launch {
            repository.collectProperties()
            }
    }

    fun sort() {
        repository.sortByPrice()
    }


    fun sortByType() {
        repository.sortByType()
    }
    fun defaultProperty() {
        repository.dbProperty()
    }


}