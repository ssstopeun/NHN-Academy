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

package com.nhnacademy.number;

import com.nhnacademy.number.impl.RationalNumber;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import javax.naming.OperationNotSupportedException;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RationalNumberCalcTest {
    @Test
    @Order(1)
    @DisplayName("2-1. 유리수에 유리수를 더할 수 있습니다. (2점)")
    void add() throws OperationNotSupportedException {
        MyNumber rn1 = new RationalNumber(1,2);
        MyNumber rn2 = new RationalNumber(1,3);
        MyNumber rn3 = rn1.plus(rn2);

        //1/2 + 1/3 = 5/6
        log.info("{} + {} = {}",rn1,rn2,rn3);

        Assertions.assertEquals("5/6",rn3.toString());

    }

    @Test
    @Order(2)
    @DisplayName("2-2. 유리수에서 유리수를 뺄 수 있습니다. (2점)")
    void minus() throws OperationNotSupportedException {
        MyNumber rn1 = new RationalNumber(1,2);
        MyNumber rn2 = new RationalNumber(1,3);
        MyNumber rn3 = rn1.minus(rn2);

        //1/2 - 1/3 = 1/6
        log.info("{} - {} = {}",rn1, rn2, rn3);

        Assertions.assertEquals("1/6",rn3.toString());
    }

    @Test
    @Order(3)
    @DisplayName("2-3. 유리수에 유리수를 곱할 수 있습니다. (2점)")
    void multipliedBy() throws OperationNotSupportedException {

        MyNumber rn1 = new RationalNumber(1,2);
        MyNumber rn2 = new RationalNumber(1,3);
        MyNumber rn3 = rn1.multipliedBy(rn2);

        //1/2 * 1/3 = 1/6
        log.info("{} * {} = {}", rn1,rn2,rn3);

        Assertions.assertEquals("1/6",rn3.toString());
    }

    @Test
    @Order(4)
    @DisplayName("2-4. 유리수를 유리수로 나눌 수 있습니다. (2점)")
    void dividedBy() throws OperationNotSupportedException {
        MyNumber rn1 = new RationalNumber(1,2);
        MyNumber rn2 = new RationalNumber(1,3);
        MyNumber rn3 = rn1.divideBy(rn2);
        //1/2 / 1/3 = 3/2

        log.info("{} / {} = {}",rn1,rn2,rn3);
        Assertions.assertEquals(rn3.toString(),"3/2");

    }

    @Test
    @Order(5)
    @DisplayName("2-5. 유리수의 값을 비교할 수 할 수 있습니다. (2점)")
    void equals(){
        MyNumber rn1 = new RationalNumber(1,2);
        MyNumber rn2 = new RationalNumber(1,3);
        MyNumber rn3 = new RationalNumber(rn2);

        log.info("rn1값과 rn2값이 동일합니다? {}",rn1.equals(rn2));
        log.info("rn2값과 rn3값이 동일합니다? {}",rn2.equals(rn3));

        Assertions.assertAll(
                ()->Assertions.assertFalse(rn1.equals(rn2)),
                ()->Assertions.assertTrue(rn2.equals(rn3))
        );
    }
}
