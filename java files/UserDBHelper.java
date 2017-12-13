package com.example.mre_0.csci4100finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by MrE_0 on 2017-12-12.
 */

public class UserDBHelper extends SQLiteOpenHelper{
    static final int DATABASE_VERSION = 1;

    static final String TABLE = "users";

    static final String CREATE_STATEMENT = "CREATE TABLE users (\n" +
            "      _id integer primary key autoincrement,\n" +
            "      name varchar(100) not null\n" +
            ")\n";

    static final String DROP_STATEMENT = "DROP TABLE users";

    public UserDBHelper(Context context) {
        super(context, "users", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create the database, using CREATE SQL statement
        db.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersionNum,
                          int newVersionNum) {
        // delete the old database
        db.execSQL(DROP_STATEMENT);

        // re-create the database
        db.execSQL(CREATE_STATEMENT);
    }

    // CREATE
    public String createUser(String name) {
        // create a new entity object (User)
        //Score score = new Score(name, p);

        // put that data into the database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("name", name);

        db.insert(TABLE, null, newValues);
        //score.setId(id);

        return name;
    }

    public ArrayList<String> getAllUsers() {
        ArrayList<String> users = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"_id", "name"};
        String where = "";
        String[] whereArgs = new String[] {  };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "name DESC");

        cursor.moveToFirst();
        do {
            if (!cursor.isAfterLast()) {
                long id = cursor.getInt(0);
                String name = cursor.getString(1);


                users.add(name);
            }

            cursor.moveToNext();
        } while (!cursor.isAfterLast());

        Log.i("SQLite", "getAllUsers(): num = " + users.size());

        return users;
    }

    // DELETE
    public boolean deleteUser(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numRows = db.delete(TABLE, "_id = ?", new String[] { "" + id });

        return (numRows == 1);
    }
}
