package com.shubham.griddemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.shubham.griddemo.Adapter.CustomAdapter;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.LandingAnimator;

public class GridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        //fetching data from config activity
        Intent intent = getIntent();
        String mSpeed = intent.getStringExtra("speed");
        String mHeight = intent.getStringExtra("height");
        String mWidth = intent.getStringExtra("width");

        //initViews
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        //Repository/Initial List
        ArrayList<Integer> item = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++)
            item.add(i);

        //setting adapter and dynamically setting speed for transition
        CustomAdapter adapter = new CustomAdapter(item, mSpeed, mHeight, mWidth);
        int numberOfColumns = calculateNoOfColumns(GridActivity.this, Integer.parseInt(mWidth) + 20);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new LandingAnimator());
        recyclerView.getItemAnimator().setRemoveDuration(0);
        recyclerView.getItemAnimator().setMoveDuration(Integer.parseInt(mSpeed) * 1000);
    }

    //func to calculate no of columns according to dynamic width
    public static int calculateNoOfColumns(Context context, float columnWidthDp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5);
        return noOfColumns;
    }
}