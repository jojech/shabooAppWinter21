package edu.byui.cit.partygame.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(foreignKeys = @ForeignKey(entity = Topic.class,
        parentColumns = "topicKey",
        childColumns = "topicKey",
        onDelete = ForeignKey.CASCADE))
public class Entry {
    @PrimaryKey(autoGenerate = true)
    private long entryKey;

    private String entryName;
    private long topicKey;

    public Entry(long topicKey, String entryName) {
        this.topicKey = topicKey;
        this.entryName = entryName;
    }

    public long getEntryKey() {
        return entryKey;
    }

    void setEntryKey(long entryKey) {
        this.entryKey = entryKey;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public long getTopicKey() {
        return topicKey;
    }

    void setTopicKey(long topicKey) {
        this.topicKey = topicKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return entryKey == entry.entryKey &&
                topicKey == entry.topicKey &&
                Objects.equals(entryName, entry.entryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryKey, entryName, topicKey);
    }

    @Override
    public String toString() {
        return "Entry{" +
                "entryKey=" + entryKey +
                ", entryName='" + entryName + '\'' +
                ", topicKey=" + topicKey +
                '}';
    }
}
