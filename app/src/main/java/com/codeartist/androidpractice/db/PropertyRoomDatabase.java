package com.codeartist.androidpractice.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.codeartist.androidpractice.model.Property;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Property.class}, version = 1, exportSchema = false)
public abstract class PropertyRoomDatabase extends RoomDatabase {

    public abstract PropertyDao propertyDao();

    private static volatile PropertyRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static PropertyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PropertyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PropertyRoomDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
