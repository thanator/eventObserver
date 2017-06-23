package com.tan_ds.eventscreator.Model;

import java.io.Serializable;

/**
 * Created by Tan-DS on 6/14/2017.
 */

public class Event implements Serializable {

    private long id;
    private long calendarId;
    private  long date_from, date_to;
    private  String name;
    private String what_to_do;
    private String timeZone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (calendarId != event.calendarId) return false;
        if (date_from != event.date_from) return false;
        if (date_to != event.date_to) return false;
        if (name != null ? !name.equals(event.name) : event.name != null) return false;
        if (what_to_do != null ? !what_to_do.equals(event.what_to_do) : event.what_to_do != null)
            return false;
        return timeZone != null ? timeZone.equals(event.timeZone) : event.timeZone == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (calendarId ^ (calendarId >>> 32));
        result = 31 * result + (int) (date_from ^ (date_from >>> 32));
        result = 31 * result + (int) (date_to ^ (date_to >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (what_to_do != null ? what_to_do.hashCode() : 0);
        result = 31 * result + (timeZone != null ? timeZone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date_from=" + date_from +
                ", date_to=" + date_to +
                ", name='" + name + '\'' +
                ", what_to_do='" + what_to_do + '\'' +
                '}';
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(long calendarId) {
        this.calendarId = calendarId;
    }

    public long getDate_from() {
        return date_from;
    }

    public void setDate_from(long date_from) {
        this.date_from = date_from;
    }

    public long getDate_to() {
        return date_to;
    }

    public void setDate_to(long date_to) {
        this.date_to = date_to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhat_to_do() {
        return what_to_do;
    }

    public void setWhat_to_do(String what_to_do) {
        this.what_to_do = what_to_do;
    }
}
