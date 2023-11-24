package com.example.yp1ex.data_base;

import static com.example.yp1ex.data_base.DataBaseConst.GROUPS_ID;
import static com.example.yp1ex.data_base.DataBaseConst.STUDENTS_ID;
import static com.example.yp1ex.data_base.DataBaseConst.TABLE_NAME_GROUPS;
import static com.example.yp1ex.data_base.DataBaseConst.TABLE_NAME_STUDENTS;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.constraintlayout.widget.Group;

import com.example.yp1ex.data.Groups;
import com.example.yp1ex.data.Students;

import java.nio.file.attribute.GroupPrincipal;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataBaseManager {
    private Context context;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {
        this.context = context;
        dbHelper = new DataBaseHelper(this.context);
    }

    public void openDb() {
        db = dbHelper.getWritableDatabase();
    }

    public void closeDb() {
        db.close();
    }

    @SuppressLint("Range")
    public List<Students> getStudents() {
        List<Students> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME_STUDENTS, null);
        while (cursor.moveToNext()) {
            Students student = new Students();
            student.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataBaseConst.STUDENTS_ID))));
            student.setFirstName(cursor.getString(cursor.getColumnIndex(DataBaseConst.STUDENTS_FIRSTNAME)));
            student.setSecondName(cursor.getString(cursor.getColumnIndex(DataBaseConst.STUDENTS_SECOND_NAME)));
            student.setSurname(cursor.getString(cursor.getColumnIndex(DataBaseConst.STUDENTS_SURNAME)));
            student.setDate(cursor.getString(cursor.getColumnIndex(DataBaseConst.STUDENTS_DATE)));
            student.setIdGroup(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataBaseConst.STUDENTS_GROUP))));
            students.add(student);
        }
        cursor.close();
        return students;
    }

    @SuppressLint("Range")
    public Groups getGroup(int groupId){
        Groups group = new Groups();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_GROUPS + " WHERE " + GROUPS_ID + " = " + "\"" + groupId + "\"", null);
        if (cursor.moveToFirst()){
            group.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataBaseConst.GROUPS_ID))));
            group.setNumber(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataBaseConst.GROUPS_NUMBER))));
            group.setName(cursor.getString(cursor.getColumnIndex(DataBaseConst.GROUPS_NAME)));
        }
        cursor.close();
        return group;
    }

    @SuppressLint("Range")
    public List<Groups> getGroups() {
        List<Groups> groups = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME_STUDENTS, null);
        while (cursor.moveToNext()) {
            Groups group = new Groups();
            group.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataBaseConst.GROUPS_ID))));
            group.setNumber(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataBaseConst.GROUPS_NUMBER))));
            group.setName(cursor.getString(cursor.getColumnIndex(DataBaseConst.GROUPS_NAME)));
            groups.add(group);
        }
        cursor.close();
        return groups;
    }

    public void addStudent(Students student){
        ContentValues cv = new ContentValues();
        cv.put(DataBaseConst.STUDENTS_FIRSTNAME, student.getFirstName());
        cv.put(DataBaseConst.STUDENTS_SECOND_NAME, student.getSecondName());
        cv.put(DataBaseConst.STUDENTS_SURNAME, student.getSurname());
        cv.put(DataBaseConst.STUDENTS_DATE, student.getDate());
        cv.put(DataBaseConst.STUDENTS_GROUP, student.getIdGroup());
        db.insert(TABLE_NAME_STUDENTS, null, cv);
    }

    public void addGroups (Groups group){
        ContentValues cv = new ContentValues();
        cv.put(DataBaseConst.GROUPS_NUMBER, group.getNumber());
        cv.put(DataBaseConst.GROUPS_NAME, group.getName());
        db.insert(TABLE_NAME_GROUPS, null, cv);
    }

    public void updStudent(Students student){
        ContentValues cv = new ContentValues();
        cv.put(DataBaseConst.STUDENTS_FIRSTNAME, student.getFirstName());
        cv.put(DataBaseConst.STUDENTS_SECOND_NAME, student.getSecondName());
        cv.put(DataBaseConst.STUDENTS_SURNAME, student.getSurname());
        cv.put(DataBaseConst.STUDENTS_DATE, student.getDate());
        cv.put(DataBaseConst.STUDENTS_GROUP, student.getIdGroup());
        db.update(TABLE_NAME_STUDENTS, cv, STUDENTS_ID + " = " + student.getId(), null);
    }

    public void updGroups (Groups group){
        ContentValues cv = new ContentValues();
        cv.put(DataBaseConst.GROUPS_NUMBER, group.getNumber());
        cv.put(DataBaseConst.GROUPS_NAME, group.getName());
        db.update(TABLE_NAME_GROUPS, cv, GROUPS_ID + " = " + group.getId(), null);
    }

    public void delStudents(Students student){
        db.delete(TABLE_NAME_STUDENTS, STUDENTS_ID + " = " + student.getId(), null);
    }

    public void delGroup(Groups group){
        db.delete(TABLE_NAME_GROUPS, GROUPS_ID + " = " + group.getId(), null);
    }
}
