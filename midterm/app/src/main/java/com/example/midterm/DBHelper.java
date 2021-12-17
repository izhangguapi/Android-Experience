package com.example.midterm;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper {
    private static final String DB_NAME = "user.db";
    private static final String DB_TABLE = "userinfo";
    private static final int DB_VERSION = 1;

    public static final String KEY_ID = "_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NICKNAME = "nickname";

    private SQLiteDatabase db;
    private final Context context;
    private BaseOpenHelper BaseOpenHelper;

    public DBHelper(Context _context) {
        context = _context;
    }

    /**
     * 关闭数据库
     */
    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    /**
     * 打开数据库
     */
    public void open() throws SQLiteException {
        BaseOpenHelper = new BaseOpenHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = BaseOpenHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = BaseOpenHelper.getReadableDatabase();
        }
    }

    /**
     * 添加
     * @param userinfo
     * @return
     */
    public long insert(UserInfo userinfo) {
        ContentValues newValues = new ContentValues();
        newValues.put(USERNAME, userinfo.Username);
        newValues.put(PASSWORD, userinfo.Password);
        newValues.put(NICKNAME, userinfo.Nickname);
        return db.insert(DB_TABLE, null, newValues);
    }

    /**
     * 根据用户名查询一条信息
     * @param count
     * @return
     */
    public UserInfo[] queryOneData(String count) {
        Cursor results = db.query(DB_TABLE, new String[]{KEY_ID, USERNAME, PASSWORD, NICKNAME},
                USERNAME + "=" + "?", new String[]{count}, null, null, null);
        return ConvertToUsrInfo(results);
    }

    /**
     * 查询除传入的用户名以外的所有数据
     * @param count
     * @return
     */
    public UserInfo[] queryNicknameAndUsername(String count) {
        Cursor results = db.query(DB_TABLE, new String[]{KEY_ID, USERNAME, PASSWORD, NICKNAME},
                USERNAME + "!=" + "?", new String[]{count}, null, null, null);
        return ConvertToUsrInfo(results);
    }

    @SuppressLint("Range")
    private UserInfo[] ConvertToUsrInfo(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }
        UserInfo[] userinfo = new UserInfo[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            userinfo[i] = new UserInfo();
            userinfo[i].setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            userinfo[i].setUsername(cursor.getString(cursor.getColumnIndex(USERNAME)));
            userinfo[i].setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
            userinfo[i].setNickname(cursor.getString(cursor.getColumnIndex(NICKNAME)));
            cursor.moveToNext();
        }
        return userinfo;
    }

    private static class BaseOpenHelper extends SQLiteOpenHelper {
        public BaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        //创建表
        private static final String DB_CREATE = "create table " +
                DB_TABLE + " (" + KEY_ID + " integer primary key autoincrement, " +
                USERNAME + " text not null, " + PASSWORD + " text not null," + NICKNAME + " text not null);";

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

