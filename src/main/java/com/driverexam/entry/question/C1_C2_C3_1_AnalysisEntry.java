package com.driverexam.entry.question;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c1_c2_c3_1_analysis")
public class C1_C2_C3_1_AnalysisEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String img;
    @Column(columnDefinition = "TEXT")
    private String text;

    public C1_C2_C3_1_AnalysisEntry() {

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