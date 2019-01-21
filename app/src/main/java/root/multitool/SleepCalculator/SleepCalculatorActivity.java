package root.multitool.SleepCalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import root.multitool.R;


public class SleepCalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner1hours,spinner2hours,spinner1minutes,spinner2minutes;
    private static final String TAG = "SleepCalculatorLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_calculator);

        String[] hours = {"00", "01", "02", "03", "04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
        String[] minutes = {"00", "01", "02", "03", "04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
        Button calculate1, calculate2;

        ArrayAdapter<String> adapter1hour = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hours);
        adapter1hour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1hours = findViewById(R.id.spinner1);
        spinner1hours.setAdapter(adapter1hour);
        spinner1hours.setSelection(0);

        ArrayAdapter<String> adapter2hour = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hours);
        adapter2hour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2hours = findViewById(R.id.spinner3);
        spinner2hours.setAdapter(adapter2hour);
        spinner2hours.setSelection(0);

        ArrayAdapter<String> adapter1minutes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, minutes);
        adapter1minutes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1minutes = findViewById(R.id.spinner2);
        spinner1minutes.setAdapter(adapter1minutes);
        spinner1minutes.setSelection(0);

        ArrayAdapter<String> adapter2minutes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, minutes);
        adapter2minutes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2minutes = findViewById(R.id.spinner4);
        spinner2minutes.setAdapter(adapter2minutes);
        spinner2minutes.setSelection(0);

        Log.d(TAG, "Заполнение спинеров");

        calculate1 = findViewById(R.id.button1);
        calculate1.setOnClickListener(this);
        calculate2 = findViewById(R.id.button2);
        calculate2.setOnClickListener(this);
    }

    private void converting(Spinner spinnerHours, Spinner spinnerMinutes, String button){
        int hoursPosition = spinnerHours.getSelectedItemPosition();
        int minutesPosition = spinnerMinutes.getSelectedItemPosition();
        Log.d(TAG, "Получена позиция. Первая позиция: "+String.valueOf(hoursPosition)+". Вторая позиция: "+String.valueOf(minutesPosition)+".");
        Intent intentSleep = new Intent(this, AnswerActivity.class);
        intentSleep.putExtra("minutes",minutesPosition+(hoursPosition*60));
        intentSleep.putExtra("button",button);
        startActivity(intentSleep);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                Log.d(TAG, "Нажата первая кнопка");
                converting(spinner1hours,spinner1minutes,"first");
            break;
            case R.id.button2:
                Log.d(TAG,"Нажата вторая кнопка");
                converting(spinner2hours,spinner2minutes,"second");
            break;
        }
    }
}
