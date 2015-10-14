package com.sloop.avtivityanim;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeccondActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seccond);

        Button previous = (Button) findViewById(R.id.previous);
        Button next = (Button) findViewById(R.id.next);

        previous.setOnClickListener(this);
        next.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.previous:
                finish();
                break;
            case R.id.next:
                openActivity(ThirdActivity.class);
                break;
        }
    }
}
