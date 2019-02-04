package com.driverexam.entry.question;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "a1_a3_b1_1")
public class A1_A3_B1_1_Entry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 8)
    private String code;

    @Column
    private int type;  //如果为1为对错,2就是ABCD单选，3为多选

    @Column(columnDefinition = "TEXT")
    private String question;

    @Column(columnDefinition = "TEXT")
    private String questionPic;

    @JoinColumn(name = "a1_a3_b1_1_id")
    @OneToMany(targetEntity = A1_A3_B1_1_SelectionEntry.class, cascade = CascadeType.ALL)
    private List<A1_A3_B1_1_SelectionEntry> selects;

    @Column
    private String answer;//可能结果 A    ABCD    对/错

    @Column
    private double errRate;
    @Column
    private int users;
    @Column
    private int libType;//1为系统题目   0为自定义题目
    @JoinColumn(name = "a1_a3_b1_1_id")
    @OneToMany(targetEntity = A1_A3_B1_1_AnalysisEntry.class, cascade = CascadeType.ALL)
    private List<A1_A3_B1_1_AnalysisEntry> analyses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionPic() {
        return questionPic;
    }

    public void setQuestionPic(String questionPic) {
        this.questionPic = questionPic;
    }

    public List<A1_A3_B1_1_SelectionEntry> getSelects() {
        return selects;
    }

    public void setSelects(List<A1_A3_B1_1_SelectionEntry> selects) {
        this.selects = selects;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public double getErrRate() {
        return errRate;
    }

    public void setErrRate(double errRate) {
        this.errRate = errRate;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public int getLibType() {
        return libType;
    }

    public void setLibType(int libType) {
        this.libType = libType;
    }

    public List<A1_A3_B1_1_AnalysisEntry> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<A1_A3_B1_1_AnalysisEntry> analyses) {
        this.analyses = analyses;
    }

}
