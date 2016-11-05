package com.quota.quota;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Button;

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

    public MainActivity() {
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

