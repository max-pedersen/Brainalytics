package com.example.maquiz;

import java.util.ArrayList;
//Declaring the attributes of the contents class
public class Content {
    private String course;
    private String topic;
    private String topicTitle;
    private String content;
    private String youtubeLink;
//The constructor to initiate the class
    public Content(String course, String topic, String topicTitle, String content, String youtubeLink) {
        this.course = course;
        this.topic = topic;
        this.topicTitle = topicTitle;
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

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
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
        testContent.add(new Content("INFS3603", "Topic 1", "Intro to Business Intelligence", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ullamcorper fermentum laoreet. Fusce rutrum tincidunt urna, eu efficitur quam luctus vitae. Etiam ut est tortor. In erat ante, condimentum vitae velit quis, semper malesuada mi. Ut nec eros placerat, ultrices metus sit amet, lacinia neque. Nunc ac diam ornare, luctus elit id, iaculis mauris. Etiam at congue dui. Curabitur suscipit, turpis feugiat aliquam vulputate, nunc mauris pharetra nisi, id tincidunt erat enim sit amet ipsum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ullamcorper fermentum laoreet. Fusce rutrum tincidunt urna, eu efficitur quam luctus vitae. Etiam ut est tortor. In erat ante, condimentum vitae velit quis, semper malesuada mi. Ut nec eros placerat, ultrices metus sit amet, lacinia neque. Nunc ac diam ornare, luctus elit id, iaculis mauris. Etiam at congue dui. Curabitur suscipit, turpis feugiat aliquam vulputate, nunc mauris pharetra nisi, id tincidunt erat enim sit amet ipsum Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ullamcorper fermentum laoreet. Fusce rutrum tincidunt urna, eu efficitur quam luctus vitae. Etiam ut est tortor. In erat ante, condimentum vitae velit quis, semper malesuada mi. Ut nec eros placerat, ultrices metus sit amet, lacinia neque. Nunc ac diam ornare, luctus elit id, iaculis mauris. Etiam at congue dui. Curabitur suscipit, turpis feugiat aliquam vulputate, nunc mauris pharetra nisi, id tincidunt erat enim sit amet ipsum Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ullamcorper fermentum laoreet. Fusce rutrum tincidunt urna, eu efficitur quam luctus vitae. Etiam ut est tortor. In erat ante, condimentum vitae velit quis, semper malesuada mi. Ut nec eros placerat, ultrices metus sit amet, lacinia neque. Nunc ac diam ornare, luctus elit id, iaculis mauris. Etiam at congue dui. Curabitur suscipit, turpis feugiat aliquam vulputate, nunc mauris pharetra nisi, id tincidunt erat enim sit amet ipsum Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ullamcorper fermentum laoreet. Fusce rutrum tincidunt urna, eu efficitur quam luctus vitae. Etiam ut est tortor. In erat ante, condimentum vitae velit quis, semper malesuada mi. Ut nec eros placerat, ultrices metus sit amet, lacinia neque. Nunc ac diam ornare, luctus elit id, iaculis mauris. Etiam at congue dui. Curabitur suscipit, turpis feugiat aliquam vulputate, nunc mauris pharetra nisi, id tincidunt erat enim sit amet ipsum","https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        testContent.add(new Content("INFS3603", "Topic 2", "More Business Intelligence", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ullamcorper fermentum laoreet. Fusce rutrum tincidunt urna, eu efficitur quam luctus vitae. Etiam ut est tortor. In erat ante, condimentum vitae velit quis, semper malesuada mi. Ut nec eros placerat, ultrices metus sit amet, lacinia neque. Nunc ac diam ornare, luctus elit id, iaculis mauris. Etiam at congue dui. Curabitur suscipit, turpis feugiat aliquam vulputate, nunc mauris pharetra nisi, id tincidunt erat enim sit amet ipsum.","https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        testContent.add(new Content("INFS3603", "Topic 3", "Even more Business Intelligence", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ullamcorper fermentum laoreet. Fusce rutrum tincidunt urna, eu efficitur quam luctus vitae. Etiam ut est tortor. In erat ante, condimentum vitae velit quis, semper malesuada mi. Ut nec eros placerat, ultrices metus sit amet, lacinia neque. Nunc ac diam ornare, luctus elit id, iaculis mauris. Etiam at congue dui. Curabitur suscipit, turpis feugiat aliquam vulputate, nunc mauris pharetra nisi, id tincidunt erat enim sit amet ipsum.","https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        testContent.add(new Content("INFS3603", "Topic 4", "Alot more Business Intelligence", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ullamcorper fermentum laoreet. Fusce rutrum tincidunt urna, eu efficitur quam luctus vitae. Etiam ut est tortor. In erat ante, condimentum vitae velit quis, semper malesuada mi. Ut nec eros placerat, ultrices metus sit amet, lacinia neque. Nunc ac diam ornare, luctus elit id, iaculis mauris. Etiam at congue dui. Curabitur suscipit, turpis feugiat aliquam vulputate, nunc mauris pharetra nisi, id tincidunt erat enim sit amet ipsum.","https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        testContent.add(new Content("INFS3603", "Topic 5", "I know its too much but MORE Business Intelligence", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ullamcorper fermentum laoreet. Fusce rutrum tincidunt urna, eu efficitur quam luctus vitae. Etiam ut est tortor. In erat ante, condimentum vitae velit quis, semper malesuada mi. Ut nec eros placerat, ultrices metus sit amet, lacinia neque. Nunc ac diam ornare, luctus elit id, iaculis mauris. Etiam at congue dui. Curabitur suscipit, turpis feugiat aliquam vulputate, nunc mauris pharetra nisi, id tincidunt erat enim sit amet ipsum.","https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        return testContent;
    }
}
