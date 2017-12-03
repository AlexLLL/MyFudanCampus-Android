package com.example.myfudancampus;

/**
 * Created by alex on 2017/12/3.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alex on 2017/12/3.
 */

public class GPAAdapter extends RecyclerView.Adapter<GPAAdapter.ViewHolder> {
    private List<GPAModel> mGPAList;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView lessonNameText;
        TextView lessonCodeText;
        TextView teacherNameText;
        TextView semesterNameText;
        View listView;
        public ViewHolder(View gpaItemView) {
            super(gpaItemView);
            lessonNameText = (TextView) gpaItemView.findViewById(R.id.gpa_lessonName);
            lessonCodeText = (TextView) gpaItemView.findViewById(R.id.gpa_lessonCode);
            teacherNameText = (TextView) gpaItemView.findViewById(R.id.gpa_teacherName);
            semesterNameText = (TextView) gpaItemView.findViewById(R.id.gpa_semesterName);
            listView = gpaItemView;
        }
    }
    public GPAAdapter(List<GPAModel> dataList) {
        mGPAList = dataList;
    }
    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gpa_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    public void setData(List<GPAModel> viewData) {
        mGPAList.clear();
        mGPAList.addAll(viewData);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GPAModel data = mGPAList.get(position);
        holder.setIsRecyclable(true);
        holder.lessonNameText.setText(data.getLessonName());
        holder.lessonCodeText.setText(data.getLessonCode());
        holder.teacherNameText.setText(data.getTeacherName());
        holder.semesterNameText.setText(data.getSemesterName());
    }
    @Override
    public int getItemCount() {
        return mGPAList.size();
    }

}
