package com.example.maquiz;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Blob;

@Entity
public class Quiz {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "__id")
    private int id;
    private String question;
    private String option_1;
    private String option_2;
    private String option_3;
    private String option_4;
    private String correct_option;
    private String question_type;
    //private Blob visual_cue;
    private String feedback;
    private String week;
    private String youtube_info;
    private String content_title;

    public Quiz(String question, String option_1, String option_2, String option_3, String option_4, String correct_option, String question_type, String feedback, String week, String youtube_info, String content_title) {
        this.question = question;
        this.option_1 = option_1;
        this.option_2 = option_2;
        this.option_3 = option_3;
        this.option_4 = option_4;
        this.correct_option = correct_option;
        this.question_type = question_type;
        //this.visual_cue = visual_cue;
        this.feedback = feedback;
        this.week = week;
        this.youtube_info = youtube_info;
        this.content_title = content_title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption_1() {
        return option_1;
    }

    public void setOption_1(String option_1) {
        this.option_1 = option_1;
    }

    public String getOption_2() {
        return option_2;
    }

    public void setOption_2(String option_2) {
        this.option_2 = option_2;
    }

    public String getOption_3() {
        return option_3;
    }

    public void setOption_3(String option_3) {
        this.option_3 = option_3;
    }

    public String getOption_4() {
        return option_4;
    }

    public void setOption_4(String option_4) {
        this.option_4 = option_4;
    }

    public String getCorrect_option() {
        return correct_option;
    }

    public void setCorrect_option(String correct_option) {
        this.correct_option = correct_option;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

//    public Blob getVisual_cue() {
//        return visual_cue;
//    }
//
//    public void setVisual_cue(Blob visual_cue) {
//        this.visual_cue = visual_cue;
//    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getYoutube_info() {
        return youtube_info;
    }

    public void setYoutube_info(String youtube_info) {
        this.youtube_info = youtube_info;
    }

    public String getContent_title() {
        return content_title;
    }

    public void setContent_title(String content_title) {
        this.content_title = content_title;
    }


}
