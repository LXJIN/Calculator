package com.example.jin.calculator;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Calculator extends AppCompatActivity {
    TextView tvResult = null;  //显示器
    private  double firstNumber = 0;// 第一次输入的值
    private  double secondNumber = 0;// 第二次输入的值
    private String NumS = null;//数字字符
    private static String result = null;// 显示的结果
    private static String Old_result = null;// 中间的结果，形式：操作数+运算符，例如：1+
    private  int op=0;//判断操作符
    private static int Num_OP = 0;//操作符的个数
    private double num = 0;//计算结果数值

    private int[] btnNum_id = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
    private Button[] btnNum = new Button[btnNum_id.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //从布局文件中获取控件
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
            public void onClick(View v) {//退格
                result = tvResult.getText().toString();
                if(isEmpty(result)) return;
                else {
                    NumS = result.substring(result.length() - 1, result.length());//截取最后一个字符
                    if(NumS.equals("+") || NumS.equals("-") || NumS.equals("X")
                            || NumS.equals("÷")){
                        op=0;
                        Num_OP--;
                    }
                    result = result.substring(0, result.length() - 1);//截取字符串
                    tvResult.setText(result);

                }
            }
        });

        btnCE.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {//清空
                result = Old_result = NumS = null;
                num = firstNumber = secondNumber = 0;
                op = 0;
                Num_OP = 0;
                tvResult.setText(result);
            }
        });

        btnEq.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {//等于
                result = tvResult.getText().toString();
                compute();
                //try{
                tvResult.setText(String.valueOf(num));
                op = 0;
                Num_OP--;
               // }catch(Resources.NotFoundException e){
                //    System.out.println(e);
               // }
            }
        });
    }

    //输出内容到TextView上，可给firstnum赋值
    class tvShow implements Button.OnClickListener{
        @Override
        public void onClick(View v) {
            result = tvResult.getText().toString();
            String input = ((Button) v).getText().toString();
            if (result.equals("00")) {
                tvResult.setText(null);
            }else {
                //System.out.println("Start_NumOP:" + Num_OP);
                //操作符个数为1时，判断第二个运算符是否输入
                if(Num_OP == 1){
                    result = result + input;
                    tvResult.setText(result);
                    switch (v.getId()) {
                        case R.id.btnAdd:
                            Num_OP++;//操作符个数加1
                            break;
                        case R.id.btnSub:
                            Num_OP++;
                            break;
                        case R.id.btnMul:
                            Num_OP++;
                            break;
                        case R.id.btnDiv:
                            Num_OP++;
                            break;
                        default:
                            break;
                    }
                    //System.out.println("result:" + result);
                    //System.out.println("Old_result:" + Old_result);
                    //System.out.println("NumOP:" + Num_OP);
                }
                /*操作符个数为2时，判断第二个运算符，将第一个运算符的计算结果赋值给第一个操作数
                firstnum*/
                if(Num_OP == 2) {
                    result = result.substring(0, result.length()-1);//删除最后一个字符
                    //System.out.println("result:" + result);
                    //System.out.println("Old_result:" + Old_result);
                    compute();//计算值
                    Num_OP--;//操作符个数减一
                    result = String.valueOf(num) + input;
                    tvResult.setText(result);
                    switch (v.getId()) {
                        case R.id.btnAdd:
                            firstNumber = num;
                            op = 1;
                            Old_result = result;
                            break;
                        case R.id.btnSub:
                            firstNumber = num;
                            op = 2;
                            Old_result = result;
                            break;
                        case R.id.btnMul:
                            firstNumber = num;
                            op = 3;
                            Old_result = result;
                            break;
                        case R.id.btnDiv:
                            firstNumber = num;
                            op = 4;
                            Old_result = result;
                            break;
                        default:
                            break;
                    }
                }
                //操作符个数为0时，如有运算符，则给第一个操作数firstnum赋值
                if(Num_OP == 0) {
                    try {
                        //Pattern p = Pattern.compile("[^0-9]");
                        //Old_result = result.replace(input, "");//替换字符串
                        switch (v.getId()) {
                            case R.id.btnAdd:
                                firstNumber = Double.valueOf(result);
                                op = 1;
                                Old_result = result + input;//保存中间结果
                                Num_OP++;//操作符个数加1
                                break;
                            case R.id.btnSub:
                                firstNumber = Double.valueOf(result);
                                op = 2;
                                Old_result = result + input;
                                Num_OP++;
                                break;
                            case R.id.btnMul:
                                firstNumber = Double.valueOf(result);
                                op = 3;
                                Old_result = result + input;
                                Num_OP++;
                                break;
                            case R.id.btnDiv:
                                firstNumber = Double.valueOf(result);
                                op = 4;
                                Old_result = result + input;
                                Num_OP++;
                                break;
                            default:
                                break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    result = result + input;
                    tvResult.setText(result);
                }
            }
            //System.out.println("End_NumOP:" + Num_OP);
            //System.out.println("result:" + result);
            //System.out.println("Old_result:" + Old_result);
        }
    }

    //给第二个操作数secondnum赋值，计算结果num
    private void compute(){
        try{
            NumS = result.replace(Old_result, "");//替换字符串
            //System.out.println("NumS:" + NumS);
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
                if(secondNumber != 0){
                    num = firstNumber / secondNumber;
                }else{
                    Toast.makeText(getApplicationContext(), "0不能为被除数",
                            Toast.LENGTH_SHORT).show();
                    num = firstNumber;
                }
                break;
            default:
                num = 0;
                break;
        }
        //System.out.println("firstNumber:" + firstNumber);
        //System.out.println("secondNumber:" + secondNumber);
        //System.out.println("num:" + num);
    }

    //判断字符串是否为空
    private boolean isEmpty(String str){
        //trim()从当前 String 对象移除所有前导空白字符和尾部空白字符
        return (str==null || str.trim().length()==0);
    }

}


