package com.example.latoris.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.List;

/**
 * Created by Latoris on 2016/9/6.
 */


public class Cal {
    public static String caltoanswer(String cal){
       try{
           Transform trans = new Transform();
            while(cal.contains("cos")||cal.contains("sin")||cal.contains("tan")||cal.contains("cosh")||cal.contains("sinh")||cal.contains("log")
                ||cal.contains("acos")||cal.contains("atan")||cal.contains("tanh")||cal.contains("sinh")){
                cal = trans.trans(cal);
            }
            ArrayList result = getStringList(cal);  //String转换为List
            result = getPostOrder(result);//中缀变后缀
            String i = calculate(result);   //计算
            return i;
        }
        catch(Exception e){
        	return "ERROR";
        }
    }

    public static ArrayList<String> getStringList(String str){
        ArrayList<String> result = new ArrayList<String>();
        String num = "";
        Boolean numSymbol = true;
        for (int i = 0; i < str.length(); i++) {
            System.out.println("现在处理的是"+String.valueOf(str.charAt(i)));
            if(Character.isDigit(str.charAt(i)) || str.charAt(i) == '.'){
                num = num + str.charAt(i);
              //  System.out.println("判断当前数为："+num);
            }
            //是e直接添加
            else if(str.charAt(i)=='e'){
                result.add(String.valueOf(Math.E));
            }
            //是p直接添加
            else if(str.charAt(i)=='p'){
                result.add(String.valueOf(Math.PI));
            }
            //之前的数字是小数且没有加0，例如输入的是.2
            else if(num != "" && num.charAt(0) == '.' && !Character.isDigit(str.charAt(i))){
                result.add(("0"+num));
                num = "";
                result.add(String.valueOf(str.charAt(i)));
            }
            //其他数字直接添加
            else if(num != ""){
                if(!numSymbol) {
                    num = String.valueOf(-Double.valueOf(num));
                    numSymbol =true;
                }
                result.add(num);
             //   System.out.println("添加数字" + String.valueOf(num));
                num="";
                result.add(String.valueOf(str.charAt(i)));
               // System.out.println("添加字符"+ str.charAt(i));
            }
            //处理转化后的表达式把对应的三角函数放入
            else if(str.charAt(i) == 'c' ||str.charAt(i) == 's' ||str.charAt(i) == 't' ||str.charAt(i) == 'a'){
                String Symbol = "";
                for(int j = i;j < str.length() && Character.isAlphabetic(str.charAt(j)); j++){
                    Symbol+=String.valueOf(str.charAt(j));
                    i++;
                }
                switch(Symbol){
                    case "cs":
                        Symbol = "cos";
                        break;
                    case "sn":
                        Symbol = "sin";
                        break;
                    case "tn":
                        Symbol = "tan";
                        break;
                    case "th":
                        Symbol = "tanh";
                        break;
                    case "an":
                        Symbol = "atan";
                        break;
                    case "as":
                        Symbol = "acos";
                        break;
                    case "sh":
                        Symbol = "sinh";
                        break;
                    case "ch":
                        Symbol = "cosh";
                        break;
                    default:
                        break;
                }
                i--;
                result.add(Symbol);
             //   System.out.println(Symbol);
            }
            //其他符号添加
            else{
                if(i>=1) {
                    if (str.charAt(i)=='-'&&(str.charAt(i - 1) == '+' ||
                            str.charAt(i - 1) == '-' ||
                            str.charAt(i - 1) == '*' ||
                            str.charAt(i - 1) == '/' ||
                            str.charAt(i - 1) == '(')) {
                        numSymbol = false;
               //         System.out.println("fu");
                    } else {
                        System.out.println("添加符号" + String.valueOf(str.charAt(i)));
                        result.add(String.valueOf(str.charAt(i)));
                    }
                }
                else{
                        result.add(String.valueOf(str.charAt(i)));
                        System.out.println("添加符号" + String.valueOf(str.charAt(i)));
                    }
            }
        }
        //添加最后一个数
        if(num != ""){
            if(!numSymbol)
                num = String.valueOf(-Double.valueOf(num));
            result.add(num);
        }
        //如果只有一个数加上0+，让后面可以计算
        if(result.size() == 1){
            result.add("+");
            result.add("0");
        }
        ;//System.out.println(result.get(1));
        System.out.println("前缀：");
        for(int j = 0; j < result.size(); j++){
            System.out.print(result.get(j)+" ");
        }
        return result;
    }

