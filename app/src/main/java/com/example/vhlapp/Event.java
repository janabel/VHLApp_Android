package com.example.vhlapp;

import java.io.Serializable;
import java.util.Calendar;

public class Event implements Serializable {
    private String mTitle;
    private String mSpecialist;
    private String mType;
    private Calendar mDate;
    private Calendar mTime;
    private String mNotificationTime;
    private String description;
    private boolean isCompleted;

    public Event(String mTitle, String mSpecialist, String mType, Calendar mDate,
                 Calendar mTime, String mNotificationTime, String description, boolean isCompleted) {
        this.mTitle = mTitle;
        this.mSpecialist = mSpecialist;
        this.mType = mType;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mNotificationTime = mNotificationTime;
        this.description = description;
        this.isCompleted = isCompleted;
    }



    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmSpecialist() {
        return mSpecialist;
    }

    public void setmSpecialist(String mSpecialist) {
        this.mSpecialist = mSpecialist;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public Calendar getmDate() {
        return mDate;
    }

    public void setmDate(Calendar mDate) {
        this.mDate = mDate;
    }

    public Calendar getmTime() {
        return mTime;
    }

    public void setmTime(Calendar mTime) {
        this.mTime = mTime;
    }

    public String getmNotificationTime() {
        return mNotificationTime;
    }

    public void setmNotificationTime(String mNotificationTime) {
        this.mNotificationTime = mNotificationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Event{" +
                "mTitle='" + mTitle + '\'' +
                ", mSpecialist='" + mSpecialist + '\'' +
                ", mType='" + mType + '\'' +
                ", mDate=" + mDate +
                ", mTime=" + mTime +
                ", mNotificationTime='" + mNotificationTime + '\'' +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
