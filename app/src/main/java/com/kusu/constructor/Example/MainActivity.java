package com.kusu.constructor.Example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kusu.constructor.LeafType.Movable;
import com.kusu.constructor.Prototype.Leaf;
import com.kusu.constructor.R;
import com.kusu.constructor.Formul;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Formul formul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formul = (Formul) findViewById(R.id.formul);
        formul
                .setBlocks(Default.getDefBlock())
                .setRoot(Default.getDefRoot());
    }
}

