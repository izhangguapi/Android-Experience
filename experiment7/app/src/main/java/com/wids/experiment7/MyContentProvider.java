package com.wids.experiment7;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    public MyContentProvider() {
    }
    //uri匹配器
    private static UriMatcher uriMatcher = new UriMatcher(-1);
    static {
        uriMatcher.addURI("aaa.bbb.ddd","userinfo",1);
    }
    //数据库定义
    public  DBAdapter dbAdapter;

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        //throw new UnsupportedOperationException("Not yet implemented");
        int code = uriMatcher.match(uri);
        if(code ==1 ){
            //匹配成功
            dbAdapter.open();
            int insertCode = (int) dbAdapter.insertContentValues(values);
            if (insertCode == -1){
                //插入数据失败
                return null;
            }else {
                //通知给观察者，需要有观察者
                getContext().getContentResolver().notifyChange(uri, null);
                return uri;
            }
        }else {
            //匹配失败
            throw new IllegalArgumentException("匹配失败");
        }
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbAdapter = new DBAdapter(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        //throw new UnsupportedOperationException("Not yet implemented");
        int code = uriMatcher.match(uri);
        if(code ==1 ){
            //匹配成功
            Cursor result;
            if(selectionArgs[0].equals("")){
                //按照账号查询，如果账号为空，直接不理会
                return null;
            }else{
                //如果不为空，就查询数据库
                dbAdapter.open();
                result = dbAdapter.queryOneCursorData(selectionArgs[0]);
                return result;
            }
        }else{
            //匹配失败
            throw new IllegalArgumentException("匹配失败");
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}