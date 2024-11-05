// MainActivity.java
package com.example.yogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonAddCourse, buttonViewCourses;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        buttonAddCourse = findViewById(R.id.buttonAddCourse);
        buttonViewCourses = findViewById(R.id.buttonViewCourses);
//id, dayOfWeek, time, capacity, duration , price, type, description
        buttonAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo một đối tượng Course mới và thêm vào cơ sở dữ liệu
                Course newCourse = new Course(1 , "Monday"  , "10:00", 20, 60, 10.20, "Yoga",  "Beginner friendly");
                long rowId = dbHelper.addCourse(newCourse);

                if (rowId != -1) {
                    Toast.makeText(MainActivity.this, "Course added successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to add course", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonViewCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển tới màn hình hiển thị danh sách các khóa học
                Intent intent = new Intent(MainActivity.this, CourseListActivity.class);
                startActivity(intent);
            }
        });
    }
}
