package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.R;
import com.example.object.User;

import java.util.ArrayList;

/**
 * -
 * Created by :
 * -
 * Jovet Alabastro
 * Android Developer @ DesignBlue Manila Inc.
 * Email : wizardkhinz18@gmail.com
 * -
 * Date created : 11/13/2015 @ 2:13 PM
 * -
 **/


public class AdapterListUser extends BaseAdapter{

    private final String TAG = AdapterListUser.class.getSimpleName();

    private LayoutInflater inflater;
    private ArrayList<User> arrayListUser;

    public AdapterListUser(Context context, ArrayList<User> arrayListUser){
        this.arrayListUser = arrayListUser;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayListUser.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null){
            view = inflater.inflate(R.layout.row_user, null);
        }

        TextView tvUserId           = (TextView)view.findViewById(R.id.tvUserId);
        TextView tvUserName         = (TextView)view.findViewById(R.id.tvUserName);
        TextView tvUserEmail        = (TextView)view.findViewById(R.id.tvUserEmail);
        TextView tvUserImage        = (TextView)view.findViewById(R.id.tvUserImage);

        tvUserId                    .setText(String.valueOf(arrayListUser.get(position).getUserId()));
        tvUserName                  .setText(arrayListUser.get(position).getName());
        tvUserEmail                 .setText(arrayListUser.get(position).getEmail());
        tvUserImage                 .setText(arrayListUser.get(position).getImage());

        return view;
    }
}
