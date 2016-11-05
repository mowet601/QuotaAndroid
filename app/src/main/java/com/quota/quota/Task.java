package com.quota.quota;

import java.io.ObjectStreamException;
import java.io.IOException;
import java.io.Serializable;

public class Task implements Serializable{

    public String name;
    public short day;
    public short month;
    public short year;
    public short time; // of form 1345 = 1:45 PM
    public short priority;
    public boolean complete;

    public Task(String name, short day, short month, short year, short time, short priority) {
        this.name = name;
        this.month = month;
        this.day = day;
        this.year = year;
        this.time = time;
        this.priority = priority;
        complete = false;
    }

    public String code() {
        if(day<10) {
            if(time<1000) {
                if(month<10) {
                    return "0" + month + "0" + day + year + "0" + time + priority + name;
                } else {
                    return "" + month + "0" + day + year + "0" + time + priority + name;
                }
            } else {
                if(month<10) {
                    return "0" + month + "0" + day + year + time + priority + name;
                } else {
                    return "" + month + "0" + day + year + time + priority + name;
                }
            }
        } else {
            if(time<1000) {
                if(month<10) {
                    return "0" + month + day + year + "0" + time + priority + name;
                } else {
                    return "" + month + day + year + "0" + time + priority + name;
                }
            } else {
                if(month<10) {
                    return "0" + month + day + year + time + priority + name;
                } else {
                    return "" + month + day + year + time + priority + name;
                }
            }
        }
        //0-1(month), 2-3(day), 4-7(year), 8-11(time), 12(priority), 13+(name)
    }

    private String formatTime() {
        int hour = (time/100) % 12;
        if(hour == 0) {
            hour = 12;
        }
        String minStr = ""+time;
        int min = Integer.parseInt(minStr.substring(2));
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

    public String toString() {
        return "Name: " + name + " | Date: " + month + "/" + day + "/" + year + "\n" +
                "Time: " + formatTime();
    }
}
