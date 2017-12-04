package com.example.myfudancampus;

/**
 * Created by alex on 2017/12/3.
 */

import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 2017/12/3.
 */

public class GPAAdapter extends RecyclerView.Adapter<GPAAdapter.ViewHolder> {
    private ArrayList<GPAModel> mGPAList;
    Context context;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView lessonNameText;
        TextView lessonCodeText;
        TextView teacherNameText;
        TextView semesterNameText;
        View listView;
        ArrayList<GPAModel> list = new ArrayList<GPAModel>();
        Context ctx;
        public ViewHolder(View gpaItemView, Context myCtx, ArrayList<GPAModel> myList) {
            super(gpaItemView);
            gpaItemView.setOnClickListener(this);
            this.list = myList;
            this.ctx = myCtx;
            lessonNameText = (TextView) gpaItemView.findViewById(R.id.gpa_lessonName);
            lessonCodeText = (TextView) gpaItemView.findViewById(R.id.gpa_lessonCode);
            teacherNameText = (TextView) gpaItemView.findViewById(R.id.gpa_teacherName);
            semesterNameText = (TextView) gpaItemView.findViewById(R.id.gpa_semesterName);
            listView = gpaItemView;
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            GPAModel sendGPA = this.list.get(position);
            Intent intent = new Intent(this.ctx, GPADetail.class);
            intent.putExtra("lessonName", sendGPA.getLessonName());
            intent.putExtra("lessonCode", sendGPA.getLessonCode());
            intent.putExtra("creditPoint", sendGPA.getCreditPoint());
            intent.putExtra("teacherName", sendGPA.getTeacherName());
            intent.putExtra("semesterName", sendGPA.getSemesterName());
            intent.putExtra("totalStudentNumber", sendGPA.getTotalStudentNumber());
            //
            ArrayList<String> scoreValue = new ArrayList<String>();
            scoreValue = sendGPA.getScoreValue();
            intent.putExtra("scoreValue", scoreValue);
            //
            ArrayList<Float> studentCount = new ArrayList<Float>();
            studentCount = sendGPA.getStudentCount();
            intent.putExtra("studentCount",studentCount);
           this.ctx.startActivity(intent);

        }
    }
    public GPAAdapter(ArrayList<GPAModel> myList, Context myContext) {

        this.mGPAList = myList;
        this.context = myContext;
    }
    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gpa_item, parent, false);
        final ViewHolder holder = new ViewHolder(view, context, mGPAList);
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
