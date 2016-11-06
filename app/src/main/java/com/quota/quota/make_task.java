package com.quota.quota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.Button;

public class make_task extends AppCompatActivity {

    private String name;
    private short year;
    private short month;
    private short day;
    private short time;
    private short priority;

    private EditText nameField;
    private EditText dateField;
    private EditText timeField;
    private TextView error;
    private RatingBar priorityRating;
    private Button confirmTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_task);

        nameField = (EditText)findViewById(R.id.taskName);
        dateField = (EditText)findViewById(R.id.dateIn);
        timeField = (EditText)findViewById(R.id.timeIn);
        error = (TextView)findViewById(R.id.error);
        priorityRating = (RatingBar)findViewById(R.id.priority);
        confirmTask = (Button)findViewById(R.id.confirmTask);

        confirmTask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(nameField.getText()==null || dateField.getText()==null || timeField.getText()
                        == null) {
                    error.setVisibility(View.VISIBLE);
                    return;
                }
                String tempDate = dateField.getText().toString();
                short tempDay, tempMonth, tempYear;
                tempDay = Short.parseShort(tempDate.substring(0, 2));
                tempMonth = Short.parseShort(tempDate.substring(3, 5));
                tempYear = Short.parseShort(tempDate.substring(6));
                short tempTime = Short.parseShort(timeField.getText().toString().substring(0,2)+
                        timeField.getText().toString().substring(3));
                if(tempDay <= 0 || tempDay > 31 || tempMonth <= 0 || tempMonth > 12 ||
                        tempYear < 1000 || tempYear > 9999 || tempTime < 0 || tempTime > 2359) {
                    error.setVisibility(View.VISIBLE);
                    return;
                }
                day = tempDay;
                month = tempMonth;
                year = tempYear;
                time = tempTime;
                name = nameField.getText().toString();
                if(priorityRating.getRating()==3) {
                    priority = 2;
                } else if(priorityRating.getRating()<3) {
                    priority = 1;
                } else {
                    priority = 3;
                }

                Task task = new Task(name, day, month, year, time, priority);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("task", task.code());
                setResult(make_task.RESULT_OK, resultIntent);
                finish();
            }
        });


    }
}





