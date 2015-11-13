package com.example.util;

import android.provider.BaseColumns;

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


public class DatabaseConstant{

    private final String TAG = DatabaseConstant.class.getSimpleName();

    public static final class ListConstant implements BaseColumns {

        public static final String RAWQUERY 									= "rawquery";
        public static final String TB_USER 										= "tb_user";
        public static final String TB_BOOK 									    = "tb_book";

        public static final String USER_ID 										= "user_id"; //assume that the user id is from facebook id
        public static final String USER_NAME 									= "user_name";
        public static final String USER_EMAIL 									= "user_email";
        public static final String USER_IMAGE 									= "user_image";

        public static final String BORROWER_ID 							        = "user_id";
        public static final String BOOK_NAME 							        = "book_name";

    }

}
