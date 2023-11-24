package com.example.yp1ex.data_base;

public class DataBaseConst {
    public static final String DATA_BASE_NAME = "students.db";
    public static final int DATA_BASE_VERSION = 1;
    public static final String TABLE_NAME_STUDENTS = "Students";
    public static final String STUDENTS_ID = "id";
    public static final String STUDENTS_FIRSTNAME = "firstname";
    public static final String STUDENTS_SECOND_NAME = "secondName";
    public static final String STUDENTS_SURNAME = "surname";
    public static final String STUDENTS_DATE = "date";
    public static final String STUDENTS_GROUP = "idGroup";

    public static final String TABLE_NAME_GROUPS = "Groups";
    public static final String GROUPS_ID = "id";
    public static final String GROUPS_NUMBER = "number";
    public static final String GROUPS_NAME = "name";

    public static final String CREATE_TABLE_STUDENTS = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_STUDENTS +
            "" + " ( " + STUDENTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STUDENTS_FIRSTNAME + " TEXT, " +
            "" + STUDENTS_SECOND_NAME + " TEXT, " + STUDENTS_SURNAME + " TEXT, " + STUDENTS_DATE + " TEXT, " +
            STUDENTS_GROUP + " INTEGER, " + "FOREIGN KEY (" + STUDENTS_GROUP + ") REFERENCES " + TABLE_NAME_GROUPS + "(" + GROUPS_ID + "));";
    public static final String DELETE_TABLE_STUDENTS = "DROP TABLE IF EXISTS " + STUDENTS_SECOND_NAME;

    public static final String CREATE_TABLE_GROUP = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_GROUPS +
            "" + " ( " + GROUPS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + GROUPS_NUMBER + " TEXT, " + GROUPS_NAME + " TEXT);";
    public static final String DELETE_TABLE_GROUP = "DELETE TABLE IF EXISTS " + TABLE_NAME_GROUPS;
}
