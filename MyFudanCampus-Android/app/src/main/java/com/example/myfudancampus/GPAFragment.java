package com.example.myfudancampus;

/**
 * Created by alex on 2017/12/1.
 */
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class GPAFragment extends Fragment {

    List<DataModel> resultList = new ArrayList<>();
    List<DataModel> dataList = new ArrayList<>();
    String inputText = "C";
    public static final String TAG = "GPA";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View gpaView = inflater.inflate(R.layout.fragment_gpa, container, false);
        RecyclerView gpaRecyclerView = (RecyclerView) gpaView.findViewById(R.id.gpa_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        gpaRecyclerView.setLayoutManager(layoutManager);
        GPAAdapter adapter = new GPAAdapter(getNews());
        gpaRecyclerView.setAdapter(adapter);
        return gpaView;
    }

    private List<GPAModel> getNews() {
        List<DataModel> dataList = new ArrayList<>();
        List<GPAModel> resultList = new ArrayList<>();
        int i = 0;
        if (inputText.equals("")) {
            GPAModel model = new GPAModel();
            model.setLessonName("结果不存在，请重新输入");
            model.setLessonCode("");
            model.setCreditPoint(0.0f);
            model.setSemesterName("");
            model.setTeacherName("");
            model.setTotalStudentNumber(0);
            List<String> scoreValue = new ArrayList<>();
            List<Float> scoreCount = new ArrayList<>();
            model.setScoreValue(scoreValue);
            model.setStudentCount(scoreCount);
            resultList.add(model);
        } else {
            SQLiteManager dbManager = new SQLiteManager(getContext());
            dataList = dbManager.getResult(inputText);

            if (dataList.size() != 0) {
                while (i < dataList.size() - 1) {
                    List<String> scoreValue = new ArrayList<>();
                    List<Float> scoreCount = new ArrayList<>();
                    int range = 0;
                    int test = 0;
                    test = i;

                    while (dataList.get(test).getLessonCode().equals(dataList.get(test + range).getLessonCode()) && (test + range) < dataList.size() - 1) {
                        String score = dataList.get(test + range).getScoreValue();
                        Float count = dataList.get(test + range).getStudentCount();
                        scoreValue.add(score);
                        scoreCount.add(count);
                        Log.v(TAG, "操作指针是"+(test+range));
                        range = range + 1;
                    }
                    //将SQL数组转换成我们想要的数组
                    GPAModel model = new GPAModel();
                    model.setLessonName("  "+dataList.get(test).getLessonName());
                    model.setLessonCode(dataList.get(test).getLessonCode()+"  ");
                    model.setCreditPoint(dataList.get(test).getCreditPoint());
                    model.setSemesterName("  "+dataList.get(test).getSemesterName());
                    model.setTeacherName(dataList.get(test).getTeacherName()+"  ");
                    model.setTotalStudentNumber(dataList.get(test).getTotalStudentNumber());
                    model.setScoreValue(scoreValue);
                    model.setStudentCount(scoreCount);
                    resultList.add(model);
                    i = i + range;
                    //print("区间range是'\(range)'")
                }
                //最后一个score词典少了一个尾部，需要手动补充
                String score = dataList.get(dataList.size()-1).getScoreValue();
                Float count = dataList.get(dataList.size()-1).getStudentCount();
                resultList.get(resultList.size()-1).getScoreValue().add(score);
                resultList.get(resultList.size()-1).getStudentCount().add(count);
            } else {
                GPAModel model = new GPAModel();
                model.setLessonName("结果不存在，请重新输入");
                model.setLessonCode("");
                model.setCreditPoint(0.0f);
                model.setSemesterName("");
                model.setTeacherName("");
                model.setTotalStudentNumber(0);
                List<String> scoreValue = new ArrayList<>();
                List<Float> scoreCount = new ArrayList<>();
                model.setScoreValue(scoreValue);
                model.setStudentCount(scoreCount);
                resultList.add(model);
            }
        }
        return resultList;
    }



}