package stofian.recurdo;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoalSingletonTest {

    private IGoal sampleGoal1 = new IGoal() {
        @Override
        public void setName(String name) {

        }

        @Override
        public void setCategory(Integer category) {

        }

        @Override
        public void setInterval(Integer interval) {

        }

        @Override
        public void setIntervalScale(Integer recurScale) {

        }

        @Override
        public void setWeekdays(boolean[] weekdays) {

        }

        @Override
        public void setDayOfMonth(Integer dayOfMonth) {

        }

        @Override
        public String getName() {
            return "Goal1";
        }

        @Override
        public Integer getCategory() {
            return 1;
        }

        @Override
        public Integer getInterval() {
            return 1;
        }

        @Override
        public Integer getIntervalScale() {
            return 1;
        }

        @Override
        public boolean[] getWeekdays() {
            return new boolean[] {true, false, false, false, false, false, false};
        }

        @Override
        public Integer getDayOfMonth() {
            return 1;
        }
    };

    private IGoal sampleGoal2 = new IGoal() {
        @Override
        public void setName(String name) {

        }

        @Override
        public void setCategory(Integer category) {

        }

        @Override
        public void setInterval(Integer interval) {

        }

        @Override
        public void setIntervalScale(Integer recurScale) {

        }

        @Override
        public void setWeekdays(boolean[] weekdays) {

        }

        @Override
        public void setDayOfMonth(Integer dayOfMonth) {

        }

        @Override
        public String getName() {
            return "Goal2";
        }

        @Override
        public Integer getCategory() {
            return 2;
        }

        @Override
        public Integer getInterval() {
            return 2;
        }

        @Override
        public Integer getIntervalScale() {
            return 2;
        }

        @Override
        public boolean[] getWeekdays() {
            return new boolean[] {false, false, false, false, false, false, true};
        }

        @Override
        public Integer getDayOfMonth() {
            return 2;
        }
    };

    @Test
    public void addAndRetrieveWorks() {

        GoalSingleton.getInstance().addGoal(sampleGoal1);
        GoalSingleton.getInstance().addGoal(sampleGoal2);
        GoalSingleton.getInstance().findGoal(sampleGoal1.getName());
        assertEquals(sampleGoal2, GoalSingleton.getInstance().findGoal(sampleGoal2.getName()).orElse(null));
    }

}