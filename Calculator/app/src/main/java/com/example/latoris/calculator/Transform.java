package com.example.latoris.calculator;

/**
 * Created by Latoris on 2017/8/25.
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Transform {
    public Transform(){}
    public String trans(String a){
        int i = 0;
        String c = a;
        int flag = 0;
        int SymbolPosition = 0;
        int SymbolLength = 0;
        Stack<ArrayList> leftStack= new Stack<ArrayList>();
        ArrayList answer=(ArrayList) new ArrayList<ArrayList>();
        String symbol = null;

        while(i < a.length()){
            if(a.charAt(i) == '('){
                ArrayList temp = (ArrayList)new ArrayList();
                temp.add("(");
                temp.add(i);
                leftStack.push(temp);

            }
            else if(a.charAt(i) == ')'){
                ArrayList b = leftStack.pop();
                if(b.get(0) == "("){
                    b.add(i);
                    int first = (int)b.get(1);
                    answer.add(b);

                    if((int)b.get(1) == SymbolPosition+SymbolLength){
                        if(symbol.equals("log")){
                            int position = 0;
                            for(int j = first;j < i;j++){
                                if(a.charAt(j)==',')
                                    position = j;
                            }
                            c = a.substring(0,first-SymbolLength)+"("+a.substring(position+1, i)+")"+
                                    "l"+ "(" + a.substring(first+1,position)+")" +a.substring(i+1, a.length());
                        }
                        else if(symbol.equals("sin")||symbol.equals("cos")||symbol.equals("tan")||symbol.equals("tanh")||
                                symbol.equals("atan")||symbol.equals("acos")||symbol.equals("sinh")||symbol.equals("cosh")){
                            c = a.substring(0,first-SymbolLength)+a.substring(first, i+1)+
                                    symbol.charAt(0)+symbol.charAt(SymbolLength-1)+ a.substring(i+1, a.length());
                            System.out.println(c);
                        }
                    }
                }
                else
                    leftStack.push(b);
            }
            else if(i+3<a.length() && a.substring(i+3,i+4).equals("(")){
                System.out.println(":"+a.substring(i+3,i+4));
                if(flag == 0 && a.substring(i,i+3).equals("log")){
                    symbol= "log";
                    SymbolPosition = i;
                    SymbolLength = 3;
                    flag = 1;
                }
                else if(flag == 0 && (
                        a.substring(i,i+3).equals("cos")||
                                a.substring(i,i+3).equals("sin")||
                                a.substring(i,i+3).equals("tan"))
                        ){
                    symbol= a.substring(i,i+3);
                    SymbolPosition = i;
                    SymbolLength = 3;
                    flag = 1;
                    System.out.println("Symbol:"+symbol);
                }
            }
            else if(i+4<a.length()){
                if(flag == 0 && (a.substring(i,i+4).equals("cosh")||
                        a.substring(i,i+4).equals("sinh")||
                        a.substring(i,i+4).equals("tanh")||
                        a.substring(i,i+4).equals("atan")||
                        a.substring(i,i+4).equals("acos"))){
                    symbol= a.substring(i,i+4);
                    SymbolPosition = i;
                    SymbolLength = 4;
                    flag = 1;
                }

            }
            i++;
        }
        System.out.println("对应：");
        for(i = 0; i < answer.size();i++){
            ArrayList temp = (ArrayList) answer.get(i);
            int first = (int)temp.get(1);
            int second = (int)temp.get(2);
            System.out.println(first + "," + second);
        }
 //       System.out.println("阶段成果"+c);
        return c;

    }
}