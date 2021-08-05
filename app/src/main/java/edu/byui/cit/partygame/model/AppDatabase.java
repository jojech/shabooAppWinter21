package edu.byui.cit.partygame.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = { Topic.class, Entry.class }, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase singleton;

    public static AppDatabase getInstance(Context appCtx) {
        if (singleton == null) {
            singleton = Room.databaseBuilder(
                    appCtx, AppDatabase.class, "Shaboo")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return singleton;
    }

    public abstract TopicDAO getTopicDAO();
    public abstract EntryDAO getEntryDAO();
}
