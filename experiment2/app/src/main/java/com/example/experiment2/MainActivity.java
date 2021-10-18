package com.example.experiment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, dot;
    private Button add, sub, mul, div, equ;
    private String opt_num1 = "", opt_num2 = "", opt;
    private EditText result_et;
    int flag = 0;//0表示输入的为第一个数，1表示输入的为第二个数，2表示已经计算出结果，需要重置输入框的数据
    boolean judgeDot1 = false, judgeDot2 = false;//false表示两个数字都没有小数点，true表示有小数点

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        add = (Button) findViewById(R.id.add);
        sub = (Button) findViewById(R.id.sub);
        mul = (Button) findViewById(R.id.mul);
        div = (Button) findViewById(R.id.div);
        dot = (Button) findViewById(R.id.dot);
        equ = (Button) findViewById(R.id.equ);
        result_et = (EditText) findViewById(R.id.result_et);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        dot.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
        equ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (flag == 2) {
            result_et.setText("");
            flag = 0;
        }
        switch (v.getId()) {
            case R.id.btn0:
                inputDigit("0");
                break;
            case R.id.btn1:
                inputDigit("1");
                break;
            case R.id.btn2:
                inputDigit("2");
                break;
            case R.id.btn3:
                inputDigit("3");
                break;
            case R.id.btn4:
                inputDigit("4");
                break;
            case R.id.btn5:
                inputDigit("5");
                break;
            case R.id.btn6:
                inputDigit("6");
                break;
            case R.id.btn7:
                inputDigit("7");
                break;
            case R.id.btn8:
                inputDigit("8");
                break;
            case R.id.btn9:
                inputDigit("9");
                break;
            case R.id.dot:
                if (flag == 0) {
                    if (judgeDot1 || opt_num1.equals("")) {
                        break;
                    } else {
                        opt_num1 += ".";
                        result_et.setText(result_et.getText().toString() + ".");
                        judgeDot1 = true;
                    }
                } else {
                    if (judgeDot2 || opt_num2.equals("")) {
                        break;
                    } else {
                        opt_num2 += ".";
                        result_et.setText(result_et.getText().toString() + ".");
                        judgeDot2 = true;
                    }
                }
                break;
            case R.id.add:
                operation("+");
                break;
            case R.id.sub:
                operation("-");
                break;
            case R.id.mul:
                operation("*");
                break;
            case R.id.div:
                operation("/");
                break;
            case R.id.equ:
                flag = 0;
                equalsNum();
                break;
            default:
                result_et.setText("出现错误");
                break;
        }

    }

    /**
     * 按下符号按钮后的处理
     * @param symbol
     */
    private void operation(String symbol) {
        if (flag == 1 || opt_num1.equals("")) {
            return;
        } else {
            flag = 1;
            opt = symbol;
            result_et.setText(result_et.getText().toString() + symbol);

        }
    }

    /**
     * 按下数字按钮显示在屏幕上的数字，且存入变量中
     * @param digit
     */
    private void inputDigit(String digit) {
        if (flag == 0) {
            opt_num1 += digit;
            result_et.setText(result_et.getText().toString() + digit);
        } else {
            opt_num2 += digit;
            result_et.setText(result_et.getText().toString() + digit);
        }
    }

    /**
     * 按下等于按钮后，计算结果
     */
    private void equalsNum() {
        if (opt_num1.equals("") || opt_num2.equals("")) {
            result_et.setText("输入错误，请重新输入");
            opt_num1 = "";opt_num2 = "";opt = "";flag = 2;judgeDot1 = false;judgeDot2 = false;
        } else {
            switch (opt) {
                case "+":
                    result_et.setText(String.valueOf(Float.parseFloat(opt_num1) + Float.parseFloat(opt_num2)));
                    opt_num1 = "";opt_num2 = "";opt = "";flag = 2;judgeDot1 = false;judgeDot2 = false;
                    break;
                case "-":
                    result_et.setText(String.valueOf(Float.parseFloat(opt_num1) - Float.parseFloat(opt_num2)));
                    opt_num1 = "";opt_num2 = "";opt = "";flag = 2;judgeDot1 = false;judgeDot2 = false;
                    break;
                case "*":
                    result_et.setText(String.valueOf(Float.parseFloat(opt_num1) * Float.parseFloat(opt_num2)));
                    opt_num1 = "";opt_num2 = "";opt = "";flag = 2;judgeDot1 = false;judgeDot2 = false;
                    break;
                case "/":
                    result_et.setText(String.valueOf(Float.parseFloat(opt_num1) / Float.parseFloat(opt_num2)));
                    opt_num1 = "";opt_num2 = "";opt = "";flag = 2;judgeDot1 = false;judgeDot2 = false;
                    break;
                default:
                    result_et.setText("计算错误");
                    opt_num1 = "";opt_num2 = "";opt = "";flag = 2;judgeDot1 = false;judgeDot2 = false;
                    break;
            }
        }
    }
}