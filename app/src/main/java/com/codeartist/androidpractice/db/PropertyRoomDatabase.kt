package com.codeartist.androidpractice.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codeartist.androidpractice.model.Property

@Database(entities = arrayOf(Property::class), version = 1, exportSchema = false)
abstract class PropertyRoomDatabase : RoomDatabase() {
    abstract fun propertyDao(): PropertyDao

    companion object {
        @Volatile
        private var INSTANCE: PropertyRoomDatabase? = null
        fun getDatabase(context: Context): PropertyRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        PropertyRoomDatabase::class.java,
                        "mar_property_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}