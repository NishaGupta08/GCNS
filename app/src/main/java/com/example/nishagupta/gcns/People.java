package com.example.nishagupta.gcns;

import java.util.ArrayList;

/**
 * Created by nishagupta on 03/10/18.
 */

public class People {

    private String firstname;
    private String secondnm;
    private String email;

    public ArrayList<String> arrayList = new ArrayList<>();

    public People(String fn, String sn, String email) {

        this.firstname = fn;
        this.secondnm = sn;
        this.email = email;
    }

    ArrayList<String> getArrayList()
    {
        arrayList.add(firstname);
        arrayList.add(secondnm);
        arrayList.add(email);

        return arrayList;
    }

}
