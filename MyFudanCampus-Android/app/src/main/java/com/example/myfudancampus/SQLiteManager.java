package com.example.myfudancampus;

/**
 * Created by alex on 2017/12/3.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.IOException;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class SQLiteManager extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "data.s3db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //操作数据库，遍历查询
    public List<DataModel> getResult(String input) {
        List<DataModel> resultList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT Lesson.lessonName, Lesson.lessonCode, Lesson.creditPoint, Course.totalStudentNumber,Teacher.id AS teacherId, Teacher.name AS teacherName, Semester.name AS semesterName, Score.scoreValue, Score.studentCount FROM (Lesson INNER JOIN Course ON Lesson.id = Course.lesson_id) LEFT JOIN Score ON Course.id = Score.course_id LEFT JOIN Teacher ON Course.teacher_id = Teacher.id LEFT JOIN Semester ON Course.semester_id = Semester.id WHERE Lesson.lessonName like '%"+input+"%' ORDER BY Semester.name desc, Lesson.lessonCode asc, Score.scoreValue = 'A' DESC, Score.scoreValue = 'A-' DESC, Score.scoreValue = 'B+' DESC, Score.scoreValue = 'B' DESC, Score.scoreValue = 'B-' DESC, Score.scoreValue = 'C+' DESC, Score.scoreValue = 'C' DESC, Score.scoreValue = 'C-' DESC, Score.scoreValue = 'D+' DESC, Score.scoreValue = 'D' DESC, Score.scoreValue = 'D-' DESC, Score.scoreValue = 'P' DESC, Score.scoreValue = 'F' DESC";
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor!=null&&cursor.moveToFirst()){
            do{
                DataModel pointer= new DataModel();
                pointer.setLessonName(cursor.getString(cursor.getColumnIndex("lessonName")));
                resultList.add(pointer);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return resultList;
    }
}
