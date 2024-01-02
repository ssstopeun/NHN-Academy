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
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import javax.naming.OperationNotSupportedException;
import java.util.List;

@Slf4j
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class CalculatorTest {
    @Order(1)
    @Test
    @DisplayName("4-1. 문자열 수식을 받아 형태소 단위로 분리합니다. (15점)")
    void parsing() {
        String expression = "3 * 2 + ( 4 / 5 * -2)";
        List<String> expected = List.of("3","*","2","+","(","4","/","5","*","-2",")");
        List<String> actual = Calculator.parsing(expression);
        log.info("{}",actual);

        Assertions.assertEquals(expected,actual);
    }

    @Order(2)
    @DisplayName("4-2. RPN 계산기 입력 가능 형태로 만들어 봅니다. (15점)")
    @Test
    void infixToPostfix() {
        String expression = "3 * 2 + ( 4 / 5 * -2)";
        List<String> expected = List.of("3", "2", "*", "4", "5", "/", "-2", "*", "+");
        List<String> actual = Calculator.infixToPostfix(Calculator.parsing(expression));
        log.info("{}", actual);
        Assertions.assertEquals(expected,actual);
    }

    @Order(3)
    @DisplayName("4-3. RPN 계산기를 만들어 봅니다. (15점)")
    @Test
    void run() throws OperationNotSupportedException {
        String expression = "3 * 2 + ( 4 / 5 * -2)";
        MyNumber expected = new MyInteger(6);
        MyNumber actual = Calculator.run(expression);
        log.info("{}",actual);
        Assertions.assertEquals(expected,actual);
    }

    @Order(4)
    @DisplayName("5-1. Calculator class에서 RationalNumber도 처리 가능하도록 수정합니다. (20 점)")
    @Test
    void runByRationalNumber() throws OperationNotSupportedException {
        String expression = "[5,3] * [3,5] + ( [-4,2] * [1,4] )";
        MyNumber expected = new RationalNumber(1,2);
        MyNumber actual = Calculator.run(expression);
        Assertions.assertEquals(expected,actual);
    }
}