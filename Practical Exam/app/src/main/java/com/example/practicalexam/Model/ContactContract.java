package com.example.practicalexam.Model;

public final class ContactContract {
    private ContactContract() {
    }

    public static class ContactEntry  {
        public static final String TABLE_NAME = "Contact";
        public static final String COLUMN_EMP_ID = "EmpId";
        public static final String COLUMN_EMP_NAME = "EmpName";
        public static final String COLUMN_EMP_EMAIL = "EmpEmail";
        public static final String COLUMN_EMP_ADDRESS = "EmpAddress";
        public static final String COLUMN_NOTES = "Notes";

        public static final String CREATE_TABLE_QUERY = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_EMP_ID + " INTEGER PRIMARY KEY," +
                COLUMN_EMP_NAME + " TEXT NOT NULL," +
                COLUMN_EMP_EMAIL + " TEXT," +
                COLUMN_EMP_ADDRESS + " TEXT," +
                COLUMN_NOTES + " TEXT" +
                ");";

    }


}
