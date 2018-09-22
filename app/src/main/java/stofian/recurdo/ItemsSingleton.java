package stofian.recurdo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

interface IItem {
    void setName(String name);
    void setCategory(Integer category);
    void setInterval(Integer interval);
    void setRecurScale(Integer recurScale);
    void setWeekdays(boolean[] weekdays);
    void setDayOfMonth(Integer dayOfMonth);

    String getName();
    Integer getCategory();
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
    private Integer category;
    private Integer interval;
    private Integer recurScale;
    private boolean[] weekdays;
    private Integer dayOfMonth;

    public static final Integer DAYLY = 1;
    public static final Integer WEEKLY = 2;
    public static final Integer MONTHLY = 3;

    public static final Integer EXERCISE = 1;
    public static final Integer FOOD = 2;
    public static final Integer HYGIENE = 3;
    public static final Integer RESTING = 4;
    public static final Integer HOBBY = 5;
    public static final Integer LIFE = 6;

    public static final Integer MONDAY = 0;
    public static final Integer TUESDAY = 1;
    public static final Integer WEDNESDAY = 2;
    public static final Integer THURSDAY = 3;
    public static final Integer FRIDAY = 4;
    public static final Integer SATURDAY = 5;
    public static final Integer SUNDAY = 6;




    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Integer category) {
        this.category = category;
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

    public Integer getCategory() {
        return category;
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


