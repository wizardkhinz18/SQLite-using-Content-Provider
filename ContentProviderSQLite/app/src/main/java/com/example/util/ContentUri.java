package com.example.util;

import android.net.Uri;
import android.provider.BaseColumns;

import static com.example.util.DatabaseConstant.ListConstant.*;

/**
 * -
 * Created by :
 * -
 * Jovet Alabastro
 * Android Developer
 * Email : wizardkhinz18@gmail.com
 * -
 * Date created : 11/13/2015 @ 1:30 PM
 * -
 **/


public class ContentUri {

    public static final class ListUri implements BaseColumns {

        public static final Uri CONTENT_URI_RAWQUERY 							= Uri.parse("content://"+ MyContentProvider.AUTHORITY + "/rawquery");
        public static final Uri CONTENT_URI_TB_USER 							= Uri.parse("content://"+ MyContentProvider.AUTHORITY + "/" + TB_USER);
        public static final Uri CONTENT_URI_TB_BOOK 							= Uri.parse("content://"+ MyContentProvider.AUTHORITY + "/" + TB_BOOK);

    }

}
