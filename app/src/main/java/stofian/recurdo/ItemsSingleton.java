package stofian.recurdo;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ItemsSingleton {
    private static ItemsSingleton itemsInstance;
    private List<Item> items = new ArrayList<>();


    static synchronized ItemsSingleton getInstance() {

        if (itemsInstance == null) {
            itemsInstance = new ItemsSingleton();
        }
        return itemsInstance;
    }

    public void addItem(String name, List<Integer> weekdays) {
        this.items.add(new Item(name, weekdays));
    }

    private Optional<Item> findItem(String name) {

        for (int x=0 ; x<items.size() ; x++) {
            if ( items.get(x).getName().equals(name) ) {
                return Optional.of(items.get(x));
            }
        }
        return Optional.empty();
    }
}


class Item {
    private String name;
    private List<Integer> weekdays;
    private Integer interval;

    Item(String name, List<Integer> weekdays) {
        this.name = name;
        this.weekdays = weekdays;
    }

    String getName() {
        return name;
    }

    Integer getInterval() {
        return interval;
    }

    boolean matchDay(Integer day) {
        return weekdays.contains(day);
    }

    void setWeekdays(List<Integer> weekdays) {
        this.weekdays = weekdays;
    }

}


