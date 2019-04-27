package org.maxpedersen.maquiz;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

//Declaring the attributes of the contents class
@Entity
public class Content {
    @PrimaryKey
    @NonNull
    private int id;
    private int week;
    private String topic;
    private String content_page1;
    private String content_page2;
    private String content_page3;
    private String content_page4;

//The constructor to initiate the class


    public Content(int week, String topic, String content_page1, String content_page2, String content_page3, String content_page4) {
        this.week = week;
        this.topic = topic;
        this.content_page1 = content_page1;
        this.content_page2 = content_page2;
        this.content_page3 = content_page3;
        this.content_page4 = content_page4;

    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent_page1() {
        return content_page1;
    }

    public void setContent_page1(String content_page1) {
        this.content_page1 = content_page1;
    }

    public String getContent_page2() {
        return content_page2;
    }

    public void setContent_page2(String content_page2) {
        this.content_page2 = content_page2;
    }

    public String getContent_page3() {
        return content_page3;
    }

    public void setContent_page3(String content_page3) {
        this.content_page3 = content_page3;
    }

    public String getContent_page4() {
        return content_page4;

    }

    public void setContent_page4(String content_page4) {
        this.content_page4 = content_page4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}






