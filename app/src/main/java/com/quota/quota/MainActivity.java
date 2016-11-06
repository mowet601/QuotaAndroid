package com.quota.quota;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends ListActivity {

    public ArrayList<Task> todayList;
    public ArrayList<Task> otherList;

    public ArrayAdapter<Task> todayAdapt;
    public ArrayAdapter<Task> otherAdapt;

    public ListView todayView;
    public ListView otherView;

    public Button addTaskButton;
    public TextView viewOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todayList = new ArrayList<Task>();
        otherList = new ArrayList<Task>();

        todayView = (ListView)findViewById(android.R.id.list);

        todayAdapt = new ArrayAdapter<Task>(todayView.getContext(),
                android.R.layout.simple_list_item_1, todayList);
        otherAdapt = new ArrayAdapter<Task>(getListView().getContext(),
                android.R.layout.simple_list_item_1, otherList);

        addTaskButton = (Button) findViewById(R.id.newTaskButton);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), make_task.class);
                startActivityForResult(myIntent, 0);
            }
        });

        viewOther = (TextView)findViewById(R.id.other);
        viewOther.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), others.class);

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
                        Collections.sort(otherList, new TaskComparator());
                        todayAdapt = new ArrayAdapter<Task>(todayView.getContext(),
                                android.R.layout.simple_list_item_1, todayList);
                        todayView.setAdapter(todayAdapt);

                       /* for(int i = 0; i<todayList.size(); i++) {
                            View v = todayAdapt.getView(i, null, null);
                            if (task.priority == 1) {
                                v.setBackgroundColor(Color.rgb(204, 204, 0));
                            } else if (task.priority == 2) {
                                v.setBackgroundColor(Color.rgb(255, 128, 0));
                            } else if (task.priority == 3) {
                                v.setBackgroundColor(Color.rgb(204, 0, 0));
                            }
                        }*/

                    } else if (tomorrow.getYear() >= curr.getYear()) {
                        otherList.add(task);
                        Collections.sort(otherList, new TaskComparator());
                        otherView.setAdapter(otherAdapt);
                    }
                }
            }
        }
    }
}
