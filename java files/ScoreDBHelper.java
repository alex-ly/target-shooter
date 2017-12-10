package com.example.mre_0.csci4100finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by MrE_0 on 2017-11-14.
 */

public class ScoreDBHelper extends SQLiteOpenHelper{
    static final int DATABASE_VERSION = 1;

    static final String TABLE = "scores";

    static final String CREATE_STATEMENT = "CREATE TABLE scores (\n" +
            "      _id integer primary key autoincrement,\n" +
            "      name varchar(100) not null,\n" +
            "      score decimal not null\n" +
            ")\n";

    static final String DROP_STATEMENT = "DROP TABLE scores";

    public ScoreDBHelper(Context context) {
        super(context, "scores", null, DATABASE_VERSION);
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
    public Score createScore(String name,
                             float playerScore) {
        // create a new entity object (Score)
        Score score = new Score(name, playerScore);

        // put that data into the database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("name", name);
        newValues.put("score", playerScore);

        long id=db.insert(TABLE, null, newValues);
        score.setId(id);

        return score;
    }

    public ArrayList<Score> getAllScores() {
        ArrayList<Score> scores = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"_id", "name", "score"};
        String where = "";
        String[] whereArgs = new String[] {  };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "score DESC");

        cursor.moveToFirst();
        do {
            if (!cursor.isAfterLast()) {
                long id = cursor.getInt(0);
                String name = cursor.getString(1);
                float playerScore = cursor.getFloat(2);

                Score score = new Score(name, playerScore);
                score.setId(id);

                scores.add(score);
            }

            cursor.moveToNext();
        } while (!cursor.isAfterLast());

        Log.i("SQLite", "getAllScores(): num = " + scores.size());

        return scores;
    }

    // DELETE
    public boolean deleteScore(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numRows = db.delete(TABLE, "_id = ?", new String[] { "" + id });

        return (numRows == 1);
    }
}
