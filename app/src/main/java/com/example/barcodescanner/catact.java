package com.example.barcodescanner;

import android.os.Bundle;
import android.app.Activity;
import android.widget.GridView;

public class catact extends Activity {

    GridView gridview;

    public static String[] names = {
            "Data Structures through C",
            "Computer System Architecture",
            "Database System Concepts",
            "Software Engineering",
            "Operating System Concepts",
            "Signals and Systems",
            "OP-AMPS and Linear Circuits",

    };
    public static int[] Images = {
            R.drawable.c,
            R.drawable.coa,
            R.drawable.dbms,
            R.drawable.oose,
            R.drawable.os,
            R.drawable.ss,
            R.drawable.opm,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catact);
        gridview = (GridView) findViewById(R.id.customgrid);
        gridview.setAdapter(new CustomGVAdapter(catact.this, names, Images));
    }
}