package com.wids.experiment7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
    private static final String DB_NAME = "usr.db";
    private static final String DB_TABLE = "usrinfo";
    private static final int DB_VERSION = 1;

    public static final String KEY_ID = "_id";
    public static final String NUNBER = "number";
    public static final String PASSWD = "passwd";

    private SQLiteDatabase db;
    private final Context context;
    private DBOpenHelper dbOpenHelper;

    public DBAdapter(Context _context) {
        context = _context;
    }

    /** Close the database */
    public void close() {
        if (db != null){
            db.close();
            db = null;
        }
    }
    /** Open the database */
    public void open() throws SQLiteException {
        dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbOpenHelper.getWritableDatabase();
        }
        catch (SQLiteException ex) {
            db = dbOpenHelper.getReadableDatabase();
        }
    }

    public long insert(UsrInfo usrinfo) {
        ContentValues newValues = new ContentValues();
        newValues.put(NUNBER, usrinfo.Number);
        newValues.put(PASSWD, usrinfo.Passwd);
        return db.insert(DB_TABLE, null, newValues);
    }

    public long insertContentValues(ContentValues newValues) {
        return db.insert(DB_TABLE, null, newValues);
    }


    public UsrInfo[] queryAllData() {
        Cursor results =  db.query(DB_TABLE, new String[] { KEY_ID, NUNBER, PASSWD},
                null, null, null, null, null);
        return ConvertToUsrInfo(results);
    }

    public UsrInfo[] queryOneData(String count) {
        Cursor results =  db.query(DB_TABLE, new String[] { KEY_ID, NUNBER,PASSWD},
                NUNBER + "=" + "?", new String[] {count}, null, null, null);
        return ConvertToUsrInfo(results);
    }

    public Cursor querybyseclector(String selection,String[] arg ) {
        Cursor results = db.query(DB_TABLE, new String[]{KEY_ID, NUNBER,PASSWD},
                selection, arg, null, null, null);
        return results;
    }

    public Cursor queryOneCursorData(String count) {
        Cursor results =  db.query(DB_TABLE, new String[] { KEY_ID, NUNBER},
                NUNBER + "=" + "?", new String[] {count}, null, null, null);
        return results;
    }

    private UsrInfo[] ConvertToUsrInfo(Cursor cursor){
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()){
            return null;
        }
        UsrInfo[] usrinfo = new UsrInfo[resultCounts];
        for (int i = 0 ; i<resultCounts; i++){
            usrinfo[i] = new UsrInfo();
            usrinfo[i].setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            usrinfo[i].setNumber(cursor.getString(cursor.getColumnIndex(NUNBER)));
            usrinfo[i].setPasswd(cursor.getString(cursor.getColumnIndex(PASSWD)));
            cursor.moveToNext();
        }
        return usrinfo;
    }

    public long deleteAllData() {
        return db.delete(DB_TABLE, null, null);
    }

    public long deleteOneData(long id) {
        return db.delete(DB_TABLE,  KEY_ID + "=" + id, null);
    }

    public long deleteDataByName(String name) {
        return db.delete(DB_TABLE,  NUNBER + "=" + "?", new String[]{name});
    }

    public long updateOneData(long id , UsrInfo usrinfo){
        ContentValues updateValues = new ContentValues();
        updateValues.put(NUNBER, usrinfo.Number);
        updateValues.put(PASSWD, usrinfo.Passwd);
        return db.update(DB_TABLE, updateValues,  KEY_ID + "=" + id, null);
    }

    public long updateOneContentValues(ContentValues Values){
        return db.update(DB_TABLE, Values,  NUNBER + "=" + "?", new String[]{Values.getAsString("count")});
    }

    private static class DBOpenHelper extends SQLiteOpenHelper
    {
        public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        private static final String DB_CREATE = "create table " +
                DB_TABLE + " (" + KEY_ID + " integer primary key autoincrement, " +
                NUNBER+ " text not null, " + PASSWD+ " text not null);";

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
            _db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(_db);
        }
    }
}

