package com.trunghtluu.gyminventory2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.trunghtluu.gyminventory2.model.ItemData;

public class ItemDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventory.db";
    private static final String TABLE_NAME = "item";
    private static int DATABASE_VERSION = 1;

    public static final String COLUMN_ITEM_ID = "item_id";
    public static final String COLUMN_ITEM_NAME = "item_name";

    public ItemDBHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatement = "CREATE TABLE " + TABLE_NAME +
                " (" +
                COLUMN_ITEM_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_ITEM_NAME + " TEXT" + ")";

        db.execSQL(createStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DATABASE_VERSION = newVersion;
        String updateQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(updateQuery);
        onCreate(db);
    }

    public void insertItem(ItemData item) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ITEM_ID, item.getId());
        contentValues.put(COLUMN_ITEM_NAME, item.getName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
    }

    public void deleteReceipt(ItemData item){
        String deleteStatement = "DELETE FROM "+TABLE_NAME+ " WHERE "+COLUMN_ITEM_ID+" = "+item.getId();
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(deleteStatement);
    }

    public Cursor retrieveReceipts() {

        String selectStatement = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();

        Cursor items = db.rawQuery(selectStatement, null);

        return items;

    }

}
