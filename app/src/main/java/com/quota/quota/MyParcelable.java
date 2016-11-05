package com.quota.quota;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Noah on 11/5/2016.
 */

public class MyParcelable implements Parcelable{

    private Task task;

    public MyParcelable(Task task) {
        this.task = task;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.toString();
    }

    public static final Parcelable.Creator<MyParcelable> CREATOR = new
            Parcelable.Creator<MyParcelable>() {
        public MyParcelable createFromParcel(Parcel in) {
            return new MyParcelable(in);
        }

        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
        }
    };

    private MyParcelable(Parcel in) {
        task = null;
    }

}
