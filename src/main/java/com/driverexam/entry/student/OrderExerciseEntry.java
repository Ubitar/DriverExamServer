package com.driverexam.entry.student;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_exercise")
public class OrderExerciseEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int A1_A3_B1_1_id;
    @Column
    private int A1_A3_B1_4_id;
    @Column
    private int B2_A2_1_id;
    @Column
    private int B2_A2_4_id;
    @Column
    private int C1_C2_C3_1_id;
    @Column
    private int C1_C2_C3_4_id;
    @Column
    private int E_F_D_1_id;
    @Column
    private int E_F_D_4_id;

    @Column
    private int userId;//用户的id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getA1_A3_B1_1_id() {
        return A1_A3_B1_1_id;
    }

    public void setA1_A3_B1_1_id(int a1_A3_B1_1_id) {
        A1_A3_B1_1_id = a1_A3_B1_1_id;
    }

    public int getA1_A3_B1_4_id() {
        return A1_A3_B1_4_id;
    }

    public void setA1_A3_B1_4_id(int a1_A3_B1_4_id) {
        A1_A3_B1_4_id = a1_A3_B1_4_id;
    }

    public int getB2_A2_1_id() {
        return B2_A2_1_id;
    }

    public void setB2_A2_1_id(int b2_A2_1_id) {
        B2_A2_1_id = b2_A2_1_id;
    }

    public int getB2_A2_4_id() {
        return B2_A2_4_id;
    }

    public void setB2_A2_4_id(int b2_A2_4_id) {
        B2_A2_4_id = b2_A2_4_id;
    }

    public int getC1_C2_C3_1_id() {
        return C1_C2_C3_1_id;
    }

    public void setC1_C2_C3_1_id(int c1_C2_C3_1_id) {
        C1_C2_C3_1_id = c1_C2_C3_1_id;
    }

    public int getC1_C2_C3_4_id() {
        return C1_C2_C3_4_id;
    }

    public void setC1_C2_C3_4_id(int c1_C2_C3_4_id) {
        C1_C2_C3_4_id = c1_C2_C3_4_id;
    }

    public int getE_F_D_1_id() {
        return E_F_D_1_id;
    }

    public void setE_F_D_1_id(int e_F_D_1_id) {
        E_F_D_1_id = e_F_D_1_id;
    }

    public int getE_F_D_4_id() {
        return E_F_D_4_id;
    }

    public void setE_F_D_4_id(int e_F_D_4_id) {
        E_F_D_4_id = e_F_D_4_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
