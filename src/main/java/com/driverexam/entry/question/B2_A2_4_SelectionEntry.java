package com.driverexam.entry.question;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "b2_a2_4_selection")
public class B2_A2_4_SelectionEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String selectIndex;

    @Column
    private String options;

    public B2_A2_4_SelectionEntry() {

    }

    public B2_A2_4_SelectionEntry(String selectIndex, String options) {
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
