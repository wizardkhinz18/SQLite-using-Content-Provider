package com.example.queries;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.example.object.User;

import java.util.ArrayList;

import static com.example.util.DatabaseConstant.ListConstant.*;
import static com.example.util.ContentUri.ListUri.CONTENT_URI_TB_USER;
import static com.example.util.ContentUri.ListUri.CONTENT_URI_RAWQUERY;

/**
 * -
 * Created by :
 * -
 * Jovet Alabastro
 * Android Developer
 * Email : wizardkhinz18@gmail.com
 * -
 * Date created : 11/13/2015 @ 1:49 PM
 * -
 **/


public class QueriesUser {

    private final String TAG = QueriesUser.class.getSimpleName();

    public static final class ListQueries implements BaseColumns {

        public static void insert(ContentResolver cr, ArrayList<User> arrayListUser){

            for(int i = 0; i < arrayListUser.size(); i++){

                User user = arrayListUser.get(i);

                ContentValues values = new ContentValues();

                values.put(USER_ID, user.getUserId());
                values.put(USER_NAME, user.getName());
                values.put(USER_EMAIL, user.getEmail());
                values.put(USER_IMAGE, user.getImage());

                cr.insert(CONTENT_URI_TB_USER, values); //calling MyContentProvider event insert

            }

        }

        //all users
        public static ArrayList<User> getAllUser(ContentResolver cr){

            ArrayList<User> arrayListUser = new ArrayList<>();

            Cursor cursor = cr.query(CONTENT_URI_TB_USER, null, null, null, null);

            while(cursor.moveToNext()){

                User user = new User(
                        cursor.getInt(cursor.getColumnIndex(USER_ID)),
                        cursor.getString(cursor.getColumnIndex(USER_NAME)),
                        cursor.getString(cursor.getColumnIndex(USER_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(USER_IMAGE))
                );

                arrayListUser.add(user);

            }
            cursor.close();

            return arrayListUser;

        }

        //specific user
        public static ArrayList<User> getUserById(ContentResolver cr, int user_id){

            ArrayList<User> arrayListUser = new ArrayList<>();

            String query = "SELECT " +
                                "* " +
                            "FROM " +
                                TB_USER + " " +
                            "WHERE " +
                                USER_ID + " = '" + user_id + "'";

            Cursor cursor = cr.query(CONTENT_URI_RAWQUERY, null, query, null, null);

            while(cursor.moveToNext()){

                User user = new User(
                        cursor.getInt(cursor.getColumnIndex(USER_ID)),
                        cursor.getString(cursor.getColumnIndex(USER_NAME)),
                        cursor.getString(cursor.getColumnIndex(USER_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(USER_IMAGE))
                );

                arrayListUser.add(user);

            }
            cursor.close();

            return arrayListUser;

        }


    }

}
