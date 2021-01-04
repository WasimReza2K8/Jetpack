package com.codeartist.androidpractice.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.codeartist.androidpractice.db.PropertyDao
import com.codeartist.androidpractice.model.Property
import com.codeartist.androidpractice.network.MarsApi
import com.codeartist.androidpractice.network.RequestApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

class PropertyRepository(private val dao: PropertyDao, private val retrofitService: RequestApi) {
    val dbProperties: LiveData<List<Property>> = dao.property
    val finalProperties = MediatorLiveData<List<Property>>()

    init {
        finalProperties.addSource(dbProperties) { properties -> finalProperties.setValue(properties) }
    }


    suspend fun collectProperties() {
        //withContext(Dispatchers.IO) {
        try {
            val result = withTimeout(3_000) {
                retrofitService.getProperties()
            }
            dao.deleteAll()
            dao.insert(result)
           // println("current thread" + Thread.currentThread())
        } catch (error: Throwable) {
            throw error
        }
        //  }

    }


    fun sortByPrice() {
        //val pro = finalProperties.value
        finalProperties.setValue(finalProperties.value?.sortedBy { it.price })
    }

    fun sortByType() {
        // val pro = finalProperties.value
        finalProperties.setValue(finalProperties.value?.sortedBy { it.type })
    }

    fun dbProperty() {
        finalProperties.setValue(dbProperties.value)
    }
}