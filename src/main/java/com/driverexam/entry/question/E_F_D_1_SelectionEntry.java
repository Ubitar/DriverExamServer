package com.driverexam.entry.question;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "e_f_d_1_selection")
public class E_F_D_1_SelectionEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String selectIndex;

    @Column
    private String options;

    public E_F_D_1_SelectionEntry() {

    }

    public E_F_D_1_SelectionEntry(String selectIndex, String options) {
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
