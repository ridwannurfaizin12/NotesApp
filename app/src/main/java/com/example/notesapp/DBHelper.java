package com.example.notesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper (Context context) {
        super(context, "catatan.db", null, 1);
    }

    //membuat tabel dan kolom catatan
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE catatan_table(id INTEGER PRIMARY KEY, judul TEXT, catatan TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS catatan_table");
    }

    //input data catatan ke dalam SQLite database
    public boolean inputNotes(Data data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("judul", data.getJudul());
        cv.put("catatan", data.getCatatan());
        return db.insert("catatan_table", null, cv) > 0;
    }

    //mendapatkan seluruh data catatan dari database
    public Cursor allNotes() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from " + "catatan_table", null);
    }

    //Update catatan
    public boolean updateNotes(Data data, int id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("judul", data.getJudul());
        cv.put("catatan", data.getCatatan());
        return db.update("catatan_table", cv, "id" + "=" + id, null) > 0;
    }

    //Delete Catatan
    public void deleteNotes(int id) {
        SQLiteDatabase db = getReadableDatabase();
        db.delete("catatan_table", "id" + "=" + id, null);
    }

    //delete seluruh catatan
    public void deleteAllNotes() {
        SQLiteDatabase db = getReadableDatabase();
        db.delete("catatan_table", null,null);
    }

}
