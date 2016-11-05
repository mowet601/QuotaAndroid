package com.quota.quota;

import android.app.ListActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;


public class MainActivity extends ListActivity {

    public ArrayList<Task> todayList;
    public ArrayList<Task> tomorrowList;
    public ArrayList<Task> otherList;

    public ArrayAdapter<Task> todayAdapt;
    public ArrayAdapter<Task> tomorrowAdapt;
    public ArrayAdapter<Task> otherAdapt;

    public ListView today;
    public ListView tomorrow;
    public ListView other;

    public Button addTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todayList = new ArrayList<Task>();
        tomorrowList = new ArrayList<Task>();
        otherList = new ArrayList<Task>();

        todayAdapt = new ArrayAdapter<Task>(getListView().getContext(),
                android.R.layout.simple_list_item_1, todayList);
        tomorrowAdapt = new ArrayAdapter<Task>(getListView().getContext(),
                android.R.layout.simple_list_item_1, tomorrowList);
        otherAdapt = new ArrayAdapter<Task>(getListView().getContext(),
                android.R.layout.simple_list_item_1, otherList);

        addTaskButton = (Button)findViewById(R.id.newTaskButton);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CreateTask.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }



}

