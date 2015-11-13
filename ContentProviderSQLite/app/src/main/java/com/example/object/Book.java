package com.example.object;

/**
 * -
 * Created by :
 * -
 * Jovet Alabastro
 * Android Developer
 * Email : wizardkhinz18@gmail.com
 * -
 * Date created : 11/13/2015 @ 1:42 PM
 * -
 **/


public class Book {

    private int borrower_id;
    private String book_name;

    public Book(int borrower_id, String book_name){
        this.borrower_id = borrower_id;
        this.book_name = book_name;
    }

    public int getBorrowerId(){
        return borrower_id;
    }

    public  String getBookName(){
        return book_name;
    }

}
