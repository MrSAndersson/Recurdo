package stofian.recurdo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;


public class NewItemActivity extends AppCompatActivity {
    EditText nameEditText;
    Spinner categorySpinner;
    EditText intervalEditText;
    Spinner scaleSpinner;
    LinearLayout weekViewGroup;
    RadioButton mondayButton;
    RadioButton tuesdayButton;
    RadioButton wednesdayButton;
    RadioButton thursdayButton;
    RadioButton fridayButton;
    RadioButton saturdayButton;
    RadioButton sundayButton;
    LinearLayout monthViewGroup;
    EditText dayOfMonthButton;
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
        mondayButton = findViewById(R.id.new_item_monday);
        tuesdayButton = findViewById(R.id.new_item_tuesday);
        wednesdayButton = findViewById(R.id.new_item_wednesday);
        thursdayButton = findViewById(R.id.new_item_thursday);
        fridayButton = findViewById(R.id.new_item_friday);
        saturdayButton = findViewById(R.id.new_item_saturday);
        sundayButton = findViewById(R.id.new_item_sunday);
        monthViewGroup = findViewById(R.id.new_item_month_view_group);
        dayOfMonthButton = findViewById(R.id.new_item_day_of_month);
        createButton = findViewById(R.id.new_item_create_button);


        intervalEditText.setText("1");
        intervalEditText.addTextChangedListener(new IntervalChangeWatcher(this));
        weekViewGroup.setVisibility(View.GONE);
        monthViewGroup.setVisibility(View.GONE);
        dayOfMonthButton.setText("1");

        ArrayAdapter<CharSequence> categorySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.new_item_categories, android.R.layout.simple_spinner_item);
        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categorySpinnerAdapter);

        ArrayAdapter<CharSequence> scaleSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.interval_scale_singular, android.R.layout.simple_spinner_item);
        scaleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scaleSpinner.setAdapter(scaleSpinnerAdapter);

        scaleSpinner.setOnItemSelectedListener(new OnScaleSwitchListener());


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

            if (i2 != 0) {


                Integer interval = Integer.parseInt(charSequence.toString());

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

}
