package com.codeartist.androidpractice.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.codeartist.androidpractice.model.Property
import com.codeartist.androidpractice.repository.PropertyRepository

class SearchResultViewModel @ViewModelInject constructor(
        private val repository: PropertyRepository,
        @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val _filtered: MutableLiveData<List<Property>> = MutableLiveData(ArrayList())
    val filtered: LiveData<List<Property>> = _filtered
    fun doSearch(query: String) {
        _filtered.value = repository.finalProperties.value?.filter {
            it.id.toString().contains(query) || it.price.toString().contains(query) || it.type.contains(query)
        }
    }

}