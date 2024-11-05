// MainActivity.java
package com.example.yogaapp;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button buttonAddCourse;
//    private Button buttonViewCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các nút từ layout
        buttonAddCourse = findViewById(R.id.buttonAddCourse);
//        buttonViewCourses = findViewById(R.id.buttonViewCourses);

        // Sự kiện khi nhấn vào nút "Add Course"
//        buttonAddCourse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AddInstanceActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        buttonAddCourse.setOnClickListener(v -> onClick());


        // Sự kiện khi nhấn vào nút "View Courses"
//        buttonViewCourses.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, CourseAdapter.class);
//                startActivity(intent);
//            }
//        });
    }

    public void onClick() {
            Intent intent = new Intent(MainActivity.this, AddInstanceActivity.class);
            startActivity(intent);
            finish();
    }
}
