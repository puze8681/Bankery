package com.example.parktaejun.bankery;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InformActivity extends AppCompatActivity {

    private EditText howMoney;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);

        howMoney = (EditText)findViewById(R.id.informMoney);
        send = (Button)findViewById(R.id.send);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = Integer.parseInt(send.getText().toString());

                Intent intent = new Intent(InformActivity.this,CameraActivity.class);

                intent.putExtra("howmoney",money);

                Intent intent1 = new Intent(InformActivity.this,CameraActivity.class);
                startActivity(intent1);

                finish();
            }
        });
    }


}