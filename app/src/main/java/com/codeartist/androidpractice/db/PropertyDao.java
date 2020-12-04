package com.codeartist.androidpractice.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codeartist.androidpractice.model.Property;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PropertyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ArrayList<Property> property);

    @Query("DELETE FROM property_table")
    void deleteAll();

    @Query("SELECT * FROM property_table ORDER BY price ASC")
    LiveData<List<Property>> getProperty();
}

