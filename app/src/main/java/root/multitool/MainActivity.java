package root.multitool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import root.multitool.SleepCalculator.SleepCalculatorActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonSleepCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSleepCalculator = findViewById(R.id.button);
        buttonSleepCalculator.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Intent intent = new Intent(this, SleepCalculatorActivity.class);
                startActivity(intent);
            break;
            default:
            break;
        }
    }
}
