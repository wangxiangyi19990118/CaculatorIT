package com.example.com.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//00000000000000
public class MainActivity extends AppCompatActivity {
    private double Firstnumber = 0;//第一次按键结果
    private double Secondnumber = 0;//第二次按键结果
    private double Forthnumber = 0;//第三个记数量
    private double Thirdnumber = 0;//第四个记数量
    private double Secondnumber1 = 0;//一个中间量
    private int number = 0;//一个中间量
    double Result = 0;//计算结果
    int op = 0;//判断操作数
    int op1=0;//存储上一个按键的操作数
    int op3=0;//判断是否继续刚才的计算
    int count1=0;//一个记数器
    int count2=0;//也是一个记数器
    boolean isFirst=false ;//判断是否有第一操作数
    boolean isPoint = false;//判断是否按了小数点
    boolean isSecNull = false;//判断是否有第二次按键
    boolean isEqual = false;//判断是否按了“=”按钮
    boolean isBack=true;//判断能否按下BACK按钮
    boolean isClick=false;//判断是否按下了几个数字键
    //1111111111111111111111
    private View.OnClickListener NumListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {//对数字键的监听
            TextView text = (TextView) findViewById(R.id.TxtDisplay);
            if(text.getText().equals("NaN")) {text.setText("Error!"); EnableButton() ;}//如果计算结果不正确，报错
            Button but = (Button) view;
            isClick=true;//按下了数字键
            count2++;
            isBack=true;//可以按下BACK键
            switch (1){
                case 1:
                    if (op == 0) {//证明是第一次计算，没有按加减乘除四个键
                        if (isPoint == false) {//没有按下小数点键，即整数计算
                            Firstnumber = Double.parseDouble(text.getText().toString() + but.getText().toString() );
                            double first = Firstnumber;
                            number = (int) first;
                            if (Firstnumber % 1 != 0)//如果结果是小数，直接输出结果
                                text.setText(String.valueOf(Double.parseDouble(text.getText().toString() + but.getText().toString())));
                            else//如果结果是整数，将浮点数转为整数显示
                                text.setText(String.valueOf(number));
                        } else if(isPoint==true)//按下了小数点键，就没有了判断结果是否是整数的阶段
                        {
                            String a=but.getText().toString();
                            text.setText(text.getText().toString()+a);
                            Firstnumber = Double.parseDouble(text.getText().toString());
                            Thirdnumber = Firstnumber;
                        }
                        Thirdnumber = Firstnumber;//记录最近一次操作的结果
                        isFirst=true ;
                    }
                    else if(op!=0){//第二个操作数的计算
                        { if(text.getText().toString().equals("0"))
                            break;
                            double second = Secondnumber;
                            number = (int) second;
                            if (isPoint == false) {//判断是否按下了小数点键，其余判断和显示同上
                                isSecNull = true;
                                Secondnumber1 = Double.parseDouble(String.valueOf(number) + but.getText().toString());
                                Secondnumber = Secondnumber1;
                                if (Secondnumber % 1 != 0)
                                    text.setText(String.valueOf(Double.parseDouble(String.valueOf(Secondnumber) + but.getText().toString())));
                                else
                                    text.setText(String.valueOf((int) Secondnumber1));
                            }
                            else if(isPoint==true){//按了小数点键后的计算
                                isSecNull = true;
                                text.setText(text.getText().toString()+ but.getText().toString());
                                Secondnumber = Double.parseDouble((text.getText().toString()));
                            }
                            Thirdnumber = Secondnumber;//记录最近一次的操作结果
                        }
                    }
            } if((text.getText().toString().length())>17) { //如果位数超了，就不再进行输入
                EnableButton();
                Button btnCE = (Button) findViewById(R.id.BtnCE);
                Button btnC = (Button) findViewById(R.id.BtnC);
                Button btnBack = (Button) findViewById(R.id.BtnBack);
                btnCE.setEnabled(true);
                btnC.setEnabled(true);
                btnBack.setEnabled(true);
            }
        }
    };

    private void setButtonListener(int viewId){     //绑定监听器
        Button but = (Button) findViewById(viewId);
        but.setOnClickListener(NumListener2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取TextView Txt1，并将其置为0
        TextView t = (TextView) findViewById(R.id.TxtDisplay);
        t.setText("0");
        //给按钮1234567890绑定监听器
        setButtonListener(R.id.Btn1);
        setButtonListener(R.id.Btn2);
        setButtonListener(R.id.Btn3);
        setButtonListener(R.id.Btn4);
        setButtonListener(R.id.Btn5);
        setButtonListener(R.id.Btn6);
        setButtonListener(R.id.Btn7);
        setButtonListener(R.id.Btn8);
        setButtonListener(R.id.Btn9);
        setButtonListener(R.id.Btn0);
    }
    public void  BtnAddClick(View v){//加法计算按键
        isPoint =false;
        TextView text = (TextView) findViewById(R.id.TxtDisplay);
        if(isEqual ==true&&isClick==true ){//如果按下=号后又按了数字键，又过来按了加号，证明是重新开始计算，自动清零
            Firstnumber  =Secondnumber ;
            Secondnumber=0;
            isEqual =false ;
            Result=0;
        }
        op = 1;//记录操作数为1
        switch (op1) {//对于刚才记录下来的第一第二操作数进行运算，如果刚才没有按下加减乘除，就跳出
            case 1://上一次是加法的情况
                if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    break;
                Firstnumber = Firstnumber + Secondnumber ;
                Secondnumber = 0;
                break;
            case 2://上一次是减法
                if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    break;
                Firstnumber = Firstnumber -Secondnumber;
                Secondnumber = 0;
                break;
            case 3://上一次是乘法
                if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    break;
                Firstnumber = Firstnumber *Secondnumber;
                Secondnumber = 0;
                break;
            case 4://上一次是除法
                if(Secondnumber ==0){//如果被除数为零的话，报错
                    text.setText("Error!");
                    EnableButton();
                    break;
                }
                if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    break;
                Firstnumber = Firstnumber / Secondnumber;
                Secondnumber = 0;
                break;
            default :
                break;
        }
        op1=op;//记录最近一次的操作数
        op3=1;//证明按下了四则运算按键
    }
    public void  BtnMinusClick(View v){//减法计算按键
        TextView text = (TextView) findViewById(R.id.TxtDisplay);
        isPoint =false;
        if(isEqual ==true&&isClick==true){//如果按下=号后又按了数字键，又过来按了加号，证明是重新开始计算，自动清零
            Firstnumber  =Secondnumber ;
            Secondnumber=0;
            isEqual =false ;
            Result=0;
        }
        op = 2;//记录操作数为2
        switch (op1) {//对于刚才记录下来的第一第二操作数进行运算，如果刚才没有按下加减乘除，就跳出
            case 1://上一次是加法
                if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    break;
                Firstnumber = Firstnumber + Secondnumber;
                Secondnumber = 0;
                break;
            case 2://上一次是减法
                if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    break;
                Firstnumber = Firstnumber -Secondnumber;
                Secondnumber = 0;
                break;
            case 3://上一次是乘法
                if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    break;
                Firstnumber = Firstnumber *Secondnumber;
                Secondnumber = 0;
                break;
            case 4://上一次是除法
                if(Secondnumber ==0){//如果被除数为零，报错
                    text.setText("Error!");
                    EnableButton();
                    break;
                }
                if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    break;
                Firstnumber = Firstnumber / Secondnumber;
                Secondnumber = 0;
                break;
            default :
                break;
        }
        op1=op;//记录最近一次的操作数
        op3=1;//证明按下了四则运算按键
    }
    public void  BtnMultiplyClick(View v){//乘法运算按键
        TextView text = (TextView) findViewById(R.id.TxtDisplay);
        isPoint =false;
        if(isEqual ==true&&isClick==true)
            {//如果按下=号后又按了数字键，又过来按了加号，证明是重新开始计算，自动清零
                Firstnumber = Secondnumber;
                Secondnumber = 0;
                isEqual = false;
                Result = 0;
            }
            op = 3;//记录操作数为3
            switch (op1) {//对于刚才记录下来的第一第二操作数进行运算，如果刚才没有按下加减乘除，就跳出
                case 1://上一次是加法
                    if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                        break;
                    Firstnumber = Firstnumber + Secondnumber;
                    Secondnumber = 0;
                    break;
                case 2://上一次是减法
                    if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                        break;
                    Firstnumber = Firstnumber - Secondnumber;
                    Secondnumber = 0;
                    break;
                case 3://上一次是乘法
                    if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                        break;
                    Firstnumber = Firstnumber * Secondnumber;
                    Secondnumber = 0;
                    break;
                case 4://上一次是除法
                    if (Secondnumber == 0) {//如果被除数为零，报错
                        text.setText("Error!");
                        EnableButton();
                        break;
                    }
                    if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                        break;
                    Firstnumber = Firstnumber / Secondnumber;
                    Secondnumber = 0;
                    break;
                default:
                    break;
            }
            op1 = op;//记录最近一次的操作数
            op3 = 1;//证明按下了四则运算按键
        }
    public void  BtnDivideClick(View v){
        TextView text = (TextView) findViewById(R.id.TxtDisplay);
        isPoint =false;
        if(isEqual ==true&&isClick==true){//如果按下=号后又按了数字键，又过来按了加号，证明是重新开始计算，自动清零
            Firstnumber  =Secondnumber ;
            Secondnumber=0;
            isEqual =false ;
            Result=0;
        }
        op = 4;//记录操作数为4
        switch (op1) {//对于刚才记录下来的第一第二操作数进行运算，如果刚才没有按下加减乘除，就跳出
            case 1://上一次是加法
                if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    break;
                    Firstnumber = Firstnumber + Secondnumber;
                Secondnumber = 0;
                break;
            case 2://上一次是减法
                if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    break;
                Firstnumber = Firstnumber -Secondnumber;
                Secondnumber = 0;
                break;
            case 3://上一次是乘法
                if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    break;
                Firstnumber = Firstnumber *Secondnumber;
                Secondnumber = 0;
                break;
            case 4://上一次是除法
                if(Secondnumber ==0){//如果被除数为零，报错
                    text.setText("Error!");
                    EnableButton();
                    break;
                }
                if(isEqual ==true&&isClick==false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    break;
                Firstnumber = Firstnumber / Secondnumber;
                Secondnumber = 0;
                break;
            default :
                break;
        }
        op1=op;//记录最近一次的操作数
        op3=1;//证明按下了四则运算按键
    }
    public void  BtnEqualClick(View v){
        isEqual =true;//表示按下了等于键
        isPoint =false;//小数点键复位
        TextView text11 = (TextView) findViewById(R.id.TxtDisplay);
        isBack=false ;//结果不能进行BACK运算
        if(op3==0){//如果在上一次按下了等于键后只按了数字键没有按四则运算键，则直接根据上一次的四则运算符号对此数和上次最近操作数进行运算
            switch (op1){
                case 1:
                    if(isClick ==true)//如果按了数字键，则证明不是连续运算
                        Result = Forthnumber + Thirdnumber ;//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                    if(isClick ==false)
                    { Result =Result+ Thirdnumber ;//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                        Firstnumber =Result;}
                    if (Result % 1 != 0)//判断计算结果是否是整数
                        text11.setText(String.valueOf(Result));
                    else
                        text11.setText(String.valueOf((int) Result));
                    break;
                case 2:
                    if(isClick ==true)//如果按了数字键，则证明不是连续运算
                        Result = Forthnumber - Thirdnumber ;//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                    if(isClick ==false)
                    { Result = Result- Thirdnumber ;
                    Firstnumber =Result ;}//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                    if (Result % 1 != 0)//判断计算结果是否是整数
                        text11.setText(String.valueOf(Result));
                    else
                        text11.setText(String.valueOf((int) Result));
                    break;
                case 3:
                    if(isClick ==true)//如果按了数字键，则证明不是连续运算
                        Result = Forthnumber * Thirdnumber ;//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                    if(isClick ==false)
                    { Result =Result * Thirdnumber ;
                    Firstnumber =Result ;}//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                    if (Result % 1 != 0)//判断计算结果是否是整数
                        text11.setText(String.valueOf(Result));
                    else
                        text11.setText(String.valueOf((int) Result));
                    break;
                case 4:
                    if (Thirdnumber == 0) {//如果最近的数字是零并且上一次操作数为4，那么零就要成为被除数，报错
                        text11.setText("Error!");
                        EnableButton();
                        break;
                    }
                    if(isClick ==true)//如果按了数字键，则证明不是连续运算
                        Result = Forthnumber /Thirdnumber ;//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                    if(isClick ==false)
                    {Result = Result /Thirdnumber ;
                    Firstnumber =Result ;}//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                    if (Result % 1 != 0)//判断计算结果是否是整数
                        text11.setText(String.valueOf(Result));
                    else
                        text11.setText(String.valueOf((int) Result));
                    break;
                default:
                    break;
            }
            Secondnumber =0;
        }
        if(op3!=0)//如果有操作数
        {   if(isClick ==false )
            Thirdnumber =Firstnumber ;
            if(op == 1)//加法
        { Result = Firstnumber + Thirdnumber ;//最近操作数和第一计算数和最近的计算数进行运算
            if (Result % 1 != 0)//判断是否是整数然后决定如何显示
                text11.setText(String.valueOf(Result));
            else
                text11.setText(String.valueOf((int) Result));
            Forthnumber =Thirdnumber ;//保存最近的计算数字
            Secondnumber =0;//第二计算数清零
            Firstnumber =Result ;}//第一计算数等于本次计算结果
            if(op == 2){//减法
                Result = Firstnumber - Thirdnumber ;//最近操作数和第一计算数和最近的计算数进行运算
                if (Result % 1 != 0)//判断是否是整数然后决定如何显示
                    text11.setText(String.valueOf(Result));
                else
                    text11.setText(String.valueOf((int) Result));
                Forthnumber =Thirdnumber ;//保存最近的计算数字
                Secondnumber =0;//第二计算数清零
                Firstnumber =Result ;}//第一计算数等于本次计算结果
            if(op == 3){
                Result = Firstnumber * Thirdnumber ;//最近操作数和第一计算数和最近的计算数进行运算
                if (Result % 1 != 0)//判断是否是整数然后决定如何显示
                    text11.setText(String.valueOf(Result));
                else
                    text11.setText(String.valueOf((int) Result));
                Forthnumber =Thirdnumber ;//保存最近的计算数字
                Secondnumber =0;//第二计算数清零
                Firstnumber =Result ;}//第一计算数等于本次计算结果
            if(op == 4){
                if(Thirdnumber==0){
                    text11.setText("Error!");
                    EnableButton();
                }
                else{
                    Result = Firstnumber / Thirdnumber ;//最近操作数和第一计算数和最近的计算数进行运算
                    if (Result % 1 != 0)//判断是否是整数然后决定如何显示
                        text11.setText(String.valueOf(Result));
                    else
                        text11.setText(String.valueOf((int) Result));
                    Forthnumber =Thirdnumber ;//保存最近的计算数字
                    Secondnumber =0;//第二计算数清零
                    Firstnumber =Result ;}//第一计算数等于本次计算结果
                }}
        isClick=false ;//恢复默认值
        op3=0;
        count2=0;
    }

    public void  BtnCClick(View v){//按键C的事件，所有计算都清空
        TextView text = (TextView) findViewById(R.id.TxtDisplay);
        text.setText("0");
        Result = 0;
        Firstnumber = 0;
        Secondnumber = 0;
        Thirdnumber = 0;
        Forthnumber =0;
        isPoint = false;
        isSecNull = false;
        isEqual = false;
        isClick =false ;
        op = 0;
        op1=0;
        op3=0;
        Return();
    }
    public  void EnableButton(){//使发生错误时除了C以外的按键都无法使用

        Button btnPlus = (Button) findViewById(R.id.BtnPlus);
        Button btnMinus = (Button) findViewById(R.id.BtnMinus);
        Button btnMultiply = (Button) findViewById(R.id.BtnMultiply);
        Button btnDivide = (Button) findViewById(R.id.BtnDivide);
        Button btnEqual = (Button) findViewById(R.id.BtnEqual);
        Button btnRotting = (Button) findViewById(R.id.BtnRooting);
        Button btnSquare = (Button) findViewById(R.id.BtnSquare);
        Button btnReciprocal = (Button) findViewById(R.id.BtnReciprocal);
        Button btnCE = (Button) findViewById(R.id.BtnCE);
        Button btnC = (Button) findViewById(R.id.BtnC);
        Button btnBack = (Button) findViewById(R.id.BtnBack);
        Button btnSign = (Button) findViewById(R.id.BtnSign);
        Button btnPoint = (Button) findViewById(R.id.BtnPoint);
        btnPlus.setEnabled(false);
        btnMinus.setEnabled(false);
        btnMultiply.setEnabled(false);
        btnDivide.setEnabled(false);
        btnEqual.setEnabled(false);
        btnRotting.setEnabled(false);
        btnSquare.setEnabled(false);
        btnReciprocal.setEnabled(false);
        btnCE.setEnabled(false);
        btnC.setEnabled(true);
        btnBack.setEnabled(false);
        btnSign.setEnabled(false);
        btnPoint.setEnabled(false);
        Button btn1 = (Button) findViewById(R.id.Btn1);
        Button btn2 = (Button) findViewById(R.id.Btn2);
        Button btn3 = (Button) findViewById(R.id.Btn3);
        Button btn4 = (Button) findViewById(R.id.Btn4);
        Button btn5 = (Button) findViewById(R.id.Btn5);
        Button btn6 = (Button) findViewById(R.id.Btn6);
        Button btn7 = (Button) findViewById(R.id.Btn7);
        Button btn8 = (Button) findViewById(R.id.Btn8);
        Button btn9 = (Button) findViewById(R.id.Btn9);
        Button btn0 = (Button) findViewById(R.id.Btn0);
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
        btn0.setEnabled(false);
    }
    public void Return1(){//恢复所有按键但是不清空操作
        Button btnPlus = (Button) findViewById(R.id.BtnPlus);
        Button btnMinus = (Button) findViewById(R.id.BtnMinus);
        Button btnMultiply = (Button) findViewById(R.id.BtnMultiply);
        Button btnDivide = (Button) findViewById(R.id.BtnDivide);
        Button btnEqual = (Button) findViewById(R.id.BtnEqual);
        Button btnRotting = (Button) findViewById(R.id.BtnRooting);
        Button btnSquare = (Button) findViewById(R.id.BtnSquare);
        Button btnReciprocal = (Button) findViewById(R.id.BtnReciprocal);
        Button btnCE = (Button) findViewById(R.id.BtnCE);
        Button btnC = (Button) findViewById(R.id.BtnC);
        Button btnBack = (Button) findViewById(R.id.BtnBack);
        Button btnSign = (Button) findViewById(R.id.BtnSign);
        Button btnPoint = (Button) findViewById(R.id.BtnPoint);
        btnPlus.setEnabled(true);
        btnMinus.setEnabled(true);
        btnMultiply.setEnabled(true);
        btnDivide.setEnabled(true);
        btnEqual.setEnabled(true);
        btnRotting.setEnabled(true);
        btnSquare.setEnabled(true);
        btnReciprocal.setEnabled(true);
        btnCE.setEnabled(true);
        btnC.setEnabled(true);
        btnBack.setEnabled(true);
        btnSign.setEnabled(true);
        btnPoint.setEnabled(true);
        Button btn1 = (Button) findViewById(R.id.Btn1);
        Button btn2 = (Button) findViewById(R.id.Btn2);
        Button btn3 = (Button) findViewById(R.id.Btn3);
        Button btn4 = (Button) findViewById(R.id.Btn4);
        Button btn5 = (Button) findViewById(R.id.Btn5);
        Button btn6 = (Button) findViewById(R.id.Btn6);
        Button btn7 = (Button) findViewById(R.id.Btn7);
        Button btn8 = (Button) findViewById(R.id.Btn8);
        Button btn9 = (Button) findViewById(R.id.Btn9);
        Button btn0 = (Button) findViewById(R.id.Btn0);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);
        btn0.setEnabled(true);
    }
    public void Return(){//使所有按键恢复并清空操作
        Button btnPlus = (Button) findViewById(R.id.BtnPlus);
        Button btnMinus = (Button) findViewById(R.id.BtnMinus);
        Button btnMultiply = (Button) findViewById(R.id.BtnMultiply);
        Button btnDivide = (Button) findViewById(R.id.BtnDivide);
        Button btnEqual = (Button) findViewById(R.id.BtnEqual);
        Button btnRotting = (Button) findViewById(R.id.BtnRooting);
        Button btnSquare = (Button) findViewById(R.id.BtnSquare);
        Button btnReciprocal = (Button) findViewById(R.id.BtnReciprocal);
        Button btnCE = (Button) findViewById(R.id.BtnCE);
        Button btnC = (Button) findViewById(R.id.BtnC);
        Button btnBack = (Button) findViewById(R.id.BtnBack);
        Button btnSign = (Button) findViewById(R.id.BtnSign);
        Button btnPoint = (Button) findViewById(R.id.BtnPoint);
        btnPlus.setEnabled(true);
        btnMinus.setEnabled(true);
        btnMultiply.setEnabled(true);
        btnDivide.setEnabled(true);
        btnEqual.setEnabled(true);
        btnRotting.setEnabled(true);
        btnSquare.setEnabled(true);
        btnReciprocal.setEnabled(true);
        btnCE.setEnabled(true);
        btnC.setEnabled(true);
        btnBack.setEnabled(true);
        btnSign.setEnabled(true);
        btnPoint.setEnabled(true);
        Button btn1 = (Button) findViewById(R.id.Btn1);
        Button btn2 = (Button) findViewById(R.id.Btn2);
        Button btn3 = (Button) findViewById(R.id.Btn3);
        Button btn4 = (Button) findViewById(R.id.Btn4);
        Button btn5 = (Button) findViewById(R.id.Btn5);
        Button btn6 = (Button) findViewById(R.id.Btn6);
        Button btn7 = (Button) findViewById(R.id.Btn7);
        Button btn8 = (Button) findViewById(R.id.Btn8);
        Button btn9 = (Button) findViewById(R.id.Btn9);
        Button btn0 = (Button) findViewById(R.id.Btn0);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);
        btn0.setEnabled(true);
        TextView text = (TextView) findViewById(R.id.TxtDisplay);
        text.setText("0");
        Result = 0;
        Firstnumber = 0;
        Secondnumber = 0;
        Thirdnumber = 0;
        Forthnumber=0;
        isPoint = false;
        isSecNull = false;
        isEqual= false;
        isFirst =false ;
        isBack =true;
        op = 0;
        isClick=false ;
        op1=0;
    }
    public  void ButSignClick(View v){//正负号计算
        TextView textP = (TextView) findViewById(R.id.TxtDisplay);
        if (op == 0) {//证明是第一计算数
            if(isPoint ==false){
                Firstnumber = -Double.parseDouble(textP.getText().toString());
                int First=(int)Firstnumber ;
                if (Firstnumber % 1 != 0)
                    textP.setText(String.valueOf(Firstnumber));
                else
                    textP.setText(String.valueOf(First));
                }
            if(isPoint ==true){
                textP.setText("-"+textP.getText().toString());
                Firstnumber = Double.parseDouble(textP.getText().toString());
            }Thirdnumber = Firstnumber;
        } else if(op!=0)//证明是第二计算数
        {  if(isPoint ==false){
            Secondnumber = -Double.parseDouble(textP.getText().toString());
            int Sec=(int)Secondnumber ;
            if (Secondnumber % 1 != 0)
                textP.setText(String.valueOf(Secondnumber));
            else
                textP.setText(String.valueOf(Sec ));}
            if(isPoint ==true){
                textP.setText("-"+textP.getText().toString());
                Secondnumber = Double.parseDouble(textP.getText().toString());
            }Thirdnumber = Secondnumber;
        }
    }
    public void BtnCEClick(View v){//CE键，清空当前计算数
        TextView textCE = (TextView) findViewById(R.id.TxtDisplay);
        textCE.setText("0");
        if(op==0)
            Firstnumber =0;
        if(op!=0)
            Secondnumber =0;
        Thirdnumber =0;
        Return1();
    }
    public void BtnBackClick(View v){//BACK键，从右到左删除一位
        TextView text = (TextView) findViewById(R.id.TxtDisplay);
        if(isBack ==true){//判断是否可以进行此操作，如果不可以就直接跳出，不做任何操作
            String str = text.getText().toString();
            String str1=str.substring(0, str.length() - 1);
            if(str1.equals("-")){
                text.setText("0");
                EnableButton();
            }
            else if ((str.length() > 0 )&&( str.substring(0, str.length() - 1).length() > 0)) {//如果删除一位后剩余位数不为零
                text.setText(str.substring(0, str.length() - 1));
                if ((op1 == 0&&isEqual==false)||(op1!=0&&isEqual==true))//如果是第一计算数
                { Firstnumber = Double.parseDouble(str.substring(0, str.length() - 1));
                    Thirdnumber =Firstnumber ;}
                else {//如果是第二计算数
                    text.setText(str.substring(0, str.length() - 1));
                    Secondnumber = Double.parseDouble(text.getText() .toString() );
                    Thirdnumber =Secondnumber ;
                }
            } else if (str.length() > 0 && str.substring(0, str.length() - 1).length() == 0) {//如果删除一位后剩余位数为零，那么就将该计算数置为零
                text.setText("0");
                if ((op1 == 0&&isEqual==false)||(op1!=0&&isEqual==true))
                    Firstnumber = 0;
                else
                    Secondnumber = 0;
                Thirdnumber =0;
            }
            else if (str.length() > 0 && str.substring(0, str.length() - 1)== "-"){//如果删除一位后剩余位数为负号，那么就将该计算数置为零
                text.setText("0");
                if ((op1 == 0&&isEqual==false)||(op1!=0&&isEqual==true))
                    Firstnumber = 0;
                else
                    Secondnumber = 0;
                Thirdnumber =0;
            }
            else{//其他情况
                text.setText("0");
                if (op==0)
                    Firstnumber = 0;
                else
                    Secondnumber = 0;
                Thirdnumber =0;
            }
        }Return1();
    }
    public void BtnPointClick(View v){//小数点
        if (isPoint == false) {//如果之前没有按过小数点键，如果按了不做处理
            isPoint = true;
            TextView text = (TextView) findViewById(R.id.TxtDisplay);
            double   Text1=Double .parseDouble (text.getText().toString()) ;
            if (Text1== 0||(Text1 ==Result &&count2==0))//如果开始为0，
                text.setText(("0" + ".").toString());
            else
                text.setText(String.valueOf((int)Text1  )+ ".");
        }}
    public void BtnSquareClick(View v){//平方
        TextView text = (TextView) findViewById(R.id.TxtDisplay);
        double res = Double.parseDouble(text.getText().toString());
        if(op == 0)
            Firstnumber= res*res;
        else Secondnumber = res*res;
        Thirdnumber=res*res;
        String result = String.valueOf(res*res);
        int result2=(int)(res*res);
        if (Double.parseDouble(result )% 1 != 0)
            text.setText(result);
        else
            text.setText(String.valueOf(result2 ));
    }
    public void BtnReciprocalClick(View v){//取倒数
        TextView text = (TextView) findViewById(R.id.TxtDisplay);
        double res = Double.parseDouble(text.getText().toString());
        if (res != 0) {//被除数不为零
            if(op == 0)
                Firstnumber= 1 / res;
            else Secondnumber = 1 / res;
            Thirdnumber= 1 / res;
            Double result1 =  1 / res;
            int result2=(int)(1/res);
            double number1 =Double.parseDouble( String.valueOf(result1));
            if (result1 % 1 != 0)
                text.setText(String.valueOf(result1));
            else
                text.setText(String.valueOf(result2 ));
            op = 0;
        } else {//被除数为零，报错
            text.setText("ERROR!");
            EnableButton();
        }
    }
    public void BtnRootingClick(View v){//开平方
        TextView text = (TextView) findViewById(R.id.TxtDisplay);
        double res = Double.parseDouble(text.getText().toString());
        if (res >= 0) {//如果底数大于等于零，正常计算
            if(op == 0)
                Firstnumber=(Math.sqrt(res));
            else Secondnumber =(Math.sqrt(res));
            Thirdnumber=(Math.sqrt(res));
            Double result1 = (Math.sqrt(res));;
            double number1 = Double.parseDouble(String.valueOf(result1));
            int number2=(int) number1;
            if (result1 % 1 != 0)
                text.setText(String.valueOf(result1));
            else
                text.setText(String.valueOf(number2));
        } else {//如果底数等于零，报错
            text.setText("ERROR!");
            EnableButton();
        }
    }
}
