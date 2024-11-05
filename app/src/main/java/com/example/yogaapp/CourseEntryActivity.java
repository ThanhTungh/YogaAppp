// CourseEntryActivity.java
package com.example.yogaapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CourseEntryActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_entry);

        dbHelper = new DatabaseHelper(this);

        EditText editTextDayOfWeek = findViewById(R.id.editTextDayOfWeek);
        EditText editTextTime = findViewById(R.id.editTextTime);
        EditText editTextCapacity = findViewById(R.id.editTextCapacity);
        EditText editTextDuration = findViewById(R.id.editTextDuration);
        EditText editTextPrice = findViewById(R.id.editTextPrice);
        EditText editTextType = findViewById(R.id.editTextType);
        EditText editTextDescription = findViewById(R.id.editTextDescription);
        Button buttonSaveCourse = findViewById(R.id.buttonSaveCourse);

        buttonSaveCourse.setOnClickListener(v -> {
            String dayOfWeek = editTextDayOfWeek.getText().toString();
            String time = editTextTime.getText().toString();
            String capacity = editTextCapacity.getText().toString();
            String duration = editTextDuration.getText().toString();
            String price = editTextPrice.getText().toString();
            String type = editTextType.getText().toString();
            String description = editTextDescription.getText().toString();

            if (dayOfWeek.isEmpty() || time.isEmpty() || capacity.isEmpty() || duration.isEmpty() || price.isEmpty() || type.isEmpty()) {
                Toast.makeText(CourseEntryActivity.this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            } else {
                saveCourse(dayOfWeek, time, Integer.parseInt(capacity), Integer.parseInt(duration), Double.parseDouble(price), type, description);
            }
        });
    }

    private void saveCourse(String dayOfWeek, String time, int capacity, int duration, double price, String type, String description) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_COURSE_DAY, dayOfWeek);
        values.put(DatabaseHelper.COLUMN_COURSE_TIME, time);
        values.put(DatabaseHelper.COLUMN_COURSE_CAPACITY, capacity);
        values.put(DatabaseHelper.COLUMN_COURSE_DURATION, duration);
        values.put(DatabaseHelper.COLUMN_COURSE_PRICE, price);
        values.put(DatabaseHelper.COLUMN_COURSE_TYPE, type);
        values.put(DatabaseHelper.COLUMN_COURSE_DESCRIPTION, description);

        long newRowId = db.insert(DatabaseHelper.TABLE_COURSE, null, values);
        db.close();

        if (newRowId != -1) {
            Toast.makeText(this, "Course saved successfully!", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity after saving
        } else {
            Toast.makeText(this, "Error saving course.", Toast.LENGTH_SHORT).show();
        }
    }
}
