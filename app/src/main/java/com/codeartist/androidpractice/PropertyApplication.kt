package com.codeartist.androidpractice

import android.app.Application
import com.codeartist.androidpractice.db.PropertyRoomDatabase
import com.codeartist.androidpractice.repository.PropertyRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PropertyApplication : Application() {
}