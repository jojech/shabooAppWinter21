package edu.byui.cit.partygame.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract class TopicDAO {
    // Return the number of Topics in the database
    @Query("SELECT COUNT(*) FROM Topic")
    public abstract long count();

    // Populate array with the topic objects
    @Query("SELECT * FROM Topic")
    public abstract List<Topic> getAllTopics();

    // Return matching Topic object to passed key
    @Query("SELECT * FROM TOPIC WHERE topicKey = :key")
    public abstract Topic getTopicByKey(long key);

    @Query("SELECT topicKey FROM TOPIC WHERE topicName = :topicName")
    public abstract long getTopicKeyByName(String topicName);

    // Insert Topic AND update Topic Key
    public void insert(Topic toInsert) {
        long key = realInsert(toInsert);
        toInsert.setTopicKey(key);
    }

    // Actual insert statement **Don't Use**
    @Insert
    public abstract long realInsert(Topic toInsert);

    @Update
    public abstract void update(Topic toUpdate);

    @Delete
    public abstract void delete(Topic toDelete);

    @Query("DELETE FROM Topic")
    public abstract void deleteAll();
}
