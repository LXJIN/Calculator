package com.example.jin.calculator;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Calculator extends AppCompatActivity {
    TextView tvResult = null;  //显示器
    private  double firstNumber = 0;// 第一次输入的值
    private  double secondNumber = 0;// 第二次输入的值
    private String NumS = null;//数字字符
    private static String result = null;// 显示的结果
    private static String Old_result = null;// 中间的结果
    private  int op=0;//判断操作数
    private double num = 0;//计算结果数值

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
        btnAdd.setOnClickListener(show);
        btnSub.setOnClickListener(show);
        btnMul.setOnClickListener(show);
        btnDiv.setOnClickListener(show);

        btnBackspace.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = tvResult.getText().toString();
                if(isEmpty(result)) return;
                else
                    tvResult.setText(result.substring(0, result.length()-1));//截取字符串
            }
        });

        btnCE.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = null;
                firstNumber = secondNumber = 0;
                tvResult.setText(result);
            }
        });

        btnEq.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    NumS = result.replace(Old_result, "");
                    System.out.println("NumS:" + NumS);
                    secondNumber=Double.valueOf(NumS);
                }catch(NumberFormatException e) {
                    System.out.println(e);
                }
                switch (op) {
                    case 1:
                        num = firstNumber + secondNumber;
                        break;
                    case 2:
                        num = firstNumber - secondNumber;
                        break;
                    case 3:
                        num = firstNumber * secondNumber;
                        break;
                    case 4:
                        num = firstNumber / secondNumber;
                        break;
                    default:
                        num = 0;
                        break;
                }
                //try{
                System.out.println("firstNumber:" + firstNumber);
                System.out.println("secondNumber:" + secondNumber);
                System.out.println("num:" + num);
                tvResult.setText(String.valueOf(num));
               // }catch(Resources.NotFoundException e){
                //    System.out.println(e);
               // }
            }
        });
    }

    class tvShow implements Button.OnClickListener{
        @Override
        public void onClick(View v) {
            result = tvResult.getText().toString();
            String input = ((Button) v).getText().toString();
            if (result.equals("00")) {
                tvResult.setText(null);
            }else {
                try{
                    //Pattern p = Pattern.compile("[^0-9]");
                    switch (v.getId()) {
                        case R.id.btnAdd:
                            //firstNumS = p.matcher(result).replaceAll("").trim();
                            firstNumber=Double.valueOf(result);
                            op=1;
                            Old_result = result + input;
                            break;
                        case R.id.btnSub:
                            //firstNumS = p.matcher(result).replaceAll("").trim();
                            firstNumber=Double.valueOf(result);
                            op=2;
                            Old_result = result + input;
                            break;
                        case R.id.btnMul:
                            //firstNumS = p.matcher(result).replaceAll("").trim();
                            firstNumber=Double.valueOf(result);
                            op=3;
                            Old_result = result + input;
                            break;
                        case R.id.btnDiv:
                            //firstNumS = p.matcher(result).replaceAll("").trim();
                            firstNumber=Double.valueOf(result);
                            op=4;
                            Old_result = result + input;
                            break;
                    }
                    result = result +input;
                    tvResult.setText(result);
                    System.out.println("result:" + result);
                    System.out.println("Old_result:" + Old_result);
                }catch(NumberFormatException e){
                    System.out.println(e);
                }
            }
        }
    }

    //判断字符串是否为空
    private boolean isEmpty(String str){
        //trim()从当前 String 对象移除所有前导空白字符和尾部空白字符
        return (str==null || str.trim().length()==0);
    }

}


