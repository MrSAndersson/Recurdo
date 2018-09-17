package stofian.recurdo;

import java.util.ArrayList;
import java.util.List;

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

    public Item getItem(String name) {

        for (int x=0 ; x<items.size() ; x++) {
            if ( items.get(x).getName().equals(name) ) {
                return items.get(x);
            }
        }
        return null;
    }


}


class Item {
    private String name;
    private List<Integer> weekdays;

    Item(String name, List<Integer> weekdays) {
        this.name = name;
        this.weekdays = weekdays;
    }

    String getName() {
        return name;
    }

    boolean matchDay(Integer day) {
        return weekdays.contains(day);
    }

    void setWeekdays(List<Integer> weekdays) {
        this.weekdays = weekdays;
    }

}