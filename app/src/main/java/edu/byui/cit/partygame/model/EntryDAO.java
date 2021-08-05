package edu.byui.cit.partygame.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract class EntryDAO {

    @Query("SELECT COUNT(*) FROM Entry")
    public abstract long count();

    @Query("SELECT COUNT(*) FROM ENTRY WHERE topicKey = :key")
    public abstract long countByTopicKey(long key);

    @Query("SELECT * FROM Entry")
    public abstract List<Entry> getAllEntries();

    @Query("SELECT * FROM Entry WHERE EntryKey = :key")
    public abstract Entry getEntryByKey(long key);

    @Query("SELECT * FROM ENTRY WHERE topicKey = :key")
    public abstract List<Entry> getEntriesByTopicKey(long key);

    public void insert(Entry toInsert) {
        long key = realInsert(toInsert);
        toInsert.setEntryKey(key);
    }

    @Insert
    public abstract long realInsert(Entry toInsert);

    @Update
    public abstract void update(Entry toUpdate);

    @Delete
    public abstract void delete(Entry toDelete);

    @Query("DELETE FROM Entry")
    public abstract void deleteAll();
}
