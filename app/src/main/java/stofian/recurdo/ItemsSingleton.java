package stofian.recurdo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

interface IItem {
    void addItem(String name, List<Integer> weekdays);
    String getName();
    Integer getInterval();
    boolean matchDay(Integer day);
    void setWeekdays(List<Integer> weekdays);
}


class ItemsSingleton {
    private static ItemsSingleton itemsInstance;
    private List<IItem> items = new ArrayList<>();


    static synchronized ItemsSingleton getInstance() {

        if (itemsInstance == null) {
            itemsInstance = new ItemsSingleton();
        }
        return itemsInstance;
    }

    public void addItem(IItem newItem) {
        this.items.add(newItem);
    }

    private Optional<IItem> findItem(String name) {

        for (int x=0 ; x<items.size() ; x++) {
            if ( items.get(x).getName().equals(name) ) {
                return Optional.of(items.get(x));
            }
        }
        return Optional.empty();
    }
}


class Item implements IItem{
    private String name;
    private List<Integer> weekdays;
    private Integer interval;

    public void addItem(String name, List<Integer> weekdays) {
        this.name = name;
        this.weekdays = weekdays;
    }

    public String getName() {
        return name;
    }

    public Integer getInterval() {
        return interval;
    }

    public boolean matchDay(Integer day) {
        return weekdays.contains(day);
    }

    public void setWeekdays(List<Integer> weekdays) {
        this.weekdays = weekdays;
    }

}


