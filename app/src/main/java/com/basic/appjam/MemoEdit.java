package com.basic.appjam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MemoEdit  extends AppCompatActivity implements View.OnClickListener{
    Button back;
    Button save;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_edit);

        back = findViewById(R.id.back);
        save = findViewById(R.id.save);
        back.setOnClickListener(this);
        save.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
             break;
            case R.id.save:
                //Realm에 저장하기
        }
    }
}
