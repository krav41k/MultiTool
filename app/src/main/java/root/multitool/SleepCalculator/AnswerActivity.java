package root.multitool.SleepCalculator;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import root.multitool.R;

public class AnswerActivity extends AppCompatActivity  implements View.OnClickListener {

    private RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButton5,radioButton6;
    private Button buttonAlarm;
    private TextView textView;
    private static final String TAG = "SleepAnswerLog";
    private int[][] time = new int[7][7];
    private int radioButtonPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Log.d(TAG, "Создание формы");
        Intent intentSleep = getIntent();
        calculator(intentSleep.getIntExtra("minutes",0),intentSleep.getStringExtra("button"));
        Log.d(TAG, "Калькулятор выполнился");
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton1.setOnClickListener(this);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton2.setOnClickListener(this);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton3.setOnClickListener(this);
        radioButton4 = findViewById(R.id.radioButton4);
        radioButton4.setOnClickListener(this);
        radioButton5 = findViewById(R.id.radioButton5);
        radioButton5.setOnClickListener(this);
        radioButton6 = findViewById(R.id.radioButton6);
        radioButton6.setOnClickListener(this);
        buttonAlarm = findViewById(R.id.button);
        buttonAlarm.setOnClickListener(this);
        textView = findViewById(R.id.textView2);
        filler(intentSleep.getStringExtra("button"));
    }

    private void calculator(int minutes, String button) {
        Log.d(TAG, "выполнение метода calculator");
        int[] timeInMinutes = new int[7];
        int answer = 1;
        int[] first = {0, 540, 450, 360, 270, 180, 90};
        int[] second = {0, 105, 195, 285, 375, 465, 555};
        time[1][1]=minutes;
        while (time[1][1] >= 60 ) {
            time[1][1] -= 60;
            time[0][0]++;
        }
        while (answer <= 6) {
            if (button.equals("first")) {         //если нажата первая кнопка
                timeInMinutes[answer] =minutes - first[answer];
                time[answer][0] = 0;
                Log.d(TAG, "Время в минутах " + answer + " - " + timeInMinutes[answer]);
                while (timeInMinutes[answer] >= 60 ) {
                    timeInMinutes[answer] -= 60;
                    time[answer][0]++;
                }
                while (timeInMinutes[answer] < 0 ) {
                    timeInMinutes[answer] += 60;
                    time[answer][0]--;
                    if (time[answer][0] < 0) {
                        time[answer][0] =+ 24;
                    }
                }
                time[0][answer] = timeInMinutes[answer];
                Log.d(TAG, "Время " + answer + " - " + time[answer][0] + ":" + time[0][answer]);
                answer++;
            } else if (button.equals("second")) {   //если нажата вторая кнопка
                timeInMinutes[answer] = minutes + second[answer];
                time[answer][0] = 0;
                Log.d(TAG, "Время в минутах " + answer + " - " + timeInMinutes[answer]);
                while (timeInMinutes[answer] >= 60) {
                    timeInMinutes[answer] -= 60;
                    time[answer][0]++;
                    if (time[answer][0] >= 24) {
                        time[answer][0] -= 24;
                    }
                }
                time[0][answer] = timeInMinutes[answer];
                Log.d(TAG, "Время " + answer + " - " + time[answer][0] + ":" + time[0][answer]);
                answer++;
            }
        }
    }

    private void filler(String button){
        String str="";
            Log.d(TAG, "Присвоение текста радио кноркам");
            str = Integer.toString(time[1][0])+":"+Integer.toString(time[0][1]);
            Log.d(TAG, "Первый текст собран - "+str);
            radioButton1.setText(str);
            Log.d(TAG, "Присвоение текста 1-ой кнопке");
            str = Integer.toString(time[2][0])+":"+Integer.toString(time[0][2]);
            Log.d(TAG, "Второй текст собран");
            radioButton2.setText(str);
            Log.d(TAG, "Присвоение текста 2-ой кнопке");
            str = Integer.toString(time[3][0])+":"+Integer.toString(time[0][3]);
            radioButton3.setText(str);
            str = Integer.toString(time[4][0])+":"+Integer.toString(time[0][4]);
            radioButton4.setText(str);
            str = Integer.toString(time[5][0])+":"+Integer.toString(time[0][5]);
            radioButton5.setText(str);
            Log.d(TAG, "Присвоение текста 6-ой кнопке");
            str = Integer.toString(time[6][0])+":"+Integer.toString(time[0][6]);
            radioButton6.setText(str);
            if (button.equals("first")){
                str = "Если вы хотите проснуться в "+time[0][0]+":"+time[1][1]+ ", то вам нужно лечь спать в:";
                textView.setText(str);
            }else if(button.equals("second")){
                str = "Если вы хотите лечь спать в "+time[0][0]+":"+time[1][1]+ ", то вам нужно проснуться в:";
                textView.setText(str);
            }
    }

    private void createAlarm(int hour, int minutes){
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM).putExtra(AlarmClock.EXTRA_HOUR, hour).putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                if (radioButtonPosition != 0){
                    createAlarm(time[radioButtonPosition][0],time[0][radioButtonPosition]);
                }
            break;
            case R.id.radioButton1:
                radioButtonPosition=1;
            break;
            case R.id.radioButton2:
                radioButtonPosition=2;
            break;
            case R.id.radioButton3:
                radioButtonPosition=3;
            break;
            case R.id.radioButton4:
                radioButtonPosition=4;
            break;
            case R.id.radioButton5:
                radioButtonPosition=5;
            break;
            case R.id.radioButton6:
                radioButtonPosition=6;
            break;
        }
    }
}