    /**
     * 将中缀表达式转化为后缀表达式
     * @param inOrderList
     * @return
     */
    public static ArrayList<String> getPostOrder(ArrayList<String> inOrderList){

        ArrayList<String> result = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < inOrderList.size(); i++) {
            if(Character.isDigit(inOrderList.get(i).charAt(0))){
                result.add(inOrderList.get(i));
                System.out.println("后缀添加数字"+inOrderList.get(i));
            }
            else if(inOrderList.get(i).charAt(0)=='-') {
                if (inOrderList.get(i).length() > 1) {
                    if (Character.isDigit(inOrderList.get(i).charAt(1))) {
                        result.add(inOrderList.get(i));
                    }

                } else {
                    while (!stack.isEmpty() && compare(stack.peek(), inOrderList.get(i))) {
                        result.add(stack.pop());
                    }
                    stack.push(inOrderList.get(i));
                }
            }
            else{
                switch (inOrderList.get(i).charAt(0)) {
                    case '(':
                        stack.push(inOrderList.get(i));
                        break;
                    case ')':
                        while (!stack.peek().equals("(")) {
                            result.add(stack.pop());
                        }
                        stack.pop();
                        break;
                    case '!':
                        result.add("!");
                        break;
                    default:
                        while (!stack.isEmpty() && compare(stack.peek(), inOrderList.get(i))){
                            result.add(stack.pop());
                        }
                        stack.push(inOrderList.get(i));
                        break;
                }
            }
        }
        while(!stack.isEmpty()) {
            result.add(stack.pop());

        }
        System.out.println("\n后缀：");
        for(int i = 0; i < result.size(); i++){
            System.out.print(result.get(i)+" ");
        }
        System.out.print("\n");
        return result;
    }

    /**
     * 计算后缀表达式
     * @param postOrder
     * @return
     */
    public static String calculate(ArrayList<String> postOrder){
        Stack stack = new Stack();
        for (int i = 0; i < postOrder.size(); i++) {
            System.out.println(postOrder.get(i).charAt(0));
            if(Character.isDigit(postOrder.get(i).charAt(0))){
                stack.push(Double.parseDouble(postOrder.get(i)));
                //System.out.println("栈添加数字"+Double.parseDouble(postOrder.get(i)));
            }
            /*阶乘用专门的大数来计算*/
            else if(postOrder.get(i).charAt(0) == '!'){
                Double front = (Double)stack.pop();
                BigDecimal result = new BigDecimal(1);
                BigDecimal a;
                double n = Math.rint(front);
                for(double j = 2; j <= n ; j++){
                    a = new BigDecimal(j);//转换为BigDecimal类型
                    result = result.multiply(a);
                }
                double res = result.doubleValue();
                stack.push(res);
            }
            /*是字母判断为函数，使用对应函数调用计算
             * 对于sin函数由于PI/180的精度问题需要进行四舍五入
             * 否则会出现sin30 = 0.500000000001
             * */
            else if(postOrder.get(i).charAt(0)!='l' && Character.isAlphabetic(postOrder.get(i).charAt(0))){
                Double front = (Double)stack.pop();
                Double result;
                Double res;
                switch(postOrder.get(i)){
                    case "acos":
                        result = Math.toDegrees(Math.acos(front));
             //           System.out.println("acos:"+result);
                        res = Arith.round(result, 13);
                        stack.push(res);
                        break;
                    case "atan":
                        result = Math.toDegrees(Math.atan(front));
               //         System.out.println(result);
                        res = Arith.round(result, 13);
                        stack.push(res);
                        break;
                    case "cos":
                        result = Math.cos(Math.toRadians(front));
                 //       System.out.println("cos:"+result);
                        res = Arith.round(result, 15);
                        stack.push(res);
                        break;
                    case "sin":
                        result = Math.sin(Math.toRadians(front));
                   //     System.out.println(result);
                        res = Arith.round(result, 15);
                        stack.push(res);
                        break;
                    case "tan":
                        result = Math.tan(Math.toRadians(front));
                     //   System.out.println(result);
                        res = Arith.round(result, 15);
                        stack.push(res);
                        break;
                    case "sinh":
                        result = Math.sinh(front);
                       // System.out.println(result);
                        res = Arith.round(result, 13);
                        stack.push(res);
                        break;
                    case "cosh":
                        result = Math.cosh(front);
                        //System.out.println(result);
                        res = Arith.round(result, 13);
                        stack.push(res);
                        break;
                    case "tanh":
                        result = Math.tanh(front);
                        //System.out.println(result);
                        res = Arith.round(result, 13);
                        stack.push(res);
                        break;
                    default:
                        break;
                }
            }
            else if(postOrder.get(i).length()>=2) {
                if (postOrder.get(i).charAt(0) == '-' && Character.isDigit(postOrder.get(i).charAt(1))) {
                    Double num = Double.parseDouble(postOrder.get(i));
                    stack.push(num);
                }
            }
            //运算符计算
            else{
                Double back  = (Double)stack.pop();
                Double front = (Double)stack.pop();
                double res = 0;
                switch (postOrder.get(i).charAt(0)) {
                    case '+':
                        res = front + back;
                        break;
                    case '-':
                        res = front - back;
                        break;
                    case '*':
                        res = front * back;
                        break;
                    case '/':
                        if (back == 0){
                            return "除数不能是0";
                        }
                        else{
                            res = front / back;
                        }
                        break;
                    case '^':
                        res = Math.pow(front, back);
                        break;
                    //对数运算
                    case 'l':
                        res = Math.log(front)/Math.log(back);
                        break;

                }
                stack.push(res);
            }
        }
        return String.valueOf(stack.pop());
    }

    /**
     * 比较运算符等级
     * @param peek
     * @param cur
     * @return
     */
    public static boolean compare(String peek, String cur){
        if("l".equals(peek) && ("/".equals(cur) || "*".equals(cur) ||"+".equals(cur) ||"-".equals(cur))){
            return true;
        }
        else if("^".equals(peek) && ("^".equals(peek) || "/".equals(cur) || "*".equals(cur) ||"+".equals(cur) ||"-".equals(cur))){
            return true;
        }else if("*".equals(peek) && ("/".equals(cur) || "*".equals(cur) ||"+".equals(cur) ||"-".equals(cur))){
            return true;
        }else if("/".equals(peek) && ("/".equals(cur) || "*".equals(cur) ||"+".equals(cur) ||"-".equals(cur))){
            return true;
        }else if("+".equals(peek) && ("+".equals(cur) || "-".equals(cur))){
            return true;
        }else if("-".equals(peek) && ("+".equals(cur) || "-".equals(cur))){
            return true;
        }
        return false;
    }
}
