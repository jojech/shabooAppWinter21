package edu.byui.cit.partygame.model;

import androidx.lifecycle.ViewModel;

import java.util.List;


public class TopicViewModel extends ViewModel {
    private List<Entry> entryList;
    private Topic topicLiveData;

    public Topic getTopicLiveData() {
        return topicLiveData;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setTopicLiveData(Topic liveData) {
        this.topicLiveData = liveData;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }
}
