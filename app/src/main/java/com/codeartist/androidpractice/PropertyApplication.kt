package com.codeartist.androidpractice

import android.app.Application
import com.codeartist.androidpractice.db.PropertyRoomDatabase
import com.codeartist.androidpractice.repository.PropertyRepository

class PropertyApplication : Application() {
    val database by lazy { PropertyRoomDatabase.getDatabase(this) }
    val repository by lazy { PropertyRepository(database.propertyDao()) }
}