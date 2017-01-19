package team.fastflow.kusu.constructor.Example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import team.fastflow.kusu.R;
import team.fastflow.kusu.constructor.Formul;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Formul formul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formul = (Formul) findViewById(R.id.formul);
        try {
            formul
                    .setBlocks(Default.getDefBlock())
                    .setRoot(Default.getDefTree(this));
        } catch (Exception e) {
            e.printStackTrace();
        }

        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.getResultAndBacklight).setOnClickListener(this);
        findViewById(R.id.clearBacklight).setOnClickListener(this);
        findViewById(R.id.noMove).setOnClickListener(this);
        findViewById(R.id.yesMove).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
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
}

