package stofian.recurdo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class NewGoalActivity extends AppCompatActivity {
    EditText nameEditText;
    Spinner categorySpinner;
    EditText intervalEditText;
    Spinner scaleSpinner;
    LinearLayout weekViewGroup;
    CheckBox mondayCheckbox;
    CheckBox tuesdayCheckbox;
    CheckBox wednesdayCheckbox;
    CheckBox thursdayCheckbox;
    CheckBox fridayCheckbox;
    CheckBox saturdayCheckbox;
    CheckBox sundayCheckbox;
    LinearLayout monthViewGroup;
    EditText dayOfMonthEditText;
    Button createButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        nameEditText = findViewById(R.id.new_goal_name);
        categorySpinner = findViewById(R.id.new_goal_category);
        intervalEditText= findViewById(R.id.new_goal_interval);
        scaleSpinner = findViewById(R.id.new_goal_interval_scale);
        weekViewGroup = findViewById(R.id.new_goal_week_view_group);
        mondayCheckbox = findViewById(R.id.new_goal_monday);
        tuesdayCheckbox = findViewById(R.id.new_goal_tuesday);
        wednesdayCheckbox = findViewById(R.id.new_goal_wednesday);
        thursdayCheckbox = findViewById(R.id.new_goal_thursday);
        fridayCheckbox = findViewById(R.id.new_goal_friday);
        saturdayCheckbox = findViewById(R.id.new_goal_saturday);
        sundayCheckbox = findViewById(R.id.new_goal_sunday);
        monthViewGroup = findViewById(R.id.new_goal_month_view_group);
        dayOfMonthEditText = findViewById(R.id.new_goal_day_of_month);
        createButton = findViewById(R.id.new_goal_create_button);


        intervalEditText.setText("1");
        intervalEditText.addTextChangedListener(new IntervalChangeWatcher(this));
        weekViewGroup.setVisibility(View.GONE);
        monthViewGroup.setVisibility(View.GONE);
        dayOfMonthEditText.setText("1");

        ArrayAdapter<CharSequence> categorySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.new_goal_categories, android.R.layout.simple_spinner_item);
        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categorySpinnerAdapter);

        ArrayAdapter<CharSequence> scaleSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.interval_scale_singular, android.R.layout.simple_spinner_item);
        scaleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scaleSpinner.setAdapter(scaleSpinnerAdapter);

        scaleSpinner.setOnItemSelectedListener(new OnScaleSwitchListener());

        createButton.setOnClickListener(new CreateButtonListener());

    }

    private class IntervalChangeWatcher implements TextWatcher {
        Context context;

        IntervalChangeWatcher(Context context) {
            this.context = context;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (charSequence.length() != 0) {


                Integer interval = Integer.parseInt(charSequence.toString());
                Integer currentSelectedItem = scaleSpinner.getSelectedItemPosition();

                if (interval > 1) {
                    ArrayAdapter<CharSequence> scaleSpinnerAdapter = ArrayAdapter.createFromResource(context,
                            R.array.interval_scale_plural, android.R.layout.simple_spinner_item);
                    scaleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    scaleSpinner.setAdapter(scaleSpinnerAdapter);
                } else {
                    ArrayAdapter<CharSequence> scaleSpinnerAdapter = ArrayAdapter.createFromResource(context,
                            R.array.interval_scale_singular, android.R.layout.simple_spinner_item);
                    scaleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    scaleSpinner.setAdapter(scaleSpinnerAdapter);
                }
                scaleSpinner.setSelection(currentSelectedItem);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private class OnScaleSwitchListener implements AdapterView.OnItemSelectedListener {


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            switch (i) {
                case 0:
                    weekViewGroup.setVisibility(View.GONE);
                    monthViewGroup.setVisibility(View.GONE);
                    break;
                case 1:
                    weekViewGroup.setVisibility(View.VISIBLE);
                    monthViewGroup.setVisibility(View.GONE);
                    break;
                case 2:
                    weekViewGroup.setVisibility(View.GONE);
                    monthViewGroup.setVisibility(View.VISIBLE);
                    break;
                default:
                    weekViewGroup.setVisibility(View.GONE);
                    monthViewGroup.setVisibility(View.GONE);
                    break;
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class CreateButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view)
        {
            Goal newGoal = new Goal();

            boolean incorrectData = false;

            String categoryString = categorySpinner.getSelectedItem().toString();
            Integer category = 0;

            switch (categoryString)
            {
                case "Exercise":
                    category = Goal.EXERCISE;
                    break;
                case "Food":
                    category = Goal.FOOD;
                    break;
                case "Hygiene":
                    category = Goal.HYGIENE;
                    break;
                case "Resting":
                    category = Goal.RESTING;
                    break;
                case "Studies":
                    category = Goal.STUDIES;
                    break;
                case "Hobby":
                    category = Goal.HOBBY;
                    break;
                case "Life":
                    category = Goal.LIFE;
                    break;
                default:
                    Snackbar.make(view, "A category '" + categoryString +"' is invalid", Snackbar.LENGTH_LONG).show();
                    incorrectData = true;
                    break;
            }

            newGoal.setCategory(category);

            String goalName = nameEditText.getText().toString();
            if (GoalSingleton.getInstance().findGoal(goalName).isPresent())
            {
                Snackbar.make(view, "Goal with name '" + goalName + "' already exists", Snackbar.LENGTH_LONG).show();
                incorrectData = true;
            }
            newGoal.setName(goalName);

            String intervalString = intervalEditText.getText().toString();

            if (!intervalString.equals("") && Integer.parseInt(intervalString) > 0) {
                newGoal.setInterval(Integer.parseInt(intervalEditText.getText().toString()));
            } else
            {
                Snackbar.make(view, "Invalid interval!", Snackbar.LENGTH_LONG).show();
                incorrectData = true;
            }

            String intervalScaleString = scaleSpinner.getSelectedItem().toString();
            Integer intervalScale = 0;

            switch (intervalScaleString)
            {
                case "day":
                case "days":
                    intervalScale = Goal.DAILY;
                    break;
                case "week":
                case "weeks":
                    intervalScale = Goal.WEEKLY;
                    break;
                case "month":
                case "monthly":
                    intervalScale = Goal.MONTHLY;
                    break;
                default:
                    Snackbar.make(view, "Interval '" + categoryString +"' is invalid", Snackbar.LENGTH_LONG).show();
                    incorrectData = true;
                    break;
            }
            newGoal.setIntervalScale(intervalScale);

            boolean[] weekdays = new boolean[7];

            weekdays[Goal.MONDAY] = mondayCheckbox.isChecked();
            weekdays[Goal.TUESDAY] = tuesdayCheckbox.isChecked();
            weekdays[Goal.WEDNESDAY] = wednesdayCheckbox.isChecked();
            weekdays[Goal.THURSDAY] = thursdayCheckbox.isChecked();
            weekdays[Goal.FRIDAY] = fridayCheckbox.isChecked();
            weekdays[Goal.SATURDAY] = saturdayCheckbox.isChecked();
            weekdays[Goal.SUNDAY] = sundayCheckbox.isChecked();

            if ( intervalScale.equals(Goal.WEEKLY) && !(
                    weekdays[Goal.MONDAY]
                            || weekdays[Goal.TUESDAY]
                            || weekdays[Goal.WEDNESDAY]
                            || weekdays[Goal.THURSDAY]
                            || weekdays[Goal.FRIDAY]
                            || weekdays[Goal.SATURDAY]
                            || weekdays[Goal.FRIDAY])
                    )
            {
                Snackbar.make(view, "Select at least one weekday", Snackbar.LENGTH_LONG).show();
                incorrectData = true;
            }

            newGoal.setWeekdays(weekdays);

            if (intervalScale.equals(Goal.MONTHLY))
            {
                String dayOfTheMonthString = dayOfMonthEditText.getText().toString();
                if (!dayOfTheMonthString.equals("") && Integer.parseInt(dayOfTheMonthString) > 0) {
                    newGoal.setDayOfMonth(Integer.parseInt(dayOfMonthEditText.getText().toString()));
                } else
                {
                    Snackbar.make(view, "Invalid day of Month to recur on!", Snackbar.LENGTH_LONG).show();
                    incorrectData = true;
                }
            }

            if (!incorrectData) {
                GoalSingleton.getInstance().addGoal(newGoal);
                finish();
            }
        }
    }

}
