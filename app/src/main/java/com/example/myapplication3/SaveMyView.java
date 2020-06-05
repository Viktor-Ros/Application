package com.example.myapplication3;

import android.view.View;

import java.util.ArrayList;

 class SaveMyView{

    private static ArrayList<View> list = new ArrayList<>();

    static  void setList(View a) {

        list.add(a);
    }


    static ArrayList<View> getList(){

        return list;
    }
}
