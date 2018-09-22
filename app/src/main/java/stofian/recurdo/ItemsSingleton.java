package stofian.recurdo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

interface IItem {
    void setName(String name);
    void setInterval(Integer interval);
    void setRecurScale(Integer recurScale);
    void setWeekdays(boolean[] weekdays);
    void setDayOfMonth(Integer dayOfMonth);

    String getName();
    Integer getInterval();
    Integer getRecurScale();
    boolean[] getWeekdays();
    Integer getDayOfMonth();
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
    private Integer interval;
    private Integer recurScale;
    private boolean[] weekdays;
    private Integer dayOfMonth;


    public void setName(String name) {
        this.name = name;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public void setRecurScale(Integer recurScale) {
        this.recurScale = recurScale;
    }

    public void setWeekdays(boolean[] weekdays) {
        this.weekdays = weekdays;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getName() {
        return name;
    }

    public Integer getInterval() {
        return interval;
    }

    public Integer getRecurScale() {
        return recurScale;
    }

    public boolean[] getWeekdays() {
        return weekdays;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }


}


