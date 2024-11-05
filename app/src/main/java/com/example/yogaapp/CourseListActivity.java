// CourseListActivity.java
package com.example.yogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CourseListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCourses;
    private CourseAdapter courseAdapter;
    private DatabaseHelper dbHelper;
    private List<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        dbHelper = new DatabaseHelper(this);
        recyclerViewCourses = findViewById(R.id.recyclerViewCourses);
        recyclerViewCourses.setLayoutManager(new LinearLayoutManager(this));

        courseList = dbHelper.getAllCourses(); // Implement this method in DatabaseHelper
        courseAdapter = new CourseAdapter(courseList, new CourseAdapter.OnCourseClickListener() {
            @Override
            public void onEditClick(Course course) {
                // Start editing activity (or dialog) for this course
            }

            @Override
            public void onDeleteClick(Course course) {
                deleteCourse(course);
            }
        });
        recyclerViewCourses.setAdapter(courseAdapter);
    }

    private void deleteCourse(Course course) {
        boolean success = dbHelper.deleteCourse(course.getId()); // Implement deleteCourse in DatabaseHelper
        if (success) {
            courseList.remove(course);
            courseAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Course deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to delete course", Toast.LENGTH_SHORT).show();
        }
    }
}
