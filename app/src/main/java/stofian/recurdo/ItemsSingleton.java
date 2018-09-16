package stofian.recurdo;

import java.util.List;

class ItemsSingleton {
    private static ItemsSingleton itemsInstance;
    private List<item> items;


    static synchronized ItemsSingleton getInstance() {

        if (itemsInstance == null) {
            itemsInstance = new ItemsSingleton();
        }
        return itemsInstance;
    }

    public void addItem(String name, List<Integer> weekdays) {
        items.add(new item(name, weekdays));
    }

    private class item {
        private String name;
        private List<Integer> weekdays;

        item(String name, List<Integer> weekdays) {
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
}
