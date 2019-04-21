package org.maxpedersen.maquiz;

import java.util.ArrayList;
//Declaring the attributes of the contents class
public class Content {
    private String course;
    private String topic;
    private String content;
    private String youtubeLink;

//The constructor to initiate the class
    public Content(String course, String topic, String content, String youtubeLink) {
        this.course = course;
        this.topic = topic;
        this.content = content;
        this.youtubeLink = youtubeLink;
    }
//Getters & Setters
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    //ArrayList to test the functionality of the code and layout
    public static ArrayList<Content> getTestContent() {
        ArrayList<Content> testContent = new ArrayList<>();
        testContent.add(new Content("INFS3603", "Good charts Ch. 1-4", "hello", "https://www.youtube.com"));
        testContent.add(new Content("INFS3603", "Good charts Ch. 5-9", "hello", "www.youtube.com"));
        testContent.add(new Content("INFS3603", "Knowledge sharing articles & videos", "h", "h"));
        testContent.add(new Content("INFS3603", "Predictive machines Ch. 2-6",  "h", "h"));
        testContent.add(new Content("INFS3603", "Predictive Machines Ch. 7-11", "h", "H"));
        testContent.add(new Content("INFS3603", "Knowledge sharing articles & videos", "h", "h"));
        testContent.add(new Content("INFS3603", "Predictive Machines Ch. 18-19", "h", "h"));
        testContent.add(new Content("INFS3603", "Knowledge sharing articles & videos", "h", "h"));
        return testContent;
    }
}
