package com.codeartist.androidpractice.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codeartist.androidpractice.model.Property
import java.util.*

@Dao
interface PropertyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(property: List<Property>)

    @Query("DELETE FROM property_table")
    suspend fun deleteAll()

    @get:Query("SELECT * FROM property_table")
    val property: LiveData<List<Property>>
}