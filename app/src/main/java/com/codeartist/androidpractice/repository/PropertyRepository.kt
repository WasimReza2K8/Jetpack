package com.codeartist.androidpractice.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.codeartist.androidpractice.db.PropertyDao
import com.codeartist.androidpractice.model.Property
import com.codeartist.androidpractice.network.MarsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

class PropertyRepository(val dao: PropertyDao) {
    val dbProperties: LiveData<List<Property>> = dao.property
    val finalProperties = MediatorLiveData<List<Property>>()

    init {
        finalProperties.addSource(dbProperties) { properties -> finalProperties.setValue(properties) }
    }


    suspend fun collectProperties() {
        withContext(Dispatchers.IO) {
            try {
                val result = withTimeout(3_000) {
                    MarsApi.retrofitService
                            .getProperties()
                }
                dao.deleteAll()
                dao.insert(result)
            } catch (error: Throwable) {
                throw error
            }
        }

    }


    fun sortByPrice() {
        var pro = finalProperties.value
        pro = pro?.sortedBy { it.price }
        println("After sorting"+pro)
        finalProperties.setValue(pro)
       // finalProperties.value = pro
    }

    fun sortByType() {
        var pro = finalProperties.value
        pro = pro?.sortedBy { it.type }
        println("After sorting"+ pro)
        finalProperties.setValue(pro)
       // finalProperties.value = pro
    }
}