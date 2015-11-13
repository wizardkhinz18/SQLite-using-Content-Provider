package com.example;

import android.content.ContentResolver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.adapter.AdapterListUser;
import com.example.object.User;

import java.util.ArrayList;

import static com.example.queries.QueriesUser.ListQueries.*;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ContentResolver cr;

    private AdapterListUser adapterListUser;
    private ListView lvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvUsers             = (ListView)findViewById(R.id.lvUsers);

        cr = getContentResolver();


    }

    @Override
    public void onStart(){
        super.onStart();

        inserDummyUser(); //insert first

        setAdapterListUser();

    }

    private void setAdapterListUser(){
        if(adapterListUser != null){
            adapterListUser.notifyDataSetChanged();
        }

        adapterListUser = new AdapterListUser(this, getAllUser(cr));
        lvUsers.setAdapter(adapterListUser);
    }

    private void inserDummyUser(){
        ArrayList<User> arrayListUser = new ArrayList<>();

        {
            User user = new User(
                    324324324,
                    "Jojo Alabastro",
                    "wizardkhinz18@gmail.com",
                    "url_image"
            );
            arrayListUser.add(user);
        }

        {
            User user = new User(
                    2354234,
                    "Jovet Alabastro",
                    "jovetalavastro@gmail.com",
                    "url_image"
            );
            arrayListUser.add(user);
        }

        insert(cr, arrayListUser);

    }

    /*private void getAllUsers(){

        ArrayList<User> arrayListUser = getAllUser(cr);

        if(arrayListUser.size() == 0) {
            Log.d(TAG, "no records found.");
            return;
        }

        for(int i = 0; i < arrayListUser.size(); i++){
            User user = arrayListUser.get(i);
            Log.d(TAG, "userId " + user.getUserId());
            Log.d(TAG, "userName " + user.getName());
            Log.d(TAG, "userEmail " + user.getEmail());
            Log.d(TAG, "userImage " + user.getImage());
        }

    }*/

}
