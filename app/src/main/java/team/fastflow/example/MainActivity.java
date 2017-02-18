package team.fastflow.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;


import kusu.constructor.formul.Moduls.Default;
import kusu.constructor.formul.Views.Formul;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    Formul formul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formul = (Formul) findViewById(R.id.formul);

        ((ToggleButton) findViewById(R.id.toggle)).setOnCheckedChangeListener(this);
        makeFormul(false);

        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.getResultAndBacklight).setOnClickListener(this);
        findViewById(R.id.clearBacklight).setOnClickListener(this);
        findViewById(R.id.noMove).setOnClickListener(this);
        findViewById(R.id.yesMove).setOnClickListener(this);
    }

    private void makeFormul(boolean isFormul) {
        try {
            if (isFormul) {
                formul
                        .setBlocks(Default.getDefBlock())
                        .setRoot(Default.getDefTree(this));
            } else {
                formul
                        .setBlocks(Default.getDefWordBlock())
                        .setRoot(Default.getDefWordTree(this));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear:
                formul.clearBlocks();
                break;
            case R.id.getResultAndBacklight:
                formul.getResult(true, false, false, true);
                break;
            case R.id.clearBacklight:
                formul.setBacklight(false);
                formul.clearCheck();
                break;
            case R.id.noMove:
                formul.setMove(false);
                break;
            case R.id.yesMove:
                formul.setMove(true);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        makeFormul(b);
    }
}

