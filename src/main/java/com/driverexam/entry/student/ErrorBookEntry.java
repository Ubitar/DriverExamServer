package com.driverexam.entry.student;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "error_book")
public class ErrorBookEntry implements Serializable {

    public static final String ID = "id";
    public static final String QUESTION_ID = "questionId";
    public static final String TYPE = "type";
    public static final String LEVEL = "level";
    public static final String USER_ID = "userId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int questionId;//问题的id

    @Column
    private String type;//驾照类型 A1_A3_B1   B2_A2   C1_C2_C3   E_F_D

    @Column
    private int level;//  1   4
    @Column
    private int userId;//用户的id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
