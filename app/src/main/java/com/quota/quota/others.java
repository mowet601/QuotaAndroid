package com.quota.quota;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Collections;
import android.widget.TextView;

public class others extends AppCompatActivity {

    ArrayList<Task> otherList;
    ArrayAdapter otherAdapt;
    ListView otherView;
    Button back;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);

        title = (TextView)findViewById(R.id.others);
        back = (Button)findViewById(R.id.back);
        otherView = (ListView) findViewById(android.R.id.list);
        otherList = new ArrayList<Task>();
        otherAdapt = new ArrayAdapter(otherView.getContext(), android.R.layout.simple_list_item_1,
                otherList);
        otherView.setAdapter(otherAdapt);


        ArrayList<String> codeArray = (ArrayList<String>) getIntent().getSerializableExtra(
                "otherList");
        for(String s : codeArray) {
            Task task = new Task(s.substring(13), Short.parseShort(s.substring(2, 4)),
                    Short.parseShort(s.substring(0, 2)),
                    Short.parseShort(s.substring(4, 8)),
                    Short.parseShort(s.substring(8, 12)),
                    Short.parseShort(s.substring(12, 13)));
            otherList.add(task);
        }
        if(otherList.size()!=0) {
            Collections.sort(otherList, new TaskComparator());
        }


        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

    }
}
