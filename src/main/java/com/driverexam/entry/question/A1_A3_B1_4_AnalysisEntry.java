package com.driverexam.entry.question;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "a1_a3_b1_4_analysis")
public class A1_A3_B1_4_AnalysisEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String img;
    @Column(columnDefinition = "TEXT")
    private String text;

    public A1_A3_B1_4_AnalysisEntry() {

    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}