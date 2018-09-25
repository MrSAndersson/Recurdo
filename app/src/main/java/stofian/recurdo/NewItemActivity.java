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


public class NewItemActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_new_item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        nameEditText = findViewById(R.id.new_item_name);
        categorySpinner = findViewById(R.id.new_item_category);
        intervalEditText= findViewById(R.id.new_item_interval);
        scaleSpinner = findViewById(R.id.new_item_interval_scale);
        weekViewGroup = findViewById(R.id.new_item_week_view_group);
        mondayCheckbox = findViewById(R.id.new_item_monday);
        tuesdayCheckbox = findViewById(R.id.new_item_tuesday);
        wednesdayCheckbox = findViewById(R.id.new_item_wednesday);
        thursdayCheckbox = findViewById(R.id.new_item_thursday);
        fridayCheckbox = findViewById(R.id.new_item_friday);
        saturdayCheckbox = findViewById(R.id.new_item_saturday);
        sundayCheckbox = findViewById(R.id.new_item_sunday);
        monthViewGroup = findViewById(R.id.new_item_month_view_group);
        dayOfMonthEditText = findViewById(R.id.new_item_day_of_month);
        createButton = findViewById(R.id.new_item_create_button);


        intervalEditText.setText("1");
        intervalEditText.addTextChangedListener(new IntervalChangeWatcher(this));
        weekViewGroup.setVisibility(View.GONE);
        monthViewGroup.setVisibility(View.GONE);
        dayOfMonthEditText.setText("1");

        ArrayAdapter<CharSequence> categorySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.new_item_categories, android.R.layout.simple_spinner_item);
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
            Item newItem = new Item();

            boolean incorrectData = false;

            String categoryString = categorySpinner.getSelectedItem().toString();
            Integer category = 0;

            switch (categoryString)
            {
                case "Exercise":
                    category = Item.EXERCISE;
                    break;
                case "Food":
                    category = Item.FOOD;
                    break;
                case "Hygiene":
                    category = Item.HYGIENE;
                    break;
                case "Resting":
                    category = Item.RESTING;
                    break;
                case "Studies":
                    category = Item.STUDIES;
                    break;
                case "Hobby":
                    category = Item.HOBBY;
                    break;
                case "Life":
                    category = Item.LIFE;
                    break;
                default:
                    Snackbar.make(view, "Category '" + categoryString +"' is invalid", Snackbar.LENGTH_LONG).show();
                    incorrectData = true;
                    break;
            }

            newItem.setCategory(category);

            String itemName = nameEditText.getText().toString();
            if (ItemsSingleton.getInstance().findItem(itemName).isPresent())
            {
                Snackbar.make(view, "Item with name '" + itemName + "' already exists", Snackbar.LENGTH_LONG).show();
                incorrectData = true;
            }
            newItem.setName(itemName);

            String intervalString = intervalEditText.getText().toString();

            if (!intervalString.equals("") && Integer.parseInt(intervalString) > 0) {
                newItem.setInterval(Integer.parseInt(intervalEditText.getText().toString()));
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
                    intervalScale = Item.DAYLY;
                    break;
                case "week":
                case "weeks":
                    intervalScale = Item.WEEKLY;
                    break;
                case "month":
                case "monthly":
                    intervalScale = Item.MONTHLY;
                    break;
                default:
                    Snackbar.make(view, "Interval '" + categoryString +"' is invalid", Snackbar.LENGTH_LONG).show();
                    incorrectData = true;
                    break;
            }
            newItem.setIntervalScale(intervalScale);

            boolean[] weekdays = new boolean[7];

            weekdays[Item.MONDAY] = mondayCheckbox.isChecked();
            weekdays[Item.TUESDAY] = tuesdayCheckbox.isChecked();
            weekdays[Item.WEDNESDAY] = wednesdayCheckbox.isChecked();
            weekdays[Item.THURSDAY] = thursdayCheckbox.isChecked();
            weekdays[Item.FRIDAY] = fridayCheckbox.isChecked();
            weekdays[Item.SATURDAY] = saturdayCheckbox.isChecked();
            weekdays[Item.SUNDAY] = sundayCheckbox.isChecked();

            if ( intervalScale.equals(Item.WEEKLY) && !(
                    weekdays[Item.MONDAY]
                            || weekdays[Item.TUESDAY]
                            || weekdays[Item.WEDNESDAY]
                            || weekdays[Item.THURSDAY]
                            || weekdays[Item.FRIDAY]
                            || weekdays[Item.SATURDAY]
                            || weekdays[Item.FRIDAY])
                    )
            {
                Snackbar.make(view, "Select at least one weekday", Snackbar.LENGTH_LONG).show();
                incorrectData = true;
            }

            newItem.setWeekdays(weekdays);

            if (intervalScale.equals(Item.MONTHLY))
            {
                String dayOfTheMonthString = dayOfMonthEditText.getText().toString();
                if (!dayOfTheMonthString.equals("") && Integer.parseInt(dayOfTheMonthString) > 0) {
                    newItem.setDayOfMonth(Integer.parseInt(dayOfMonthEditText.getText().toString()));
                } else
                {
                    Snackbar.make(view, "Invalid day of Month to recur on!", Snackbar.LENGTH_LONG).show();
                    incorrectData = true;
                }
            }

            if (!incorrectData) {
                ItemsSingleton.getInstance().addItem(newItem);
                finish();
            }
        }
    }

}
