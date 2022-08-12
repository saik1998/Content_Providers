package com.firstapp.call_log_change_duration;

public class CallLogModel {

    String name,number,time,duration,type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CallLogModel(String name, String number, String time, String duration, String type) {
        this.name = name;
        this.number = number;
        this.time = time;
        this.duration = duration;
        this.type = type;
    }
}
