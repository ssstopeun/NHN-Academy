/*
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright 2023. NHN Academy Corp. All rights reserved.
 * + * While every precaution has been taken in the preparation of this resource,  assumes no
 * + responsibility for errors or omissions, or for damages resulting from the use of the information
 * + contained herein
 * + No part of this resource may be reproduced, stored in a retrieval system, or transmitted, in any
 * + form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the
 * + prior written permission.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

package com.nhnacademy.calculator;

import com.nhnacademy.number.MyNumber;
import com.nhnacademy.number.impl.MyInteger;
import com.nhnacademy.number.impl.RationalNumber;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.Array;
import java.util.*;

public class Calculator {
    static ArrayList<String> operators = new ArrayList<>(List.of("/","*","-","+"));
    static ArrayList<String> bracket = new ArrayList<>(List.of("(",")"));
    static ArrayList<String> squareBracket = new ArrayList<>(List.of("[","]"));
    public static List<String> parsing(String expression) {
        List<String> result = new ArrayList<>();
        ArrayList<String> letters = expressionToChar(expression);
        String number="";
        boolean rationalNum=false;

        for(int i=0;i<letters.size();i++){
            //추가 : rationalNumber parsing
            if(letters.get(i).equals("[")){
                rationalNum=true;
                number+=letters.get(i);
                continue;
            }
            if(letters.get(i).equals("]")){
                rationalNum=false;
                number+=letters.get(i);
                continue;
            }
            if(rationalNum){
                number+=letters.get(i);
                continue;
            }

            //괄호나 연산자라면
            if(operators.contains(letters.get(i))||bracket.contains(letters.get(i))){
                //- 일때 이게 음수부호인지 연산자인지 파악해야한다.
                //첫번째       - -> 음수부호
                //연산자 뒤에   - -> 음수부호
                //숫자 뒤에     - -> 연산자
                if(letters.get(i).equals("-")){
                    if(result.isEmpty()||operators.contains(result.get(result.size()-1))){
                        number+=letters.get(i);
                    }else{
                        if(!number.equals("")){
                            result.add(number);
                            number="";
                        }
                        result.add(letters.get(i));
                    }
                }else{
                    if(!number.equals("")){
                        result.add(number);
                        number="";
                    }
                    result.add(letters.get(i));
                }
            }else{
                number+=letters.get(i);
            }
        }
        return result;
    }

    //공백을 제거하고 한 글자씩 잘라주는 메서드
    private static ArrayList<String> expressionToChar(String expression) {
        String withoutSpace = expression.trim();
        ArrayList<String> result = new ArrayList<>();
        for(int i=0;i<withoutSpace.length();i++){
            Character letter = withoutSpace.charAt(i);
           if(letter==' '){
               continue;
           }else{
               result.add(String.valueOf(letter));
           }
        }
        return result;
    }

    public static List<String> infixToPostfix(List<String> arr) {
        Stack<String> operatorStack = new Stack<>();
        ArrayList<String> result = new ArrayList<>();
        Map<String,Integer> operatorPriority = new HashMap<>(); //우선순위판별을 위한 map
        operatorPriority.put("/",3);
        operatorPriority.put("*",2);
        operatorPriority.put("-",1);
        operatorPriority.put("+",0);

        for(int i=0;i<arr.size();i++){
            String letter = arr.get(i);
            //피연산자를 출력한다.
            if(!(operators.contains(letter)||bracket.contains(letter))){
                result.add(letter);
                continue;
            }

            //왼쪽괄호가 나오면 stack에 저장한다.
            if(letter.equals("(")){
                operatorStack.add(letter);
                continue;
            }
            //오른쪽괄호가 연산자 순서대로 출력
            if(letter.equals(")")){
                while(!operatorStack.isEmpty()){
                    if(operatorStack.peek().equals("(")){
                        break;
                    }
                    result.add(operatorStack.pop());
                }
                operatorStack.pop(); //왼쪽 괄호는 버린다.
                continue;
            }

            //연산자가 나오면 스택의 우선순위가 낮거나 같을 때까치 출력
            if(operators.contains(letter)){
                while(!operatorStack.isEmpty()){
                    if(operatorStack.peek().equals("(")){
                        break;
                    }
                    if(operatorPriority.get(operatorStack.peek())>operatorPriority.get(letter)){
                        result.add(operatorStack.pop());
                    }
                }
                operatorStack.push(letter);
                continue;
            }
        }
        while(!operatorStack.isEmpty()){
            result.add(operatorStack.pop());
        }

        return result;
    }

    public static <T extends MyNumber> T run(String expression) throws OperationNotSupportedException {
        List<String> infixToPostFix = infixToPostfix(parsing(expression));

        //squareBracket에 쌓여있다면 이는 유리수계산기로 처리한다.
        if(infixToPostFix.get(0).charAt(0)=='['){
            return rationalNumberCalculator(infixToPostFix);
        }else{
            return integerCalculator(infixToPostFix);
        }

    }

    private static <T extends MyNumber> T integerCalculator(List<String> infixToPostFix) throws OperationNotSupportedException {
        Stack<MyInteger> numberStack = new Stack<>();
        for(int i=0;i<infixToPostFix.size();i++){
            String letter = infixToPostFix.get(i);
            if(!operators.contains(letter)){
                MyInteger num = new MyInteger(Integer.parseInt(letter));
                numberStack.add(num);
            }else{
                //연산자를 만났을 때
                MyInteger i2 = numberStack.pop();
                MyInteger i1 = numberStack.pop();

                switch(letter){
                    case "+":
                        numberStack.push((MyInteger) i1.plus(i2));
                        break;
                    case "-":
                        numberStack.push((MyInteger) i1.minus(i2));
                        break;
                    case "*":
                        numberStack.push((MyInteger) i1.multipliedBy(i2));
                        break;
                    case "/":
                        numberStack.push((MyInteger) i1.divideBy(i2));
                        break;
                    default:
                        break;
                }
            }
        }

        return (T) numberStack.pop();
    }

    private static <T extends MyNumber> T rationalNumberCalculator(List<String> infixToPostFix) throws OperationNotSupportedException {
        Stack<RationalNumber> numberStack = new Stack<>();
        for(int i=0;i<infixToPostFix.size();i++){
            String letter = infixToPostFix.get(i);
            if(!operators.contains(letter)){
                String withoutBranketLetter = letter.substring(1,letter.length()-1);
                String[] onlyNum = withoutBranketLetter.split(",");
                RationalNumber r = new RationalNumber(Integer.parseInt(onlyNum[0]),Integer.parseInt(onlyNum[1]));
                numberStack.add(r);
            }else{
                //연산자를 만났을 때
                RationalNumber r2 = numberStack.pop();
                RationalNumber r1 = numberStack.pop();

                switch(letter){
                    case "+":
                        numberStack.push((RationalNumber) r1.plus(r2));
                        break;
                    case "-":
                        numberStack.push((RationalNumber) r1.minus(r2));
                        break;
                    case "*":
                        numberStack.push((RationalNumber) r1.multipliedBy(r2));
                        break;
                    case "/":
                        numberStack.push((RationalNumber) r1.divideBy(r2));
                        break;
                    default:
                        break;
                }
            }
        }

        return (T) numberStack.pop();
    }

}
