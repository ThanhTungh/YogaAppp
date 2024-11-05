// CourseAdapter.java
package com.example.yogaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> courseList;
    private OnCourseClickListener listener;

    public interface OnCourseClickListener {
        void onEditClick(Course course);
        void onDeleteClick(Course course);
    }

    public CourseAdapter(List<Course> courseList, OnCourseClickListener listener) {
        this.courseList = courseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.textViewCourseType.setText(course.getType());
        holder.textViewCourseDay.setText(course.getDayOfWeek());
        holder.itemView.setOnClickListener(v -> listener.onEditClick(course));
        holder.textViewDelete.setOnClickListener(v -> listener.onDeleteClick(course));
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCourseType, textViewCourseDay, textViewDelete;

        CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCourseType = itemView.findViewById(R.id.textViewCourseType);
            textViewCourseDay = itemView.findViewById(R.id.textViewCourseDay);
            textViewDelete = itemView.findViewById(R.id.textViewDelete);
        }
    }
}
