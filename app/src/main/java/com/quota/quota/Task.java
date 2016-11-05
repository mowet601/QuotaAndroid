package com.quota.quota;


public class Task {

    private String name;
    private short day;
    private short month;
    private short year;
    private short time; // of form 1345 = 1:45 PM
    private short priority;
    private boolean complete;

    public Task(String name, short day, short month, short year, short time, short priority) {
        this.name = name;
        this.month = month;
        this.day = day;
        this.year = year;
        this.time = time;
        this.priority = priority;
        complete = false;
    }

    public String toString() {
        return name + " - Date: " + month + "/" + day + "/" + year + " - Time: " + formatTime();
    }

    private String formatTime() {
        int hour = (time/100) % 12;
        if(hour == 0) {
            hour = 12;
        }
        int min = time - hour*100;
        if(time <= 1159) {
            return hour+":"+min+" AM";
        } else {
            return hour+":"+min+" PM";
        }
    }

    public void setComplete() {
        complete = true;
    }

    public boolean isComplete() {
        return complete;
    }

    public int getPriority() {
        return priority;
    }
}
