// DatabaseHelper.java
package com.example.yogaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "universalYoga.db";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng và cột của khóa học
    public static final String TABLE_COURSE = "courses";
    public static final String COLUMN_COURSE_ID = "id";
    public static final String COLUMN_COURSE_TYPE = "type";
    public static final String COLUMN_COURSE_DAY = "day_of_week";
    public static final String COLUMN_COURSE_TIME = "time";
    public static final String COLUMN_COURSE_CAPACITY = "capacity";
    public static final String COLUMN_COURSE_DURATION = "duration";
    public static final String COLUMN_COURSE_PRICE = "price";
    public static final String COLUMN_COURSE_DESCRIPTION = "description";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng khóa học
        String CREATE_COURSE_TABLE = "CREATE TABLE " + TABLE_COURSE + "("
                + COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_COURSE_TYPE + " TEXT,"
                + COLUMN_COURSE_DAY + " TEXT,"
                + COLUMN_COURSE_TIME + " TEXT,"
                + COLUMN_COURSE_CAPACITY + " INTEGER,"
                + COLUMN_COURSE_DURATION + " INTEGER,"
                + COLUMN_COURSE_PRICE + " REAL,"
                + COLUMN_COURSE_DESCRIPTION + " TEXT"
                + ")";
        db.execSQL(CREATE_COURSE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        onCreate(db);
    }

    // Phương thức để lấy tất cả các khóa học
    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_COURSE;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_COURSE_ID));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE_TYPE));
                String dayOfWeek = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE_DAY));
                String time = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE_TIME));
                int capacity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_COURSE_CAPACITY));
                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_COURSE_DURATION));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_COURSE_PRICE));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE_DESCRIPTION));

                Course course = new Course(id, dayOfWeek, time, capacity, duration , price, type, description);
                courseList.add(course);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return courseList;
    }
    public boolean deleteCourse(int courseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_COURSE, COLUMN_COURSE_ID + " = ?", new String[]{String.valueOf(courseId)});
        db.close();
        return rowsAffected > 0;
    }

    public long addCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSE_TYPE, course.getType());
        values.put(COLUMN_COURSE_DAY, course.getDayOfWeek());
        values.put(COLUMN_COURSE_TIME, course.getTime());
        values.put(COLUMN_COURSE_CAPACITY, course.getCapacity());
        values.put(COLUMN_COURSE_DURATION, course.getDuration());
        values.put(COLUMN_COURSE_PRICE, course.getPrice());
        values.put(COLUMN_COURSE_DESCRIPTION, course.getDescription());

        // Thêm khóa học và trả về ID của dòng mới
        long rowId = db.insert(TABLE_COURSE, null, values);
        db.close();
        return rowId;
    }

}
