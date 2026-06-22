package com.example.calendar.model;

public class CalendarDay {

    private final int day;
    private final boolean today;
    private final boolean weekend;

    public CalendarDay(int day, boolean today, boolean weekend) {
        this.day = day;
        this.today = today;
        this.weekend = weekend;
    }

    public int getDay() {
        return day;
    }

    public boolean isToday() {
        return today;
    }

    public boolean isWeekend() {
        return weekend;
    }
}
