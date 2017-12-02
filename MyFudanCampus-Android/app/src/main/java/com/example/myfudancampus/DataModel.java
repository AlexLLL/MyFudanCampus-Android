package com.example.myfudancampus;

/**
 * Created by alex on 2017/12/2.
 */

public class DataModel {
    private String lessonName;
    private String lessonCode;
    private Double creditPoint;
    private Integer totalStudentNumber;
    private String teacherName;
    private String semesterName;
    private String scoreValue;
    private Integer studentCount;

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
    public Double getCreditPoint() {

        return creditPoint;
    }

    public void setCreditPoint(Double data) {

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
    public String getScoreValue() {

        return scoreValue;
    }

    public void setScoreValue(String data) {

        this.scoreValue = data;
    }
    public Integer getStudentCount() {

        return studentCount;
    }

    public void setStudentCount(Integer data) {

        this.studentCount = data;
    }
}
