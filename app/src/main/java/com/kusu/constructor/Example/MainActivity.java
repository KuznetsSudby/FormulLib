package com.kusu.constructor.Example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kusu.constructor.LeafType.Movable;
import com.kusu.constructor.Prototype.Leaf;
import com.kusu.constructor.R;
import com.kusu.constructor.Formul;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Formul formul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formul = (Formul) findViewById(R.id.formul);
        formul
                .setBlocks(Default.getDefBlock())
                .setRoot(Default.getDefRoot());

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
                formul.getResult(true, false, false);
                break;
            case R.id.clearBacklight:
                formul.setBacklight(false);
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

