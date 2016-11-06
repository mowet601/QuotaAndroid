package com.quota.quota;

import java.util.Calendar;
import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

    public int compare(Task one, Task two) {
        Calendar calOne = Calendar.getInstance();
        Calendar calTwo = Calendar.getInstance();
        calOne.set(Calendar.HOUR_OF_DAY, 0);
        calTwo.set(Calendar.HOUR_OF_DAY, 0);
        calOne.set(Calendar.MINUTE, 0);
        calTwo.set(Calendar.MINUTE, 0);
        calOne.set(Calendar.SECOND, 0);
        calTwo.set(Calendar.SECOND, 0);
        calOne.set(Calendar.MILLISECOND, 0);
        calTwo.set(Calendar.MILLISECOND, 0);
        calOne.set(one.year, one.month, one.day, one.time/100, one.time/100-one.time);
        calTwo.set(two.year, two.month, two.day, two.time/100, two.time/100-two.time);
        return calOne.compareTo(calTwo);
    }
}