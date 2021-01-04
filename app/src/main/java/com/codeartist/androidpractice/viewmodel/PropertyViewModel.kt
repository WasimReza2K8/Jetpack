package com.codeartist.androidpractice.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeartist.androidpractice.repository.PropertyRepository
import kotlinx.coroutines.launch

class PropertyViewModel @ViewModelInject constructor(
        private val repository: PropertyRepository,
        @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

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

    fun doSearch(query: String) {
        val filtered = repository.finalProperties.value?.filter {
            it.id == query.toIntOrNull() || it.price == query.toIntOrNull() || it.type.contains(query)
        }
        repository.finalProperties.value = filtered
    }


}