package com.driverexam.entry.student;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "exams")
public class ExamEntry implements Serializable {

    public static final String ID="id";
    public static final String TYPE="type";
    public static final String A1_A3_B1_1_SCORE="A1_A3_B1_1_score";
    public static final String A1_A3_B1_4_SCORE="A1_A3_B1_4_score";
    public static final String B2_A2_1_SCORE="B2_A2_1_score";
    public static final String B2_A2_4_SCORE="B2_A2_4_score";
    public static final String C1_C2_C3_1_SCORE="C1_C2_C3_1_score";
    public static final String C1_C2_C3_4_SCORE="C1_C2_C3_4_score";
    public static final String E_F_D_1_SCORE="E_F_D_1_score";
    public static final String E_F_D_4_SCORE="E_F_D_4_score";
    public static final String USER_ID="userId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int A1_A3_B1_1_score;
    @Column
    private int A1_A3_B1_4_score;
    @Column
    private int B2_A2_1_score;
    @Column
    private int B2_A2_4_score;
    @Column
    private int C1_C2_C3_1_score;
    @Column
    private int C1_C2_C3_4_score;
    @Column
    private int E_F_D_1_score;
    @Column
    private int E_F_D_4_score;

    @Column
    private int userId;//用户的id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getA1_A3_B1_1_score() {
        return A1_A3_B1_1_score;
    }

    public void setA1_A3_B1_1_score(int a1_A3_B1_1_score) {
        A1_A3_B1_1_score = a1_A3_B1_1_score;
    }

    public int getA1_A3_B1_4_score() {
        return A1_A3_B1_4_score;
    }

    public void setA1_A3_B1_4_score(int a1_A3_B1_4_score) {
        A1_A3_B1_4_score = a1_A3_B1_4_score;
    }

    public int getB2_A2_1_score() {
        return B2_A2_1_score;
    }

    public void setB2_A2_1_score(int b2_A2_1_score) {
        B2_A2_1_score = b2_A2_1_score;
    }

    public int getB2_A2_4_score() {
        return B2_A2_4_score;
    }

    public void setB2_A2_4_score(int b2_A2_4_score) {
        B2_A2_4_score = b2_A2_4_score;
    }

    public int getC1_C2_C3_1_score() {
        return C1_C2_C3_1_score;
    }

    public void setC1_C2_C3_1_score(int c1_C2_C3_1_score) {
        C1_C2_C3_1_score = c1_C2_C3_1_score;
    }

    public int getC1_C2_C3_4_score() {
        return C1_C2_C3_4_score;
    }

    public void setC1_C2_C3_4_score(int c1_C2_C3_4_score) {
        C1_C2_C3_4_score = c1_C2_C3_4_score;
    }

    public int getE_F_D_1_score() {
        return E_F_D_1_score;
    }

    public void setE_F_D_1_score(int e_F_D_1_score) {
        E_F_D_1_score = e_F_D_1_score;
    }

    public int getE_F_D_4_score() {
        return E_F_D_4_score;
    }

    public void setE_F_D_4_score(int e_F_D_4_score) {
        E_F_D_4_score = e_F_D_4_score;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
