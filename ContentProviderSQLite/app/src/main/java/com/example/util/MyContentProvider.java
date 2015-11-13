package com.example.util;

import static android.provider.BaseColumns._ID;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import static com.example.util.DatabaseConstant.ListConstant.*;
import static com.example.util.ContentUri.ListUri.*;

/**
 * -
 * Created by :
 * -
 * Jovet Alabastro
 * Android Developer
 * Email : wizardkhinz18@gmail.com
 * -
 * Date created : 11/13/2015 @ 1:25 PM
 * -
 **/


public class MyContentProvider extends ContentProvider {

    private final String TAG = MyContentProvider.class.getSimpleName();

    private final static String 	DATABASE_NAME 		= "databasename.db";
    private final static int 		DATABASE_VERSION	= 1;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    public static final String AUTHORITY = "com.example.util";
    private static UriMatcher sUriMatcher;

    private static final int tb_user 					= 1;
    private static final int tb_book     				= 2;
    private static final int rawquery 					= 69;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private final String TAG = DatabaseHelper.class.getSimpleName();

        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            /*Log.i(TAG, "success creating database");*/
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub

            db.execSQL("CREATE TABLE " + TB_USER +
                    "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + USER_ID + " INTEGER,"
                    + USER_NAME + " TEXT,"
                    + USER_EMAIL + " TEXT,"
                    + USER_IMAGE + " TEXT);");

            db.execSQL("CREATE TABLE " + TB_BOOK +
                    "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BORROWER_ID + " INTEGER,"
                    + BOOK_NAME + " TEXT);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

            db.execSQL("DROP TABLE IF EXISTS " + TB_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TB_BOOK);

            onCreate(db);

        }

    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        // TODO Auto-generated method stub
        db = dbHelper.getWritableDatabase();
        int count;

        switch(sUriMatcher.match(uri)){
            case tb_user:
                count = db.delete(TB_USER, where, whereArgs);
                break;
            case tb_book:
                count = db.delete(TB_BOOK, where, whereArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        // TODO Auto-generated method stub

        if(sUriMatcher.match(uri) != tb_user
                && sUriMatcher.match(uri) != tb_book){
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        ContentValues values;
        if(initialValues != null){
            values = new ContentValues(initialValues);
        }else{
            values = new ContentValues();
        }

        long rowId;
        db = dbHelper.getWritableDatabase();

        switch(sUriMatcher.match(uri)){
            case tb_user:
                rowId = db.insert(TB_USER, null, values);
                if(rowId > 0){
                    Uri xUri = ContentUris.withAppendedId(CONTENT_URI_TB_USER, rowId);
                    Log.i("insert - TB_USER", xUri.toString());
                    getContext().getContentResolver().notifyChange(xUri, null);
                    return xUri;
                }
                break;

            case tb_book:
                rowId = db.insert(TB_BOOK, null, values);
                if(rowId > 0){
                    Uri xUri = ContentUris.withAppendedId(CONTENT_URI_TB_BOOK, rowId);
                    Log.i("insert - TB_USER", xUri.toString());
                    getContext().getContentResolver().notifyChange(xUri, null);
                    return xUri;
                }
                break;

            default:
                throw new SQLException("Failed to insert row into " + uri);
        }

        return null;
    }

    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        dbHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // TODO Auto-generated method stub

        SQLiteQueryBuilder qb 	= new SQLiteQueryBuilder();
        db 						= dbHelper.getReadableDatabase();
        Cursor c 				= null;

        switch(sUriMatcher.match(uri)){
            case rawquery:
                c = db.rawQuery(selection, null);
                break;
            case tb_user:
                qb.setTables(TB_USER);
                c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case tb_book:
                qb.setTables(TB_BOOK);
                c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;

    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        // TODO Auto-generated method stub

        db = dbHelper.getWritableDatabase();
        int count = 0;

        switch(sUriMatcher.match(uri)){
            case tb_user:
                count = db.update(TB_USER, values, where, whereArgs);
                break;
            case tb_book:
                count = db.update(TB_BOOK, values, where, whereArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        sUriMatcher.addURI(AUTHORITY, RAWQUERY, rawquery);
        sUriMatcher.addURI(AUTHORITY, TB_USER, tb_user);
        sUriMatcher.addURI(AUTHORITY, TB_BOOK, tb_book);
    }
}
