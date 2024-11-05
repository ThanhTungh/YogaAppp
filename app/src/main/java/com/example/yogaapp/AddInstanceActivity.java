// AddInstanceActivity.java
package com.example.yogaapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

public class AddInstanceActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_instance);

        dbHelper = new DatabaseHelper(this);
        courseId = getIntent().getIntExtra("course_id", -1);

        EditText editTextInstanceDate = findViewById(R.id.editTextInstanceDate);
        EditText editTextTeacher = findViewById(R.id.editTextTeacher);
        EditText editTextComments = findViewById(R.id.editTextComments);
        Button buttonSaveInstance = findViewById(R.id.buttonSaveInstance);

        buttonSaveInstance.setOnClickListener(v -> {
            String date = editTextInstanceDate.getText().toString();
            String teacher = editTextTeacher.getText().toString();
            String comments = editTextComments.getText().toString();

            if (date.isEmpty() || teacher.isEmpty()) {
                Toast.makeText(AddInstanceActivity.this, "Date and Teacher are required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save instance to database
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("course_id", courseId);
            values.put("date", date);
            values.put("teacher", teacher);
            values.put("comments", comments);

            long rowId = db.insert("class_instances", null, values);
            if (rowId != -1) {
                Toast.makeText(AddInstanceActivity.this, "Instance added", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(AddInstanceActivity.this, "Failed to add instance", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
