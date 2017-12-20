package com.travlendar.travlendarServer.logic.util.googleJsonSubClass;

public class InfoPair {
    private String text;
    private String time_zone;
    private long value;

    public InfoPair(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public InfoPair() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getTimeZone() {
        return time_zone;
    }

    public void setTimeZone(String timeZone) {
        this.time_zone = timeZone;
    }
}
