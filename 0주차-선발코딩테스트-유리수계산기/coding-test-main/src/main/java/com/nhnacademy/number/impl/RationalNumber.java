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

package com.nhnacademy.number.impl;

import com.nhnacademy.number.MyNumber;

import javax.naming.OperationNotSupportedException;
import java.math.BigInteger;

public class RationalNumber implements MyNumber {
    private int numerator;      //분자
    private int denominator;    //분모
    private String rationalNumber;


    @Override
    public boolean equals(Object obj){
        RationalNumber rn = (RationalNumber)obj;
        if(this.numerator!=rn.getNumerator()||this.denominator!=rn.getDenominator()){
            return false;
        }
        return true;
    }

    public int getNumerator(){
        return numerator;
    }

    public int getDenominator(){
        return denominator;
    }

    @Override
    public String toString(){
        //분모가 1일땐 정수로 출력
        if(denominator==1){
            return String.valueOf(numerator);
        }else{
            return numerator+"/"+denominator;
        }
    }

    public RationalNumber(int n) {
        this.denominator=1;
        this.numerator=n;
    }

    public RationalNumber(int n1, int n2) {
        //약분
        int gcd = gcd(n1,n2);
        n1/=gcd;
        n2/=gcd;

        this.numerator=n1;
        this.denominator=n2;
    }

    public RationalNumber(MyNumber operand) {
        this(((RationalNumber)operand).getNumerator(),((RationalNumber)operand).getDenominator());
    }

    @Override
    public MyNumber plus(MyNumber operand) throws OperationNotSupportedException {
        return new RationalNumber(this.numerator*(((RationalNumber)operand).getDenominator())
                +this.denominator*(((RationalNumber)operand).getNumerator())
                ,this.denominator*(((RationalNumber)operand).getDenominator()));
    }

    @Override
    public MyNumber minus(MyNumber operand) throws OperationNotSupportedException {
        return new RationalNumber(this.numerator*(((RationalNumber)operand).getDenominator())
                -this.denominator*(((RationalNumber)operand).getNumerator())
                ,this.denominator*(((RationalNumber)operand).getDenominator()));
    }

    @Override
    public MyNumber multipliedBy(MyNumber operand) throws OperationNotSupportedException {
         return new RationalNumber(this.numerator*(((RationalNumber)operand).getNumerator())
                 ,this.denominator*(((RationalNumber)operand).getDenominator()));
    }

    @Override
    public MyNumber divideBy(MyNumber operand) throws OperationNotSupportedException {
        return new RationalNumber(this.numerator*(((RationalNumber)operand).getDenominator())
                ,this.denominator*(((RationalNumber)operand).getNumerator()));
    }

    //기약분수를 위한 최대공약수를 구하는 메서드
    private int gcd(int n1,int n2){
        BigInteger b1 = BigInteger.valueOf(n1);
        BigInteger b2 = BigInteger.valueOf(n2);
        return b1.gcd(b2).intValue();
    }
    public Double toDouble(){
        return this.numerator/(double)this.denominator;
    }
}
