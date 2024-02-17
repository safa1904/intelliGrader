package com.example.IntelliGrader
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class CourseRepository(context: Context) {

    private val dbHelper = CourseDatabaseHelper(context)

    fun addCourse(course: Course) {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(CourseDatabaseHelper.COLUMN_NAME, course.name)
            put(CourseDatabaseHelper.COLUMN_INSTRUCTOR, course.instructor)
        }

        db.insert(CourseDatabaseHelper.TABLE_NAME, null, values)
        db.close()
    }

    fun getAllCourses(): List<Course> {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            CourseDatabaseHelper.COLUMN_ID,
            CourseDatabaseHelper.COLUMN_NAME,
            CourseDatabaseHelper.COLUMN_INSTRUCTOR
        )

        val cursor: Cursor = db.query(
            CourseDatabaseHelper.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val courses = mutableListOf<Course>()

        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(CourseDatabaseHelper.COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(CourseDatabaseHelper.COLUMN_NAME))
            val instructor = cursor.getString(cursor.getColumnIndexOrThrow(CourseDatabaseHelper.COLUMN_INSTRUCTOR))

            val course = Course(id, name, instructor)
            courses.add(course)
        }

        cursor.close()
        db.close()

        return courses
    }
}
