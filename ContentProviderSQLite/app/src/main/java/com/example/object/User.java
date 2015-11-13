package com.example.object;

/**
 * -
 * Created by :
 * -
 * Jovet Alabastro
 * Android Developer @ DesignBlue Manila Inc.
 * Email : wizardkhinz18@gmail.com
 * -
 * Date created : 11/13/2015 @ 1:42 PM
 * -
 **/


public class User {

    private int user_id;
    private String name;
    private String email;
    private String image;

    public User(int user_id, String name, String email, String image){
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.image = image;
    }

    public int getUserId(){
        return user_id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getImage(){
        return image;
    }


}
