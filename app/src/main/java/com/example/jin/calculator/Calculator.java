package com.example.jin.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Calculator extends AppCompatActivity {
    TextView tvResult = null;  //显示器
    //boolean isClear = false; //用于是否显示器需要被清理

    private int[] btnNum_id = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
    private Button[] btnNum = new Button[btnNum_id.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //功能按钮
        tvResult = (TextView) findViewById(R.id.tvResult);
        Button btnBackspace = (Button) findViewById(R.id.btnBackspace);
        Button btnCE = (Button) findViewById(R.id.btnCE);
        Button btnAdd = (Button)findViewById(R.id.btnAdd);
        Button btnSub = (Button)findViewById(R.id.btnSub);
        Button btnMul = (Button)findViewById(R.id.btnMul);
        Button btnDiv = (Button)findViewById(R.id.btnDiv);
        Button btnEq = (Button)findViewById(R.id.btnEq);

        //数字按钮
        tvShow show = new tvShow();
        for (int i = 0; i < btnNum_id.length; i++) {
            btnNum[i] = (Button) findViewById(btnNum_id[i]);
            btnNum[i].setOnClickListener(show);
        }
        Button btnDot = (Button)findViewById(R.id.btnDot);
        btnDot.setOnClickListener(show);

        //监听事件
        Compute compute = new Compute();
        btnAdd.setOnClickListener(compute);
        btnSub.setOnClickListener(compute);
        btnMul.setOnClickListener(compute);
        btnDiv.setOnClickListener(compute);

        btnBackspace.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCE.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnEq.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    class tvShow implements Button.OnClickListener{
        @Override
        public void onClick(View v) {

        }
    }

    class Compute implements Button.OnClickListener{
        @Override
        public void onClick(View v) {

        }
    }

}
