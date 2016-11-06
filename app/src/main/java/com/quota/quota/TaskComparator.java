package com.quota.quota;

import java.util.Calendar;
import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

    public int compare(Task one, Task two) {
        Calendar calOne = Calendar.getInstance();
        Calendar calTwo = Calendar.getInstance();
        calOne.set(one.year, one.month, one.day, one.time/100, one.time/100-one.time);
        calTwo.set(two.year, two.month, two.day, two.time/100, two.time/100-two.time);
        return calOne.compareTo(calTwo);
    }
}