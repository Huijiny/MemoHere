package com.basic.appjam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MemoDetail extends AppCompatActivity implements View.OnClickListener {
    Button back;
    TextView title;
    TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.memo_detail);
        title = findViewById(R.id.detailTitle);
        content = findViewById(R.id.detailContent);
        back = findViewById(R.id.back);
        String ttl = getIntent().getStringExtra("Title");
        String cntnt= getIntent().getStringExtra("Content");
        title.setText(ttl);
        content.setText(cntnt);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();

        }
    }
}
