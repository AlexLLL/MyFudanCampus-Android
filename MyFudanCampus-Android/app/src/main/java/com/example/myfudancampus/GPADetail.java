package com.example.myfudancampus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by alex on 2017/12/4.
 */

public class GPADetail extends AppCompatActivity {

    TextView detailLessonName;
    TextView detailLessonCode;
    TextView detailCreditPoint;
    TextView detailTeacherName;
    TextView detailSemesterName;
    TextView detailTotalStudentNumber;
    TextView detailGPAlist;
    ArrayList<String> detailScoreValue = new ArrayList<String>();
    ArrayList<Float> detailStudentCount = new ArrayList<Float>();
    PieChart piechart;
    Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpa_item_detail);
        detailLessonName = (TextView) findViewById(R.id.detail_lessonName);
        detailLessonCode = (TextView) findViewById(R.id.detail_lessonCode);
        detailCreditPoint = (TextView) findViewById(R.id.detail_creditPoint);
        detailSemesterName = (TextView) findViewById(R.id.detail_semesterName);
        detailTeacherName = (TextView) findViewById(R.id.detail_teacherName);
        detailTotalStudentNumber = (TextView) findViewById(R.id.detail_totalStudentNumber);
        detailGPAlist = (TextView) findViewById(R.id.detail_GPAlist);
        piechart = (PieChart) findViewById(R.id.detail_pie_chart);
        //设置传递过来的内容
        detailLessonName.setText(getIntent().getStringExtra("lessonName"));
        detailLessonCode.setText(getIntent().getStringExtra("lessonCode"));
        detailCreditPoint.setText("  学分："+String.valueOf(getIntent().getFloatExtra("creditPoint", 0.f)));
        detailSemesterName.setText(getIntent().getStringExtra("semesterName"));
        detailTeacherName.setText("教师："+getIntent().getStringExtra("teacherName"));
        detailTotalStudentNumber.setText("总人数："+String.valueOf(getIntent().getIntExtra("totalStudentNumber", 0))+"  ");
        detailScoreValue = (ArrayList<String>)getIntent().getSerializableExtra("scoreValue");
        detailStudentCount = (ArrayList<Float>)getIntent().getSerializableExtra("studentCount");
        Integer i = 0;
        String gpaString = "";
        ArrayList<Integer> colors = new ArrayList<>();
        List<PieEntry> entries = new ArrayList<>();
        while (i<detailScoreValue.size()) {
            String a = detailScoreValue.get(i);
            float f= detailStudentCount.get(i);
            String b = String.valueOf(detailStudentCount.get(i));
            gpaString = gpaString+a+":"+b+"  ";
            entries.add(new PieEntry(f, a));
            //随机颜色
            Random random = new Random();
            int red =  random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);

            //添加颜色
            colors.add(Color.rgb(red, green, blue));
            i++;
        }
        detailGPAlist.setText(gpaString);

        //
        PieDataSet set = new PieDataSet(entries, "绩点分布");
        //将随机的颜色设置进来
        set.setColors(colors);
        set.setValueTextSize(12f);
        set.setValueTextColor(Color.WHITE);
        set.setValueFormatter(new PercentFormatter());
        PieData data = new PieData(set);
        piechart.getDescription().setEnabled(false);
        piechart.setData(data);
        piechart.invalidate(); // refresh
    }



}
