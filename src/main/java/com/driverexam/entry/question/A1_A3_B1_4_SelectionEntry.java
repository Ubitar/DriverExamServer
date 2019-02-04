package com.driverexam.entry.question;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "a1_a3_b1_4_selection")
public class A1_A3_B1_4_SelectionEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String selectIndex;

    @Column
    private String options;

    public A1_A3_B1_4_SelectionEntry() {

    }

    public A1_A3_B1_4_SelectionEntry(String selectIndex, String options) {
        this.selectIndex = selectIndex;
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSelectIndex() {
        return selectIndex;
    }

    public void setSelectIndex(String selectIndex) {
        this.selectIndex = selectIndex;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
