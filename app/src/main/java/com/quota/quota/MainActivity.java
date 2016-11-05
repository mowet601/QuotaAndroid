package com.quota.quota;

import android.app.ListActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

    public ListView todayView;

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

        addTaskButton = (Button) findViewById(R.id.newTaskButton);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), make_task.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (0): {
                if (resultCode == make_task.RESULT_OK) {
                    Bundle b = data.getExtras();
                    String s = b.getString("task");
                    Task task = new Task(s.substring(13), Short.parseShort(s.substring(0, 2)),
                            Short.parseShort(s.substring(2, 4)),
                            Short.parseShort(s.substring(4, 8)),
                            Short.parseShort(s.substring(8, 12)),
                            Short.parseShort(s.substring(12, 13)));
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.HOUR_OF_DAY, 0);
                    c.set(Calendar.MINUTE, 0);
                    c.set(Calendar.SECOND, 0);
                    c.set(Calendar.MILLISECOND, 0);
                    Date today = c.getTime();
                    c.set(Calendar.DAY_OF_MONTH, c.DAY_OF_MONTH + 1);
                    Date tomorrow = c.getTime();
                    c.set(Calendar.YEAR, task.year);
                    c.set(Calendar.MONTH, task.month - 1);
                    c.set(Calendar.DAY_OF_MONTH, task.day);
                    Date curr = c.getTime();
                    if (today.getYear() == (curr.getYear()) && today.getMonth() == (curr.getMonth())
                            && today.getDate() == (curr.getDate())) {
                        todayList.add(task);
                    } else if (tomorrow.getYear() == (curr.getYear()) && tomorrow.getMonth() ==
                            (curr.getMonth()) && tomorrow.getDate() == (curr.getDate())) {
                        tomorrowList.add(task);
                    } else {
                        otherList.add(task);
                    }
                    if (!todayAdapt.isEmpty()) {
                        getListView().setAdapter(todayAdapt);
                    }
                }
            }
        }
    }
}
