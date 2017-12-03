package com.example.myfudancampus;

/**
 * Created by alex on 2017/12/3.
 */
import java.util.ArrayList;
import java.util.List;

public class GPAModel {
    private String lessonName;
    private String lessonCode;
    private Float creditPoint;
    private Integer totalStudentNumber;
    private String teacherName;
    private String semesterName;
    private List<String> scoreValue;
    private List<Float> studentCount;

    public String getLessonName() {

        return  lessonName;
    }
    public void setLessonName(String name) {

        this.lessonName = name;
    }

    public String getLessonCode() {

        return lessonCode;
    }

    public void setLessonCode(String code) {

        this.lessonCode = code;
    }
    public Float getCreditPoint() {

        return creditPoint;
    }

    public void setCreditPoint(Float data) {

        this.creditPoint = data;
    }
    public Integer getTotalStudentNumber() {

        return totalStudentNumber;
    }

    public void setTotalStudentNumber(Integer data) {

        this.totalStudentNumber = data;
    }
    public String getTeacherName() {

        return teacherName;
    }

    public void setTeacherName(String data) {

        this.teacherName = data;
    }
    public String getSemesterName() {

        return semesterName;
    }

    public void setSemesterName(String data) {

        this.semesterName = data;
    }
    public List<String> getScoreValue() {

        return scoreValue;
    }

    public void setScoreValue(List<String> data) {

        this.scoreValue = data;
    }
    public List<Float> getStudentCount() {

        return studentCount;
    }

    public void setStudentCount(List<Float> data) {

        this.studentCount = data;
    }
}
