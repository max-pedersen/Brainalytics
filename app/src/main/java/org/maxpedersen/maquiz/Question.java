package org.maxpedersen.maquiz;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.opencsv.bean.CsvBindByName;

import java.sql.Blob;
import java.util.Date;

//Class for questions. To be use in conjunction with DAO.
@Entity
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "week")
    private int week;

    @ColumnInfo(name = "info")
    private String info;

    @ColumnInfo(name = "option_1")
    private String option_1;

    @ColumnInfo(name = "option_2")
    private String option_2;

    @ColumnInfo(name = "option_3")
    private String option_3;

    @ColumnInfo(name = "option_4")
    private String option_4;

    @ColumnInfo(name = "correct_option")
    private String correct_option;

    @ColumnInfo(name = "question_type")
    private String question_type;

    /*@ColumnInfo(name = "visual_cue")
    private Blob visual_cue; */


    @ColumnInfo(name = "content_title")
    private String content_title;

    public Question(int id, int week, String info, String option_1, String option_2, String option_3, String option_4,
                    String correct_option, String question_type,
            // Blob visual_cue, TODO resolve BLOB issue
                   String content_title) {
        this.id = id;
        this.week = week;
        this.info = info;
        this.option_1 = option_1;
        this.option_2 = option_2;
        this.option_3 = option_3;
        this.option_4 = option_4;
        this.correct_option = correct_option;
        this.question_type = question_type;
        /*this.visual_cue = visual_cue; */
        this.content_title = content_title;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    /*
    public Blob getVisual_cue() {
        return visual_cue;
    }

    public void setVisual_cue(Blob visual_cue) {
        this.visual_cue = visual_cue;
    }
    */

    public String getContent_title() {
        return content_title;
    }

    public void setContent_title(String content_title) {
        this.content_title = content_title;
    }
}



