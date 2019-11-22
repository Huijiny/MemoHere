package com.basic.appjam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MemoEdit  extends AppCompatActivity implements View.OnClickListener{
    Button back;
    Button save;
    EditText title;
    EditText content;
    double lat;
    double lon;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_edit);
        Intent intent = getIntent();
        lat = intent.getDoubleExtra("lat",0.0);
        lon = intent.getDoubleExtra("lon",0.0);
        back = findViewById(R.id.back);
        save = findViewById(R.id.save);
        title = findViewById(R.id.edit_title);
        content = findViewById(R.id.content);
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
                String ti = title.getText().toString();
                String con = content.getText().toString();
                API.addMemo(lat,lon,ti,con);
                finish();
        }
    }
}
