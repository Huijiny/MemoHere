package com.basic.appjam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //기본적으로 자바 클래스와 레이아웃 XML 파일은 수동으로 연결해주어야합니다.
    TextView text1; //텍스트 뷰를 설정하기 위해 선언합니다. 아직까진 이 곳의 객체와 XML 레이아웃의 뷰와 같지 않습니다.
    EditText edit1; // editText 는 사용자가 입력하는 글씨, 숫자 등을 나타내는 뷰를 의미합니다.
    Button btn1,btn2,btn3; //버튼은 버튼
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_intro); // onCreate(액티비티 생성) 시 setContentView 안의 레이아웃을 보여줍니다.

        //뷰와 클래스를 연결하는 과정입니다.
        //findViewById 로 LayoutXML 파일을 찾고 클래스파일에 정의된 onClick 을 연결하기위해
        //Listener 를 이용하여 연결합니다.this 는 지금 이 곳을 지칭합니다.
        findViewById(R.id.IntroLayout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.IntroLayout:
                setContentView(R.layout.activity_main); //인트로화면에서 터치시 메인 화면을 띄워줍니다.

                //이제 메인 레이아웃과 현재 클래스가 상호작용하게 이어줍니다.
                //기본적으로 R 이라는 클래스 안에 선언된다고 생각하시면 편합니다.
                // R 클래스 안의 textView1 이라는 id를 가진 친구를 현재 클래스와 연결한다고 생각합시다.
                text1 = findViewById(R.id.textView1);
                edit1 = findViewById(R.id.editText1);
                btn1 = findViewById(R.id.button1);
                btn2 = findViewById(R.id.button2);
                btn3 = findViewById(R.id.button3);

                //터치 이후 이벤트를 발생시키고 싶다면 아래의 onClick 함수와 연결되어야합니다.
                btn1.setOnClickListener(this);
                btn2.setOnClickListener(this);
                btn3.setOnClickListener(this);
                break;
            case R.id.button1:
                text1.setText(edit1.getText());
                // switch 문 들어오는 조건은 해당 뷰가 가진 id이고 해당 뷰를 설정하는 것은 현재 클래스에 선언된 객체를 이용합니다.
                break;
            case R.id.button2:
                Toast.makeText(this, edit1.getText(), Toast.LENGTH_SHORT).show();
                //Toast 는 글씨를 화면에 토스트처럼 띄워줍니다. 저 안에 들어가는 매개변수는 Toast.makeText(띄워줄 위치, 띄울 텍스트, 나타난 텍스트 유지 시간) 입니다.
                break;
            case R.id.button3:
                Intent intent = new Intent(this,SecondActivity.class);
                startActivity(intent);
                //Intent 는 액티비티 전환을 할 수 있게 해주는 역할을 합니다. 액티비티 뿐만 아니라 카메라, 앨범, 전화, 등등 여러 기능을 수행할 수 있습니다.
                break;
        }
    }
}
