package edu.byui.cit.partygame.model;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Topic {
    @PrimaryKey(autoGenerate = true)
    private long topicKey;

    private String topicName;
    private String createdBy;

    @Ignore
    public Topic(String topicName, String createdBy) {
        this.topicName = topicName;
        this.createdBy = createdBy;
    }

    public Topic(String topicName) {
        this.topicName = topicName;
        this.createdBy = "anonymous";
    }

    public long getTopicKey() {
        return topicKey;
    }

    void setTopicKey(long topicKey) {
        this.topicKey = topicKey;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return topicKey == topic.topicKey &&
                Objects.equals(topicName, topic.topicName) &&
                Objects.equals(createdBy, topic.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicKey, topicName, createdBy);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicKey=" + topicKey +
                ", topicName='" + topicName + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
