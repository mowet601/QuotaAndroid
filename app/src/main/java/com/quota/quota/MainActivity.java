package com.quota.quota;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    public ArrayList<Task> todayList;
    public ArrayList<String> otherList;

    public ArrayAdapter<Task> todayAdapt;

    public ListView todayView;

    public Button addTaskButton;
    public TextView viewOther;

    public int qPoints;
    public int counter;

    public int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todayList = new ArrayList<Task>();
        otherList = new ArrayList<String>();

        todayView = (ListView)findViewById(android.R.id.list);

        todayAdapt = new ArrayAdapter<Task>(todayView.getContext(),
                android.R.layout.simple_list_item_1, todayList);

        qPoints = 0;
        counter = 1;
        Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);

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
                myIntent.putExtra("otherList", otherList);
                startActivity(myIntent);
            }
        });

        todayView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object clicked = todayView.getItemAtPosition(position);
                if(clicked instanceof Task) {
                    Task curr = (Task)clicked;
                    Toast.makeText(getApplicationContext(), "You've completed the task: " +
                            curr.name + " and earned" + counter + "!", Toast.LENGTH_LONG).show();
                    todayList.remove(curr);
                    todayView.setAdapter(todayAdapt);
                }
                
            }

        });
    }

    public void addPoints() {
        qPoints += counter++;
        TextView score = (TextView)findViewById(R.id.score);
        score.setText(qPoints);
    }

    public void onRestart() {
        super.onRestart();
        Calendar c = Calendar.getInstance();
        if(day == c.DAY_OF_MONTH) {
            return;
        }
        if(todayList.size()>0) {
            int day = c.DAY_OF_MONTH;
            if (day != todayList.get(0).day) {
                todayList.clear();
                todayView.setAdapter(todayAdapt);
                counter = 1;
            }
            for(String s : otherList) {
                Task task = new Task(s.substring(13), Short.parseShort(s.substring(2, 4)),
                        Short.parseShort(s.substring(0, 2)),
                        Short.parseShort(s.substring(4, 8)),
                        Short.parseShort(s.substring(8, 12)),
                        Short.parseShort(s.substring(12, 13)));
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                Date today = c.getTime();
                c.set(Calendar.YEAR, task.year);
                c.set(Calendar.MONTH, task.month - 1);
                c.set(Calendar.DAY_OF_MONTH, task.day);
                Date curr = c.getTime();
                if (today.getYear() == (curr.getYear()) && today.getMonth() == (curr.getMonth())
                        && today.getDate() == (curr.getDate())) {
                    otherList.remove(s);
                    todayList.add(task);
                }
            }

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (0): {
                if (resultCode == make_task.RESULT_OK) {
                    Bundle b = data.getExtras();
                    String s = b.getString("task");
                    Task task = new Task(s.substring(13), Short.parseShort(s.substring(2, 4)),
                            Short.parseShort(s.substring(0, 2)),
                            Short.parseShort(s.substring(4, 8)),
                            Short.parseShort(s.substring(8, 12)),
                            Short.parseShort(s.substring(12, 13)));
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.HOUR_OF_DAY, 0);
                    c.set(Calendar.MINUTE, 0);
                    c.set(Calendar.SECOND, 0);
                    c.set(Calendar.MILLISECOND, 0);
                    Date today = c.getTime();
                    c.set(Calendar.YEAR, task.year);
                    c.set(Calendar.MONTH, task.month - 1);
                    c.set(Calendar.DAY_OF_MONTH, task.day);
                    Date curr = c.getTime();
                    if (today.getYear() == (curr.getYear()) && today.getMonth() == (curr.getMonth())
                            && today.getDate() == (curr.getDate())) {
                        todayList.add(task);
                        Collections.sort(todayList, new TaskComparator());
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

                    } else if (today.getYear() < curr.getYear()) {
                        otherList.add(task.code());
                        Collections.sort(otherList);
                    } else if (today.getYear()== curr.getYear()&&today.getMonth()<curr.getMonth()) {
                        otherList.add(task.code());
                        Collections.sort(otherList);
                    } else if (today.getYear() == curr.getYear() && today.getMonth()==
                            curr.getMonth()&&today.getDate()<curr.getDate()) {
                        otherList.add(task.code());
                        Collections.sort(otherList);
                    }

                }
            }
        }
    }
}


