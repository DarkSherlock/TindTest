package com.liang.tind.www.tindtest.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/3/7.
 */
public class StepChartData implements Parcelable {

    private long steps;

    private String day;

    private String date;

    public StepChartData(long steps, String day, String date) {
        this.steps = steps;
        this.day = day;
        this.date = date;
    }

    public long getSteps() {
        return steps;
    }

    public void setSteps(long steps) {
        this.steps = steps;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.steps);
        dest.writeString(this.day);
        dest.writeString(this.date);
    }

    protected StepChartData(Parcel in) {
        this.steps = in.readLong();
        this.day = in.readString();
        this.date = in.readString();
    }

    public static final Creator<StepChartData> CREATOR = new Creator<StepChartData>() {
        @Override
        public StepChartData createFromParcel(Parcel source) {
            return new StepChartData(source);
        }

        @Override
        public StepChartData[] newArray(int size) {
            return new StepChartData[size];
        }
    };
}
