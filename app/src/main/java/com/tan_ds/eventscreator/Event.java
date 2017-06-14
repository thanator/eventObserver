package com.tan_ds.eventscreator;

/**
 * Created by Tan-DS on 6/14/2017.
 */

public class Event {

    public long id;
    public long date_from, date_to;
    public String name, what_to_do;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (date_from != event.date_from) return false;
        if (date_to != event.date_to) return false;
        if (name != null ? !name.equals(event.name) : event.name != null) return false;
        return what_to_do != null ? what_to_do.equals(event.what_to_do) : event.what_to_do == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (date_from ^ (date_from >>> 32));
        result = 31 * result + (int) (date_to ^ (date_to >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (what_to_do != null ? what_to_do.hashCode() : 0);
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
}
